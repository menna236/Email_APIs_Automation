package apis;
import config.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class EmailApiClient {

    public String isEmailDisposable(String email) {

        Response response = RestAssured.given()
                .when()
                .get(Config.userCheckBaseUrl +Config.DisposableEmailEndPoint + email)
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .response();
        return response.body().jsonPath().getString("disposable");
    }
    public String isEmailValid(String token, String email) {

        Response response = RestAssured.given()
                .queryParam("token", token)
                .when()
                .get(Config.verifyRightBaseUrl +Config.VerifyEmailEndPoint + email)
                .then()
                .log().all()
                .extract()
                .response();

        if (response.getStatusCode()==200)
        {
            return response.body().jsonPath().getString("status");
        }
        else
        {
            return response.body().jsonPath().getString("status")+ response.body().jsonPath().getString("error.message");
        }

    }
}