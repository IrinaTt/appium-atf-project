Feature: Language selection

  @CHK_0002 @DEF_0001 @EN @RO @RU
  Scenario: User can select each available language on the Language page
    Given the Language page is displayed
    Then localized content is displayed for all supported languages
      | language | expectedTextMessage                                                | buttonText |
      | English  | Now it’s easier to administrate the account! Choose your language. | Continue   |
      | Română   | Acum e și mai simplu să-ți administrezi contul! Selectează limba.  | Continuă   |
      | Русский  | Теперь управлять счетом стало еще проще! Выберите язык общения.    | Продолжить |
