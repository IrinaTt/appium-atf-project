package md.moldcell.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import md.moldcell.driver.DriverSession;
import md.moldcell.pages.LanguagePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LanguageSteps {

    private LanguagePage languagePage() {
        return new LanguagePage(DriverSession.driver());
    }

    @Then("localized content is displayed for all supported languages")
    public void localizedContentIsDisplayedForAllSupportedLanguages(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        List<Executable> languageChecks = new ArrayList<>();

        for (Map<String, String> row : rows) {
            languageChecks.add(() -> {
                String language = row.get("language");
                LanguagePage page = languagePage();

                page.selectLanguage(language);

                Assertions.assertAll(
                        "Localization for " + language,
                        () -> assertEquals(
                                page.normalizeText(row.get("expectedTextMessage")),
                                page.getTextMessage(),
                                "Incorrect message for language: " + language
                        ),
                        () -> assertEquals(
                                page.normalizeText(row.get("buttonText")),
                                page.getContinueButtonText(),
                                "Incorrect Continue button text for language: " + language
                        )
                );
            });
        }

        Assertions.assertAll("Language localization", languageChecks);
    }

    @When("user selects {} language")
    public void languageIsSelected(String language) {
        languagePage().selectLanguage(language);
    }
}
