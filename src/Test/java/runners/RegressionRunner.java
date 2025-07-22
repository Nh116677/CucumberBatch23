package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path of the feature directory or feature file
        features = "src/test/resources/features/",
        //name of the package where all steps are found
        glue = "steps",
        //when we set the value of dry run to true, it stops execution and generate all missing step definition
        //when we have to execute, we set the value to false
        dryRun = false,
        tags = "@regression",
        //pretty plugin prints all the steps in console
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
)


public class RegressionRunner {
}