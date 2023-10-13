import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException, InterruptedException {

        String uri = "https://jsonplaceholder.typicode.com/users";

//        postNewUser(uri);
//        updateUserInfo(uri);
//        deleteUser(uri);
//        getAllUser(uri);
//        getInfoForId(uri, 3);
        getInfoForUserName();
    }

    private static void getInfoForId(String uri, int idUser) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest httpRequestGet = HttpRequest.newBuilder(new URI(uri+"/"+idUser))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClientGet = HttpClient.newHttpClient();
        HttpResponse responceGet = httpClientGet.send(httpRequestGet, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status GET - " + responceGet.statusCode());
        System.out.println("BODY GET - " + responceGet.body());

    }

    public static void getInfoForUserName() throws URISyntaxException, IOException, InterruptedException {
        String uri = "https://jsonplaceholder.typicode.com/users?username=Kamren";
        HttpRequest httpRequestGet = HttpRequest.newBuilder(new URI(uri))
                .GET()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        HttpClient httpClientGet = HttpClient.newHttpClient();
        HttpResponse responceGet = httpClientGet.send(httpRequestGet, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status GET - " + responceGet.statusCode());
        System.out.println("BODY GET - " + responceGet.body());

        }

    public static void updateUserInfo(String uri) throws IOException, URISyntaxException, InterruptedException {

        Gson gson = new GsonBuilder().create();
        User requestUser = User.builder()
                .email("update@email.com")
                .build();
        //String json = gson.toJson(requestUser);

        HttpRequest httpRequestPost = HttpRequest.newBuilder(new URI(uri+"/1"))
                .header("Content-Type", "application/json")
                .method("PUT", HttpRequest.BodyPublishers.ofString(gson.toJson(requestUser)))
                .build();

        HttpClient httpClientPost = HttpClient.newHttpClient();
        HttpResponse<String> responsePost = httpClientPost.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status PUT - " + responsePost.statusCode());

        User person = new Gson().fromJson(responsePost.body(), User.class);
        System.out.println("person = " + person);

    }
        public static void getAllUser (String uri) throws IOException, InterruptedException, URISyntaxException {

            HttpRequest httpRequestGet = HttpRequest.newBuilder(new URI(uri))
                    .GET()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            HttpClient httpClientGet = HttpClient.newHttpClient();
            HttpResponse responceGet = httpClientGet.send(httpRequestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status GET - " + responceGet.statusCode());
            System.out.println("BODY GET - " + responceGet.body());

        }

        public static void postNewUser (String uri) throws IOException, InterruptedException, URISyntaxException {
            Gson gson = new GsonBuilder().create();
            User requestUser = User.builder()
                    .username("Ugin")
                    .name("Evgen")
                    .email("q@q.com")
                    .address(new Address("Shevchenko", "Apt. 556", "Odessa", "10101", new Geo("-37.3159", "81.1496")))
                    .build();
            String json = gson.toJson(requestUser);

            HttpRequest httpRequestPost = HttpRequest.newBuilder(new URI(uri))
                    .header("Content-Type", "application/json")
                    .method("POST", HttpRequest.BodyPublishers.ofString(gson.toJson(requestUser)))
                    .build();

            HttpClient httpClientPost = HttpClient.newHttpClient();
            HttpResponse<String> responsePost = httpClientPost.send(httpRequestPost, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status POST - " + responsePost.statusCode());
            User person = new Gson().fromJson(responsePost.body(), User.class);
            System.out.println("person = " + person);
        }

        public static void deleteUser (String uri) throws IOException, InterruptedException, URISyntaxException {

            HttpRequest deleteRequest = HttpRequest.newBuilder(new URI(uri + "/1"))
                    .DELETE()
                    .build();

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpResponse<String> deleteResponce = httpClient.send(deleteRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status DELETE - " + deleteResponce.statusCode());
        }
    }
