package main.java.com.habil.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.net.ssl.SSLContext;

public class HttpTutorial
{
    // HttpURLConnection
    public static void httpUrlConnectionGet() throws MalformedURLException, ProtocolException, IOException
    {
        try
        {
            URI uri = new URI("http://localhost:8080/api/employees");
            URL url = uri.toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            System.out.println("Response Code: " + status);

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null)
            {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();
            System.out.println("Response Body: \n" + content.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void httpUrlConnectionPost() throws MalformedURLException, ProtocolException, IOException
    {
        try
        {
            URL url = new URI("http://localhost:8080/api/employees").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String json = "{\"departmentId\":2,\"statusId\":2,\"jobTitleId\":1,\"jobRoleId\":1,\"employeeEmail\":\"scorpion@company.com\",\"passwordHash\":\"secureHash1917\",\"surname\":\"Scorpion\",\"firstName\":\"Over\",\"middleName\":\"H\",\"dob\":\"1999-08-21\"}";

            try (OutputStream os = conn.getOutputStream())
            {
                byte[] input = json.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int status = conn.getResponseCode() ;
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null)
            {
                response.append(line.trim());
            }
            in.close();

            System.out.println("Response code: " + status);
            System.out.println("POST Response: " + response.toString());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    // conn.setRequestMethod("PUT");
    // conn.setRequestMethod("DELETE");


    // HttpClient

    public static void httpClientGet() throws URISyntaxException, InterruptedException, IOException
    {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:8080/api/employees"))
        .GET()
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Status Code: " + response.statusCode());
        System.out.println("Body: \n" + response.body());
    }

    public static void httpClientPost() throws URISyntaxException, InterruptedException, IOException
    {
        String json = "{\"departmentId\":2,\"statusId\":2,\"jobTitleId\":1,\"jobRoleId\":1,\"employeeEmail\":\"marrie@company.com\",\"passwordHash\":\"secureHash1917\",\"surname\":\"Currie\",\"firstName\":\"Marrie\",\"middleName\":\"A\",\"dob\":\"2000-05-19\"}";
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:8080/api/employees"))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(json))
        .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("Response: " + response.body());

        // async call 
        // HttpResponse<String> response2 = client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
        // .thenApply(HttpResponse::body)
        // .thenAccept(System.out::println)
        // .join() ;

        // Downloading Files 
        // HttpResponse<Path> fileResponse = client.send(request, HttpResponse.BodyHandlers.ofFile(Paths.get("response.json")));

        // Proxy Configuration 
        // HttpClient client2 = HttpClient.newBuilder()
        // .proxy(ProxySelector.of(new InetSocketAddress("proxy.example.com", 8080)))
        // .build();

        // SSL Context 
        // SSLContext sslContext = SSLContext.getInstance("TLS");
        // HttpClient client2 = HttpClient.newBuilder()
        // .sslContext(sslContext)
        // .build();

    }

    public static void main(String[] args) throws MalformedURLException, ProtocolException, IOException, InterruptedException, URISyntaxException
    {
        httpUrlConnectionPost();
        httpClientPost();  
        httpUrlConnectionGet();
        httpClientGet(); 
    }
}
