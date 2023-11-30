package qtriptest.APITests;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.RestAssured;
import org.json.JSONObject;
import com.google.gson.JsonObject;

import java.io.File;
import java.util.List;
import java.util.UUID;
public class testCase_API_02 {

    @Test(groups ="API Tests")

public void SearchCity(){

RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
RestAssured.basePath= "/api/v1/cities";

RequestSpecification httprequest = RestAssured.given().param("q", "bengaluru");
Response response = httprequest.request().get();

int status = response.getStatusCode();
Assert.assertEquals(status, 200);
System.out.println(response.body().asPrettyString());


File schemaFile = new File("/home/crio-user/workspace/fahadcoolbuddy-95-ME_API_TESTING_PROJECT/app/src/test/resources/schema1.json");
JsonSchemaValidator jasonvalidator = JsonSchemaValidator.matchesJsonSchema(schemaFile);
response.then().assertThat().body(jasonvalidator); 


JsonPath jsonpath = response.jsonPath();
String description = jsonpath.get("description").toString();
System.out.println(description);
Assert.assertEquals(description, "[100+ Places]");

List responseList = response.jsonPath().getList("");

Assert.assertEquals(responseList.size(), 1);
































    
// RestAssured.baseURI = "https://content-qtripdynamic-qa-backend.azurewebsites.net";
// RestAssured.basePath= "/api/v1/cities";

// RequestSpecification http = RestAssured.given().param("q", "beng");
// //Response response = http.request().get();
// Response response1 = http.request(Method.GET);


// int responseStatusCode = response.getStatusCode();

// Assert.assertEquals(responseStatusCode, 200);
// System.out.println(response.body().prettyPeek());




// String responseBodyAsString = response.getBody().asString();
// //System.out.println(responseBodyAsString);

//    JsonPath  jsonpath = response.jsonPath();
//    String description = jsonpath.get("description").toString();
//    System.out.println(description);
//    Assert.assertEquals(description, "[100+ Places]");


//  JSONParser parser = new JSONParser();
//  JSONArray jsonArray = parser.parse(responseBodyAsString).getAsJsonArray();
//  Assert.assertEquals(jsonArray.size(), 1);
//  String responseDescription= jsonArray.get(0).getAsJsonObject().get("description").getAsString();




}



}
