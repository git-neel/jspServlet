package lakhmani.neelabh.weatherapp;

import jakarta.servlet.ServletException;
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
@WebServlet(name="secondServlet", value="/second-servlet")
public class SecondServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String city = request.getParameter("cityName");
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);
        String apiKey = "d986939a345e0c2d2b534ef81bb417ec";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + encodedCity + "&appid=" + apiKey;

        try {
            HttpRequest req = HttpRequest.newBuilder(new URI(apiUrl)).GET().build();
            HttpClient client = HttpClient.newHttpClient();
            CompletableFuture<HttpResponse<String>> future = client.sendAsync(
                    req, HttpResponse.BodyHandlers.ofString()
            );

            // Wait for the response to complete
            HttpResponse<String> httpResponse = future.join();
            int respStatus = httpResponse.statusCode();
            String respBody = httpResponse.body();

            // Set attributes before forwarding
            request.setAttribute("respBody", respBody);
            request.setAttribute("respStatus", respStatus);
            request.getSession().setAttribute("respBodyBySession", respBody);

            // Forward the request to the JSP
            //request.getRequestDispatcher("index.jsp").forward(request, response);
            // Forward the request to the JSP with query parameters
            String redirectURL = "index.jsp?respBody=" + URLEncoder.encode(respBody, StandardCharsets.UTF_8) +
                    "&respStatus=" + respStatus;
            response.sendRedirect(redirectURL);

        } catch (URISyntaxException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
