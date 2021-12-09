package HomeworkTests;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class MyHomeTests {

    @BeforeAll
    static void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test
    void successUsersListTest() {
        given()
                .when()
                .get("/api/users?page=2")
                .then()
                .statusCode(200)
                .body("per_page", is(6))
                .body("total_pages", is(2))
                .body("support.url", is("https://reqres.in/#support-heading"));
    }

    @Test
    void successUserDataTest() {
        given()
                .when()
                .get("api/users/4")
                .then()
                .statusCode(200)
                .body("data.email", is("eve.holt@reqres.in"))
                .body("data.last_name", is("Holt"))
                .body("data.avatar", is("https://reqres.in/img/faces/4-image.jpg"));
    }

    @Test
    void successResourceListTest() {
        given()
                .when()
                .get("api/unknown")
                .then()
                .statusCode(200)
                .body("page", is(1))
                .body("total_pages", is(2))
                .body("support.text", is("To keep ReqRes free, " +
                        "contributions towards server costs are appreciated!"));
    }

    @Test
    void successResourceDataTest() {
        given()
                .when()
                .get("api/unknown/5")
                .then()
                .statusCode(200)
                .body("data.id", is(5))
                .body("data.year", is(2004))
                .body("data.color", is("#E2583E"))
                .body("data.pantone_value", is("17-1456"));
    }

    @Test
    void successRegisterTest() {
        given()
                .contentType(JSON)
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .when()
                .post("/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }
/*
    @Test
    void successCreateTest() {
        given()
                .contentType(JSON)
                .body("{ \"name\": \"morpheus\", \"job\": \"leader\" }")
                .when()
                .post("/api/users")
                .then()
                .statusCode(201)
                .body("id", is(820))
                .body("createdAt", is("2021-11-30T15:44:13.270Z"));
    }
    */
}
