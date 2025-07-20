package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.SearchEmployeePage;
import utils.CommonMethods;

public class SearchEmployeeSteps extends CommonMethods {

    //SearchEmployeePage searchEmployeePage = new SearchEmployeePage();

    @When("user clicks on PIM option")
    public void user_clicks_on_pim_option() {
        WebElement pimOption = driver.findElement(By.id("menu_pim_viewPimModule"));
        //the benefit of using this method is to get extra facilities available in it.
        //it will wait first for the element to be clickable
        click(pimOption);
    }

    @When("user clicks on employee list option")
    public void user_clicks_on_employee_list_option() {
        WebElement empListOption = driver.findElement(By.id("menu_pim_viewEmployeeList"));
        //the benefit of using this method is to get extra facilities available in it.
        //it will wait first for the element to be clickable
        click(empListOption);
    }

    @When("user enters valid employee id")
    public void user_enters_valid_employee_id() {
        // WebElement empIdSearchField = driver.findElement(By.id("empsearch_id"));
        sendText("56370773", searchEmployeePage.empIdField);
    }


    @When("user clicks on search button")
    public void user_clicks_on_search_button() {
        //   WebElement searchButton = driver.findElement(By.id("searchBtn"));
        click(searchEmployeePage.searchButton);
    }


    @Then("user should be able to see employee details")
    public void user_should_be_able_to_see_employee_details() {
        System.out.println("test passed");
    }

    @When("user enters valid employee name")
    public void user_enters_valid_employee_name() {
        //  WebElement empNameSearchField = driver.findElement(By.id("empsearch_employee_name_empName"));
        sendText("mark", searchEmployeePage.empNameField);
    }

}