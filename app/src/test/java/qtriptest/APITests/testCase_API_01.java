package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
//import com.google.json.JsonObject;
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



public class testCase_API_01 {
   
    @Test(groups ="API Tests")
     public void UserRegistration(){
        //register API
 
    RestAssured.baseURI ="https://content-qtripdynamic-qa-backend.azurewebsites.net";
        RestAssured.basePath="/api/v1/register";

      // String payload ="{\"email\":\"anything13@gmail.com\",\"password\":\"xyz213@gmail.com\",\"confirmpassword\":\"xyz213@gmail.com\"}";
       
       
        Random random = new Random();
        String randomEmail = "test"+random.nextInt()+"@gmail.com";

        HashMap<String, String> hmap = new HashMap<>();
        hmap.put("email", randomEmail);
        hmap.put("password", "test@123");
        hmap.put("confirmpassword","test@123");

        JSONObject json1 = new JSONObject(hmap);

        RequestSpecification posthttpRequest1 = RestAssured.given().header("content-type", "application/json");
        posthttpRequest1.body(json1);
        // System.out.println(json1);
        
        Response response = posthttpRequest1.request(Method.POST);
        System.out.println(response.body().prettyPeek());
        int statusCode1 = response.getStatusCode();
        System.out.println(statusCode1);
        Assert.assertEquals(statusCode1, 201);
    



   // RestAssured.baseURI= "https://content-qtripdynamic-qa-backend.azurewebsites.net";
     RestAssured.basePath="/api/v1/login";
     hmap.remove("confirmpassword");

     JSONObject json2 = new JSONObject(hmap);


    // String postPayload = "{\"email\":\"anything13@gmail.com\",\"password\":\"xyz213@gmail.com\"}"; //from jason converter convert the body 

     RequestSpecification posthttpRequest2 = RestAssured.given().header("Content-Type", "application/json");
     posthttpRequest2.body(json2);

     Response response2 = posthttpRequest2.request(Method.POST);

    int statusCode2 = response.getStatusCode();
    Assert.assertEquals(statusCode2, 201);

    JsonPath jsonpath =  response.jsonPath();
    String token = jsonpath.get("success").toString();
    System.out.println(token);












// // @Test
// //     public void Register_Userand_Login() {
// //         RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
// //         RestAssured.basePath = "/api/v1/register";
// //         RequestSpecification httprequest = RestAssured.given().log().all();

// //         String uuid = UUID.randomUUID().toString();
// //         String randomemail = "user_" + uuid + "@example.com";
// //         JSONObject js = new JSONObject();
// //         // Map<Object, Object> hm = new HashMap<>();


// //         js.put("email", randomemail);
// //         js.put("password", "Password");
// //         js.put("confirmpassword", "Password");


// //         httprequest.header("Content-Type", "application/json");
// //         httprequest.body(js.toString());
// //         Response response = httprequest.request(Method.POST);
// //         System.out.println(response.getBody().prettyPeek());


// //         int registrationStatusCode = response.getStatusCode();
// //         Assert.assertEquals(registrationStatusCode, 201, "Failed to validate status code 201 for registration");

// //         RestAssured.basePath ="/api/v1/login";
// //         httprequest = RestAssured.given().log().all();
        
// //         js.remove("confirmpassword");

// //         httprequest.header("Content-Type", "application/json");
// //         httprequest.body(js.toString());
// //          response = httprequest.request(Method.POST);
// //          System.out.println(response.getBody().prettyPeek());
// //          registrationStatusCode = response.getStatusCode();
// //         Assert.assertEquals(registrationStatusCode, 201, "Failed to validate status code 201 for registration");

// // JsonPath jsonpath = response.jsonPath() ;
// // String  token =jsonpath.get("data.token").toString();

// // System.out.println(token);


    }
   }

   













