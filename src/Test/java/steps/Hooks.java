package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utils.CommonMethods;

public class Hooks extends CommonMethods {

    @Before
    public void start(){
        openBrowserAndLaunchApplication();
    }

    @After
    public void end(Scenario scenario){
        //scenario class in cucumber, it has all the details about the execution such as testcase name, status etc.

        byte[] pic;
        //it will generate the screenshot with the name and date pattern
        if(scenario.isFailed()) {
            pic = takeScreenshot("failed/"+scenario.getName());
        }else{
            pic = takeScreenshot("passed/"+scenario.getName());
        }

        //it will attach the screenshot for the report
        scenario.attach(pic,"image/png",scenario.getName());
        closeBrowser();
    }

}