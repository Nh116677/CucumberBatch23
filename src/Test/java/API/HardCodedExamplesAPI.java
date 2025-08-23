package API;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

import io.restassured.response.Response;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamplesAPI {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3NTU5NjAwNjMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTc1NjAwMzI2MywidXNlcklkIjoiNzQ1MSJ9.ztaaFV6v--PnHy-Yod4L--vLyuZ6d4-YMcUHdd3fqoU";
    static String employee_id;


    @Test
    public void createEmployee(){
        //preparing the request
        RequestSpecification request = given().
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

        //sending the request
        Response response = request.when().post("/createEmployee.php");

        //validating the response
        response.then().assertThat().statusCode(201);
        //print your response in the console
        response.prettyPrint();
        //using hamcrest matchers, we validate the response body
        response.then().assertThat().body("Message", equalTo("Employee Created"));
        response.then().assertThat().body("Employee.emp_firstname", equalTo("rabab"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("shinde"));
        response.then().assertThat().header("Connection", equalTo("Keep-Alive"));

        //storing the employee id
        employee_id =  response.jsonPath().getString("Employee.employee_id");
        System.out.println(employee_id);
    }


    @Test
    public void bgetCreatedEmployee(){

        //preparing the request
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                queryParam("employee_id", employee_id);

        Response response = request.when().get("/getOneEmployee.php");

        response.then().assertThat().statusCode(200);
        response.prettyPrint();

    }


    @Test
    public void cUpdateEmployee(){

        //preparing the request
        RequestSpecification request = given().
                header("Content-Type","application/json").
                header("Authorization", token).
                body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"Aryan\",\n" +
                        "  \"emp_lastname\": \"karimzada\",\n" +
                        "  \"emp_middle_name\": \"sm\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"1979-07-29\",\n" +
                        "  \"emp_status\": \"lol\",\n" +
                        "  \"emp_job_title\": \"Director\"\n" +
                        "}");


        //sending the request
        Response response = request.when().put("/updateEmployee.php");

        //validate the response
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("Message", equalTo("Employee record Updated"));

    }



}