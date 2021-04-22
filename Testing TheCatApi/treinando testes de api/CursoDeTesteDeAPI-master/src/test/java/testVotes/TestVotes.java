package testVotes;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;
import static org.hamcrest.Matchers.containsString;

import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import resources.MassaDeDados;

import java.util.Scanner;

public class TestVotes extends MassaDeDados {


    @BeforeClass
    public static void urlbase(){
        RestAssured.baseURI = "https://api.thecatapi.com/v1/";
    }
	
	@Test
	public void votacao() {
		Response response = given().
				contentType("application/json").
				header(api, chave).
				body(corpoVotacao).
				when().
				post("votes/");
				response.then().statusCode(200).body("message", containsString("SUCCESS"));

	}

	@Test
	public void getVotes() {
		Response response = given(). //DADO
				contentType("application/json"). // TIPO DO CONTENT TYPE
				header(api, chave). // DADOS DO CABEÇALHO
				when(). //QUANDO
				get("votes/"); //ESTIVER NA PAGINA VOTES
				response.then().statusCode(200).body("image_id", containsString("4ia"));
	}

	@Test
	public void deleteVotes(){

		Response response = given().
				contentType("application/json").
				header(api, chave).
				when().delete("votes/282405");
				response.then().statusCode(200);
	}
}
