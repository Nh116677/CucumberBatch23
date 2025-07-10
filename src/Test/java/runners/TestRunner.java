package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path of the feature directory or feature file
        features = "src/Test/resources/features/Login.feature",
        //name of the package where all the steps are found
        glue = "steps"
)



public class TestRunner {
}

