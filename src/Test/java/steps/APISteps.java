package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class APIStepsClass {


    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token;
    static String employee_id;
    RequestSpecification request;
    Response response;


    @Given("a JWT token is generated")
    public void a_jwt_token_is_generated() {
        request = given().header("Content-Type", "application/json").
                body("{\n" +
                        "  \"email\": \"miloud321@test.com\",\n" +
                        "  \"password\": \"Test@12345\"\n" +
                        "}");


        response = request.when().post("/generateToken.php");

        token = "Bearer " + response.jsonPath().getString("token");
    }

    @Given("a request is prepared for creating an employee using API call")
    public void a_request_is_prepared_for_creating_an_employee_using_api_call() {
        request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"emp_firstname\": \"rabab\",\n" +
                        "  \"emp_lastname\": \"shinde\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2002-05-21\",\n" +
                        "  \"emp_status\": \"permanent\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}");

    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
        response = request.when().post("/createEmployee.php");
    }

    @Then("the status code for this request is {int}")
    public void the_status_code_for_this_request_is(Integer int1) {
        response.then().assertThat().statusCode(201);
        //print your response in the console
        response.prettyPrint();
        //using hamcrest matchers, we validate the response body

    }

    @Then("the employee created has a message  contains {string} and value {string}")
    public void the_employee_created_has_a_message_contains_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as global variable")
    public void the_employee_id_is_stored_as_global_variable(String string) {
        employee_id =  response.jsonPath().getString("Employee.employee_id");
    }

}
