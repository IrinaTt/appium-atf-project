Feature: User authentication

  @CHK_0003 @CHK_016 @DEF_0001 @mobile @EN @RO @RU
  Scenario Outline: Validate Login page and access My Account page in all supported languages using mobile number
    Given the Language page is displayed
    When user selects <language> language
    And user clicks on the <continueButtonText> button
    Then the Login page is displayed
    And header contains value <headerText>
    And message text has value <textMessage>
    And forgot password button has text value <forgotPasswordButtonText> and is enabled
    And registration button has text value <registrationButtonText> and is enabled
    When user populates mandatory fields
      | userName | ${MY_MOLDCELL_USERNAME_MOBILE} |
      | password | ${MY_MOLDCELL_PASSWORD}        |
    And user clicks on the <loginButtonText> button
    And user clicks on the <laterButtonText> button
    Then Ooops popup is displayed
    When user clicks on the <closeButtonText> button
    Then My account mobile page is displayed
    Examples:
      | language | continueButtonText | forgotPasswordButtonText | loginButtonText | registrationButtonText | headerText                      | textMessage                                                                         | laterButtonText         | closeButtonText |
      | English  | Continue           | Forgot your password?    | Login           | Sign Up                | welcome to my moldcell!         | Manage everything simply, quickly, and worry-free. Let's make things easier!        | Configure later         | Close           |
      | Română   | Continuă           | Ai uitat parola?         | Logare          | Înregistrare           | bine ai venit în my moldcell!   | Administrează totul simplu, rapid și fără griji. Hai să facem lucrurile mai ușoare! | Configurează mai târziu | Închide         |
      | Русский  | Продолжить         | Забыли пароль?           | Войти           | Регистрация            | добро пожаловать в my moldcell! | Управляйте всем легко, быстро и без лишних хлопот. Пусть всё будет проще!           | Настроить позже         | Закрыть         |

  @CHK_0003 @CHK_016 @DEF_0001 @email @EN @RO @RU
  Scenario Outline: Validate Login page and access My Account page in all supported languages using email
    Given the Language page is displayed
    When user selects <language> language
    And user clicks on the <continueButtonText> button
    Then the Login page is displayed
    And header contains value <headerText>
    And message text has value <textMessage>
    And forgot password button has text value <forgotPasswordButtonText> and is enabled
    And registration button has text value <registrationButtonText> and is enabled
    When user populates mandatory fields
      | userName | ${MY_MOLDCELL_USERNAME_EMAIL} |
      | password | ${MY_MOLDCELL_PASSWORD}       |
    And user clicks on the <loginButtonText> button
    And user clicks on the <laterButtonText> button
    Then My account email page is displayed
    Examples:
      | language | continueButtonText | forgotPasswordButtonText | loginButtonText | registrationButtonText | headerText                      | textMessage                                                                         | laterButtonText         |
      | English  | Continue           | Forgot your password?    | Login           | Sign Up                | welcome to my moldcell!         | Manage everything simply, quickly, and worry-free. Let's make things easier!        | Configure later         |
      | Română   | Continuă           | Ai uitat parola?         | Logare          | Înregistrare           | bine ai venit în my moldcell!   | Administrează totul simplu, rapid și fără griji. Hai să facem lucrurile mai ușoare! | Configurează mai târziu |
      | Русский  | Продолжить         | Забыли пароль?           | Войти           | Регистрация            | добро пожаловать в my moldcell! | Управляйте всем легко, быстро и без лишних хлопот. Пусть всё будет проще!           | Настроить позже         |

  @CHK_0004 @DEF_002 @EN
  Scenario Outline: Validate Username and Password fields on Login page - <caseId>
    Given the Language page is displayed
    When user selects English language
    And user clicks on the Continue button
    Then the Login page is displayed
    When user populates mandatory fields
      | userName | <username> |
      | password | <password> |
    And user clicks on the Login button
    Then the Username error message is <usernameError>
    And the Password error message is <passwordError>

    Examples:
      | caseId                     | username        | password    | usernameError                                                                 | passwordError                   |
      | Both fields are empty      | [empty]         | [empty]     | Please enter your username.                                                   | You didn't enter your password. |
      | Only Username is empty     | [empty]         | Testtest123 | Please enter your username.                                                   | [none]                          |
      | Only Password is empty     | 79333333        | [empty]     | [none]                                                                        | You didn't enter your password. |
      | Username contains spaces   | [spaces]        | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Phone is too short         | 7933333         | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Phone is too long          | 793333333333    | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Phone has leading zero     | 079333333       | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Phone contains letters     | 7933abcd        | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Phone contains punctuation | 79333.333       | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Email has no at sign       | testgmail.com   | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Email has no local part    | @gmail.com      | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Email has no domain        | test@           | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Email has no domain suffix | test@gmail      | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
      | Email has two at signs     | test@@gmail.com | Testtest123 | Your username must be a phone number (without leading 0) or an email address. | [none]                          |
