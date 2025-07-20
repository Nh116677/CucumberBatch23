package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;

import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
        click(addEmployeeButton);
    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        WebElement firstNameLoc = driver.findElement(By.id("firstName"));
        sendText("Rabab", firstNameLoc);

        WebElement middleNameLoc = driver.findElement(By.id("middleName"));
        sendText("ms", middleNameLoc);

        WebElement lastNameLoc = driver.findElement(By.id("lastName"));
        sendText("karimzada", lastNameLoc);

    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        WebElement saveButton = driver.findElement(By.id("btnSave"));
        click(saveButton);

    }
    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        System.out.println("employee added");
    }


    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstname, String middlename, String lastname) {
        //order of the parameter is imp bcz it will fetch the values in the order from feature
        WebElement firstNameLoc = driver.findElement(By.id("firstName"));
        sendText(firstname, firstNameLoc);

        WebElement middleNameLoc = driver.findElement(By.id("middleName"));
        sendText(middlename, middleNameLoc);

        WebElement lastNameLoc = driver.findElement(By.id("lastName"));
        sendText(lastname, lastNameLoc);
    }

    @When("user enters {string} and {string} and {string} values")
    public void user_enters_and_and_values(String fn, String mn, String ln) {
        WebElement firstNameLoc = driver.findElement(By.id("firstName"));
        sendText(fn, firstNameLoc);

        WebElement middleNameLoc = driver.findElement(By.id("middleName"));
        sendText(mn, middleNameLoc);

        WebElement lastNameLoc = driver.findElement(By.id("lastName"));
        sendText(ln, lastNameLoc);
    }

    @When("user enters firstname middlename and lastname values from data table")
    public void user_enters_firstname_middlename_and_lastname_values_from_data_table
            (io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for(Map<String , String > employee :employeeNames){
            System.out.println(employee.get("firstName"));
            System.out.println(employee.get("middleName"));
            System.out.println(employee.get("lastName"));

            WebElement firstNameLoc = driver.findElement(By.id("firstName"));
            sendText(employee.get("firstName"), firstNameLoc);

            WebElement middleNameLoc = driver.findElement(By.id("middleName"));
            sendText(employee.get("middleName"), middleNameLoc);

            WebElement lastNameLoc = driver.findElement(By.id("lastName"));
            sendText(employee.get("lastName"), lastNameLoc);

            WebElement saveButton = driver.findElement(By.id("btnSave"));
            click(saveButton);

            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
            click(addEmployeeButton);
        }

    }



}