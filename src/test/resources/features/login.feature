Feature: Login Functionality
@runValidLoginAsAdmin
Scenario: Login with Valid Credentials
Given the user is on the login page
When the user enters a username as "<username>"
And the user enters a password as "<password>"
And clicks on the login button
Then the user should see a successful login message

Examples:
|username|password|
|tomsmit|SuperSecretPassword!|


@runInvalidLoginAsAdmin
Scenario: Login with invalid Credentials
Given the user is on the login page
When the user enters a username as "<username>"
And the user enters a password as "<password>"
And clicks on the login button
Then the user should see an error login message

Examples:
|username|password|
|tomsmith|test|
|test|SuperSecretPassword|
|test|test|