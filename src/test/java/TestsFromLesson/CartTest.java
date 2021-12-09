package TestsFromLesson;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class CartTest {

    @Test
    void addItemToCartAsNewUserTest() {
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(1)"));
    }

    @Test
    void addItemToCartAsExistUserTest() {
        // request cart size
        // expected cart size +=1
        given()
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body("product_attribute_72_5_18=53&product_attribute_72_6_19=54&product_attribute_72_3_20=57&addtocart_72.EnteredQuantity=1")
                .cookie("__utmc=78382081; __utmz=78382081.1616511077.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); __RequestVerificationToken=1Ggj4MH1zaXe6_bAGo8oioIroK05NOUaOe14WhljCzw4J1QQJ534oqE_akH8FJqEeeP0Zd2Jpg9Wkcx1HkpBplq5dQnk7l9apiSN0CDLjPU1; ASP.NET_SessionId=1aj4jddt2se0pfra3vzngk4f; ARRAffinity=06e3c6706bb7098b5c9133287f2a8d510a64170f97e4ff5fa919999d67a34a46; __utma=78382081.1412259254.1616511077.1619017730.1621270694.10; Nop.customer=f06f17b2-5925-43a6-87dc-881192b61e6e; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=72&RecentlyViewedProductIds=71; __atuvc=0%7C16%2C0%7C17%2C0%7C18%2C0%7C19%2C10%7C20; __atuvs=60a2a4413aba0acf009; __utmb=78382081.18.10.1621270694")
                .when()
                .post("http://demowebshop.tricentis.com/addproducttocart/details/72/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(1)"));


    }
}
