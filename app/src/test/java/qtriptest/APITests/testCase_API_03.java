package qtriptest.APITests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
// import org.json.JSONObject;
// import org.openqa.selenium.json.Json;
import org.testng.Assert;
import org.testng.annotations.Test;



import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
// import org.json.simple.JSONObject;
// import org.json.JSONObject;
// import com.google.json.JsonObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.io.File;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.UUID;

public class testCase_API_03 {

        @Test(groups ="API Tests")

    public void bookingFlow() {


        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/register";



        Random random = new Random();
        String randomEmail = "test" + random.nextInt() + "@gmail.com";

        JSONObject json1 = new JSONObject();

        json1.put("email", randomEmail);
        json1.put("password", "test@123");
        json1.put("confirmpassword", "test@123");



        RequestSpecification posthttpRequest1 =
                RestAssured.given().header("content-type", "application/json");
        posthttpRequest1.body(json1);


        Response response = posthttpRequest1.request(Method.POST);
        // System.out.println(response.body().prettyPeek());
        int statusCode1 = response.getStatusCode();
        System.out.println(statusCode1);
        Assert.assertEquals(statusCode1, 201,
                "Failed to Validate status code 201 for Registration");

        RestAssured.basePath = "/api/v1/login";
        json1.remove("confirmpassword");


        JSONObject json2 = new JSONObject(json1);

        RequestSpecification posthttpRequest2 = RestAssured.given().header("Content-Type", "application/json");

        posthttpRequest2.body(json2);

        Response response2 = posthttpRequest2.request(Method.POST);

        int statusCode2 = response2.getStatusCode();
        Assert.assertEquals(statusCode2, 201,
                "Failed to Validate status code 201 for Registration");

        JsonPath jsonpath = response2.jsonPath();
        String token = jsonpath.get("data.token").toString();
        String id = jsonpath.getString("data.id").toString();

        System.out.println("Token is:" + token);
        System.out.println("Id is :" + id);


        JSONObject jsonObject1 = new JSONObject();
        String name = "Test1" + random.nextInt();
        jsonObject1.put("userId", id);
        jsonObject1.put("name", name);
        jsonObject1.put("date", "2023-12-12");
        jsonObject1.put("person", "1");
        jsonObject1.put("adventure", "2447910730");
        RestAssured.basePath = "/api/v1/reservations/new";
        RequestSpecification postReservation =
                RestAssured.given().header("Authorization",
                " Bearer " + token).header("Content-Type", "application/json").queryParam("q", "beng");
                

        postReservation.body(jsonObject1);

        Response response3 = postReservation.request(Method.POST);

        int StatusCode3 = response3.getStatusCode();
        Assert.assertEquals(StatusCode3, 200);
        System.out.println(response3.prettyPeek());
        System.out.println(StatusCode3);


      RestAssured.basePath= "/api/v1/reservations";

RequestSpecification checkReservation =RestAssured.given().log().all();
checkReservation.queryParam("id", id);
checkReservation.header("Authorization", "Bearer " + token);
                
        //        RestAssured. queryParam("id", id);
        //       checkReservation.given().header("Authorization"," Bearer " + token);

        Response response4 = checkReservation.request(Method.GET);
        int StatusCode4 = response4.getStatusCode();
        Assert.assertEquals(StatusCode4, 200);
        System.out.println(response3.prettyPeek());
        System.out.println(StatusCode4);



    }

}
//