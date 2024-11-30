Feature: Form Verification

  Scenario: Verify the form fields
  When user redirect to website "https://d3pv22lioo8876.cloudfront.net/tiptop/"
  Then Verify that the text input element with xpath "(//input[@name='my-disabled'])" is disabled in the form
	Then Verify that the text input with value 'Readonly input' is in readonly state by using two xpaths
	Then Verify that the dropdown field to select color is having 8 elements using two xpaths
	Then Verify that the submit button is disabled when no data is entered in Name field
  Then Verify that the submit button enabled when both Name and Password field is entered
	Then Verify that on submit of 'Submit' button the page shows 'Received!' text
  Then Verify that on submit of form all the data passed to the URL