package resources;
public class MassaDeDados {
    //API
    protected String api = "x-api-key";
    // Chave
    protected String chave = "ab2264f6-f8a1-4b21-b6b8-6bc72fe47c8d";
    //Cadastro
    protected String vote_id;
    protected String urlCadastro = "user/passwordlesssignup";
    protected String corpoCadastro = "{\"email\": \"teste@gmail.com\", \"appDescription\": \"teste the cat api\"}";
    //Votação
    protected String corpoVotacao = "{\"image_id\":\"d01\",\"value\":true,\"sub_id\":\"demo-d754d8\"}";
    //Favorita
    protected String corpoFavorita = "{\"image_id\": \"H0T5RQVp0\"}";
    //Desfavorita
    protected String corpoDesfavorita = "favourites/{favourite_id}";

}
