package testFavourites;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;
import resources.MassaDeDados;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class Tests_Favourites extends MassaDeDados {

    @BeforeClass
    public static void urlbase(){
        RestAssured.baseURI = "https://api.thecatapi.com/v1/";
    }
    @Test
    public void favorita() {

        Response response =
                given()
                        .contentType("application/json")
                        .header(api, chave)
                        .body(corpoFavorita)
                        .when().post("favourites");
        String id = response.jsonPath().getString("id");
        vote_id = id;
        validacao(response);
    }
    @Test
    public void desfavorita() {     //Metodo de desfavoritar as fotos
        Response response =
                given()
                        .contentType("application/json")
                        .header(api, chave)
                        .pathParam("favourite_id", vote_id)
                        .when().delete(corpoDesfavorita);
        validacao(response);
    }
    public void validacao(Response response){
        response.then().statusCode(200).body("message", containsString("SUCCESS"));
        System.out.println("RETORNO DA API => " + response.body().asString());
        System.out.println("------------------------------------------------------------------");
    }
}
