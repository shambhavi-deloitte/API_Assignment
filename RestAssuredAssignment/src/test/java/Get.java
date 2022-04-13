import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static java.lang.System.getProperties;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class Get {
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

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
    public void test_get_call(){
       JSONObject jsonObject = new JSONObject();
        Response response = requestSpecification.given()
                .when().
                get("/users").
                then().spec(responseSpecification).extract().response();
        for(int i =0;i<jsonObject.length();i++){
            boolean b = jsonObject.get("gender") == "male" && (jsonObject.get("gender") == "female");
            System.out.println(b);
            System.out.println(response.getStatusCode());
        }

    }
    @Test(priority = 2)
    public void GetEmailValidation() {
        int count = 0;
        String domain_check = getProperties().getProperty("domain_check");
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object obj = jsonArray.getJSONObject(i).get("email");
            if (obj.toString().contains(domain_check)) {
                count++;
                assertThat(count, greaterThan(2));
            }
        }
    }
    @Test(priority = 3)
    public void GetIdValidation() {
        ArrayList<Integer> id_list = new ArrayList<>();
        JSONArray jsonArray=new JSONArray();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object obj = jsonArray.getJSONObject(i).get("id");
            int id = Integer.parseInt(obj.toString());
            int count = 0;
            if (id_list.contains(id)) {
                break;
            } else {
                id_list.add(id);
            }
            assertThat(count, equalTo(1));
        }
    }

}
