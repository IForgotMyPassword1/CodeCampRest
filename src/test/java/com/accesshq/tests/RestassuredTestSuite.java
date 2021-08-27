import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
public class RestassuredTestSuite {
    @Test
    public void TestResponse(){
        String key = "7a27ce7a-6e74-4ad0-a8cb-00aebfcfcbd2";
        String suburb = "Tascott";
        given().
            header("auth-key",key).param("q", suburb).
        when().
            get("https://digitalapi.auspost.com.au/postcode/search.json").
        then().
            assertThat().statusCode(200);
    }

    @Test
    public void TestPostcode(){
        given().
                header("auth-key","7a27ce7a-6e74-4ad0-a8cb-00aebfcfcbd2" ).param("q", "Tascott").
        when().
                get("https://digitalapi.auspost.com.au/postcode/search.json").
        then().
                assertThat().body("localities.locality.postcode", equalTo(2250));
    }
}
