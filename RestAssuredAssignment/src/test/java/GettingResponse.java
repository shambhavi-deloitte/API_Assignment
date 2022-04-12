import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.HashMap;
public class GettingResponse  {
    ResponseSpecification responseSpecification;
    RequestSpecification requestSpecification;
    @BeforeClass
    public void setup() {
        String BaseURI = "https://gorest.co.in/public/v1";
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri(BaseURI).addHeader("Content-Type", "application/json");
        requestSpecification = RestAssured.with().spec(requestSpecBuilder.build());
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().expectContentType(ContentType.JSON);
        responseSpecification = responseSpecBuilder.build();
    }
    @Test(priority = 1)
    public void test_post_call() throws Exception {
        HashMap hashMap ;
        ReadingExcel readingExcel = new ReadingExcel();
        hashMap = readingExcel.ReadCells();
        HashMap hash  = new HashMap();
        hash.put("name",hashMap.get(0));
        hash.put("gender",hashMap.get(1));
        hash.put("email",hashMap.get(2));
        hash.put("status",hashMap.get(3));
        Response response = requestSpecification.given().header("Authorization", "Bearer " + "1e3fbfc04d510423f16db6b1976fb8d49a339e4e486ef5006ebe118b552a2a32")
                .body(hashMap).
                when().
                post("/users").
                then().spec(responseSpecification).extract().response();
        System.out.println(response.getStatusCode());
        ResponseBody body = response.getBody();
        System.out.println(body.asString());
    }
    @Test(priority = 2)
    public void validation() throws Exception{
        HashMap hashMap ;
        ReadingExcel readingExcel = new ReadingExcel();
        hashMap = readingExcel.ReadCells();
        HashMap hash  = new HashMap();
        hash.put("name",hashMap.get(0));
        hash.put("gender",hashMap.get(1));
        hash.put("email",hashMap.get(2));
        hash.put("status",hashMap.get(3));
        Response response = requestSpecification.given().header("Authorization", "Bearer " + "1e3fbfc04d510423f16db6b1976fb8d49a339e4e486ef5006ebe118b552a2a32")
                .body(hash).
                when().
                post("/users").
                then().spec(responseSpecification).extract().response();
        System.out.println("Error Code is " + response.getStatusCode());
        JsonPath jsonPath = new JsonPath(response.asString());
        String message = jsonPath.getString("data.message");
        System.out.println( message);


    }

}