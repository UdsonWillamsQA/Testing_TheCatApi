package test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.MassaDeDados;

public class TesteTheCatApi extends MassaDeDados{

    //Anotação junit informa que esse metodo precisa ser executado antes de todos os metodos
    @BeforeClass
    public static void urlbase(){
        RestAssured.baseURI = "https://api.thecatapi.com/v1/";
    }
    //Teste de cadastro e recebimento de chave da API do TheCatAPI
    @Test
    public void cadastro() {
        Response response = given().contentType("application/json").body(corpoCadastro).
        when().post(urlCadastro);
        validacao(response);
    }
    //Teste de votação na página do TheCatAPI
    @Test
    public void votacao() {
        Response response = given().contentType("application/json")
                .body(corpoVotacao).
                when().post("votes/");
                validacao(response);

        String id = response.jsonPath().getString("id");
        vote_id = id;
    }

    /*** @Test >>>> ESTE TESTE NÃO FUNCIONA DEVIDO A ALGUM PROBLEMA DA API UTILIZADA.
    public void deletaVoto() {
        String url = "votes/{vote_id}";
        Response response =
                given()
                .contentType("application/json")
                .header(api, chave)
                .pathParam("vote_id", vote_id)
                .when().delete(url);
                validacao(response);
    }***/
    //Teste de favoritar e desfavoritar fotos no site do TheCatAPI
    @Test
    public void favoritaDesfavorita(){
        favorita();
        desfavorita();
    }
    //Metodo para favoritar as fotos
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
    //Metodo de desfavoritar as fotos
    public void desfavorita() {

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
