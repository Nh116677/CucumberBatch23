package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path of the feature directory or feature file
        features = "src/Test/resources/features/Login.feature",
        //name of the package where all the steps are found
        glue = "steps",
        //when we set the value of dry run to true, it stops execution and generate all missing step definition
        dryRun = true
)



public class TestRunner {
}

