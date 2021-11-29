import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SelenoidTests {

    @Test
    void successStatusTest() {
        given().when()
                .get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusWithoutGivenWhenTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200);
    }

    @Test
    void successStatusWithResponseTest() {
        Response response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(response);              // bad log - io.restassured.internal.RestAssuredResponseImpl@1655b16b
        System.out.println(response.toString());    // bad log - io.restassured.internal.RestAssuredResponseImpl@1655b16b
        System.out.println(response.asString());    // good log - {"state":{"total":5,"used":0,"queued":0, ....

    }

    @Test
    void successStatusTotalKolhozTest() {
        Response response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract().response();

        assertTrue(response.asString().contains("\"total\":20")); // eto zhutkij kolhoz
    }

    @Test
    void successStatusTotalWithBodyTest() {
        get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .body("total", is(20));
    }

    @Test
    void successStatusTotalWithPathTest() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        assertEquals(20, response);
    }

    @Test
    void successStatusTotalWithAssertJTest() {
        Integer response = get("https://selenoid.autotests.cloud/status")
                .then()
                .statusCode(200)
                .extract()
                .path("total");

        assertThat(response).isEqualTo(20);
    }
}
