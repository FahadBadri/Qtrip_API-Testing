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

public class testCase_API_04 {

    @Test(groups ="API Tests")

    public void NegativeTest() {
        RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath = "/api/v1/register";

        Random random = new Random();
        String Email = "Test12" + random.nextInt() + "@gmail.com";
        System.out.println(Email);
        JSONObject jsonobject = new JSONObject();
        jsonobject.put("email", Email);
        jsonobject.put("password", "password123");
        jsonobject.put("confirmpassword", "password123");

        RequestSpecification httprequest =
                RestAssured.given().header("Content-Type", "application/json");
        httprequest.body(jsonobject.toString());


        Response response = httprequest.request(Method.POST);
        System.out.println(response.getBody().prettyPeek());


        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        JsonPath jsonpath = response.jsonPath();
        String message = jsonpath.get("success").toString();
        System.out.println(message);

      

        Response response1 = httprequest.request(Method.POST);
        System.out.println(response1.getBody().prettyPeek());
        int statusCode1 = response1.getStatusCode();
        Assert.assertEquals(statusCode1, 400);
        JsonPath jsonpath1 = response1.jsonPath();
        String token1 = jsonpath1.get("success").toString();
        String message1 = jsonpath1.get("message").toString();
        System.out.println(token1);
        System.out.println(message1);

       

    }
}


