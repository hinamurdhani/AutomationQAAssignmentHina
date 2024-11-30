package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features", // Path to your feature files
    glue = "stepDefenition", // Package containing step definitions
    plugin = {
        "pretty", // Prints Gherkin steps in console
        "html:target/cucumber-reports.html", // HTML report
        "json:target/cucumber-reports.json" // JSON report for CI/CD pipelines
    },
    monochrome = true // Makes console output readable
)

public class FormVerificationRunner {

}