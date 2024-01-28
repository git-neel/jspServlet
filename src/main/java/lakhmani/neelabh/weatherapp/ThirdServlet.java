package lakhmani.neelabh.weatherapp;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

@WebServlet(name="thirdServlet",value="/third-servlet")
public class ThirdServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String city = request.getParameter("city");
        String apiKey = "d986939a345e0c2d2b534ef81bb417ec";
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        System.out.println("Connecgtion "+connection);

        InputStream inputStream = connection.getInputStream();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        System.out.println("InputStreamReader "+inputStreamReader);

        StringBuilder responseContent = new StringBuilder();
        //to get input from reader
        Scanner scanner = new Scanner(inputStreamReader);

        while(scanner.hasNext()){
            responseContent.append(scanner.nextLine());
        }
        scanner.close();
        System.out.println("Responsecontent "+responseContent);
        // Set response content type
        response.setContentType("text/html");
        request.setAttribute("responseBody", responseContent);
        request.getRequestDispatcher("Third.jsp").forward(request,response);
    }
}
