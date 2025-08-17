package utils;

import org.json.JSONObject;

public class APIPayloadConstant {


    public static String createEmployeePayload(){

        String createEmployee = "{\n" +
                "  \"emp_firstname\": \"rabab\",\n" +
                "  \"emp_lastname\": \"shinde\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"F\",\n" +
                "  \"emp_birthday\": \"2002-05-21\",\n" +
                "  \"emp_status\": \"permanent\",\n" +
                "  \"emp_job_title\": \"Manager\"\n" +
                "}";
        return createEmployee;
    }

    public static String jsonPayload(){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "rabab");
        obj.put("emp_lastname", "shinde");
        obj.put("emp_middle_name", "ms");
        obj.put("emp_gender", "F");
        obj.put("emp_birthday", "2002-05-21");
        obj.put("emp_status", "permanent");
        obj.put("emp_job_title", "Manager");

        return obj.toString();
    }

    public static String jsonPayloadMoreDynamic(
            String emp_firstname, String emp_lastname, String emp_middle_name, String emp_gender,
            String emp_birthday, String emp_status, String emp_job_title
    ){

        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", emp_firstname);
        obj.put("emp_lastname", emp_lastname);
        obj.put("emp_middle_name", emp_middle_name);
        obj.put("emp_gender", emp_gender);
        obj.put("emp_birthday", emp_birthday);
        obj.put("emp_status", emp_status);
        obj.put("emp_job_title",emp_job_title);

        return obj.toString();
    }



}