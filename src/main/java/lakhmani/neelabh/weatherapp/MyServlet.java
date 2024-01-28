package lakhmani.neelabh.weatherapp;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@WebServlet(name="myServlet",value="/my-servlet")
public class MyServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
     String cityName = request.getParameter("userInput");
     String encodedCity = URLEncoder.encode(cityName, StandardCharsets.UTF_8);
     String apiKey="d986939a345e0c2d2b534ef81bb417ec";
     String apiUrl="https://api.openweathermap.org/data/2.5/weather?q="+encodedCity+"&appid="+apiKey;

        try {
            HttpRequest req = HttpRequest.newBuilder(new URI(apiUrl)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            CompletableFuture<HttpResponse<String>> futureResponse =
                    client.sendAsync(req,HttpResponse.BodyHandlers.ofString());
//            futureResponse.thenApply(HttpResponse::body)
//                    .thenAccept(System.out::println)
//                    .join();
            futureResponse.thenApply(HttpResponse::body)
                    .thenAccept(resp->{
            System.out.println("Response body: " + resp);
                        try {
                            PrintWriter out = response.getWriter();
                            out.println(resp);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).join();

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
