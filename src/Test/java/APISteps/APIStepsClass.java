package APISteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstant;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
        request = given().header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
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
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
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
        response = request.when().post(APIConstants.CREATE_EMPLOYEE);
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

    //----------------------------------------------------------------------------------------------------------


    @Given("a request is prepared for getting a created employee")
    public void a_request_is_prepared_for_getting_a_created_employee() {
        RequestSpecification request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                queryParam("employee_id", employee_id);
    }

    @When("a GET call is made")
    public void a_get_call_is_made() {
        Response response = request.when().get(APIConstants.GET_ONE_EMPLOYEE);
    }

    @Then("the status code for get call is {int}")
    public void the_status_code_for_get_call_is(Integer int1) {
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
    }


    @Then("the {string} data coming from the response should match with actual data.")
    public void the_data_coming_from_the_response_should_match_with_actual_data
            (String empObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps();

        Map<String, String> actualData = response.jsonPath().get(empObject);


        for (Map<String, String> map: expectedData){
            //keys
            Set<String> keys = map.keySet();
            for (String key:keys){
                //values come from feature file

                String expectedValue = map.get(key);
                System.out.println(expectedValue);
                //values come from employee object - response data
                String actualValue = actualData.get(key);
                System.out.println(actualValue);
                Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }


    @Given("a request is prepared for creating an employee using API call with dynamic payload")
    public void a_request_is_prepared_for_creating_an_employee_using_api_call_with_dynamic_payload() {
     /*
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstant.createEmployeePayload());

      */

        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstant.jsonPayload());
    }


    @Given("a request is prepared for creating an employee using data {string} , {string} , {string} , {string} , {string} , {string} , {string} values")
    public void a_request_is_prepared_for_creating_an_employee_using_data_values(
            String fn, String ln, String mn,
            String gender, String birthday,
            String status, String jobtitle) {
        request = given().
                header(APIConstants.HEADER_CONTENT_TYPE_KEY,APIConstants.HEADER_CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION_KEY, token).
                body(APIPayloadConstant.jsonPayloadMoreDynamic(fn, ln,mn,gender,birthday,status,jobtitle));
    }
}
APIStepsClass.java
7 KB