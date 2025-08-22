package steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.CommonMethods;
import utils.DBUtils;
import utils.ExcelReader;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class AddEmployeeSteps extends CommonMethods {



    String expectedFN;
    String expectedMN;
    String expectedLN;
    String empId;


    //  AddEmployeePage addEmployeePage = new AddEmployeePage();

    @When("user clicks on add employee option")
    public void user_clicks_on_add_employee_option() {
        WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
        click(addEmployeeButton);
    }

    @When("user enters firstname middlename and lastname")
    public void user_enters_firstname_middlename_and_lastname() {
        //WebElement firstNameLoc = driver.findElement(By.id("firstName"));
        sendText("Rabab", addEmployeePage.firstNameLoc);

        // WebElement middleNameLoc = driver.findElement(By.id("middleName"));
        sendText("ms", addEmployeePage.middleNameLoc);

        // WebElement lastNameLoc = driver.findElement(By.id("lastName"));
        sendText("karimzada", addEmployeePage.lastNameLoc);

    }

    @When("user clicks on save button")
    public void user_clicks_on_save_button() {
        //  WebElement saveButton = driver.findElement(By.id("btnSave"));
        click(addEmployeePage.saveButton);

    }



    @When("user enters {string} and {string} and {string}")
    public void user_enters_and_and(String firstname, String middlename, String lastname) {
        sendText(firstname, addEmployeePage.firstNameLoc);
        sendText(middlename, addEmployeePage.middleNameLoc);
        sendText(lastname, addEmployeePage.lastNameLoc);
        expectedFN =firstname;
        expectedMN =middlename;
        expectedLN =lastname;
        empId=addEmployeePage.empId.getAttribute("value");

    }


    @Then("employee is added successfully")
    public void employee_is_added_successfully() {
        String query="select emp_firstname,emp_middle_name,emp_lastname from hs_hr_employees where employee_id="+empId;
        List<Map<String,String>> fromDb= DBUtils.fetch(query);
        String actualFN=fromDb.get(0).get("emp_firstname");
        String actualMN=fromDb.get(0).get("emp_middle_name");
        String actualLN=fromDb.get(0).get("emp_lastname");
        Assert.assertEquals(expectedFN,actualFN);
        Assert.assertEquals(expectedMN,actualMN);
        Assert.assertEquals(expectedLN,actualLN);
    }


    @When("user enters {string} and {string} and {string} values")
    public void user_enters_and_and_values(String fn, String mn, String ln) {
        //  WebElement firstNameLoc = driver.findElement(By.id("firstName"));
        sendText(fn, addEmployeePage.firstNameLoc);

        //  WebElement middleNameLoc = driver.findElement(By.id("middleName"));
        sendText(mn, addEmployeePage.middleNameLoc);

        //  WebElement lastNameLoc = driver.findElement(By.id("lastName"));
        sendText(ln, addEmployeePage.lastNameLoc);
    }


    @When("user enters firstname middlename and lastname values from data table")
    public void user_enters_firstname_middlename_and_lastname_values_from_data_table
            (io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> employeeNames = dataTable.asMaps();

        for (Map<String, String> employee: employeeNames){
            System.out.println(employee.get("firstName"));
            System.out.println(employee.get("middleName"));
            System.out.println(employee.get("lastName"));

            // WebElement firstNameLoc = driver.findElement(By.id("firstName"));
            sendText(employee.get("firstName"), addEmployeePage.firstNameLoc);

            // WebElement middleNameLoc = driver.findElement(By.id("middleName"));
            sendText(employee.get("middleName"), addEmployeePage.middleNameLoc);

            // WebElement lastNameLoc = driver.findElement(By.id("lastName"));
            sendText(employee.get("lastName"), addEmployeePage.lastNameLoc);

            // WebElement saveButton = driver.findElement(By.id("btnSave"));
            click(addEmployeePage.saveButton);

            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
            click(addEmployeeButton);


        }

    }

    @When("user enters multiple employees using excel file")
    public void user_enters_multiple_employees_using_excel_file() throws IOException {
        List<Map<String, String>> employeeNames = ExcelReader.read();

        for (Map<String, String> employee: employeeNames){
            System.out.println(employee.get("firstName"));
            System.out.println(employee.get("middleName"));
            System.out.println(employee.get("lastName"));

            // WebElement firstNameLoc = driver.findElement(By.id("firstName"));
            sendText(employee.get("firstName"), addEmployeePage.firstNameLoc);

            //  WebElement middleNameLoc = driver.findElement(By.id("middleName"));
            sendText(employee.get("middleName"), addEmployeePage.middleNameLoc);

            // WebElement lastNameLoc = driver.findElement(By.id("lastName"));
            sendText(employee.get("lastName"), addEmployeePage.lastNameLoc);

            //   WebElement saveButton = driver.findElement(By.id("btnSave"));
            click(addEmployeePage.saveButton);

            WebElement addEmployeeButton = driver.findElement(By.id("menu_pim_addEmployee"));
            click(addEmployeeButton);

        }
    }

}