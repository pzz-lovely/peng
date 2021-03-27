package urlConnection;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @Auther lovely
 * @Create 2020-03-15 16:55
 * @PACKAGE_NAME IntelliJ IDEA
 * @Description This program connects to an URL and displays the response headers data and the first 10 lines of
 */
public class URLConnectionTest {
    public static void main(String[] args) {
        try{
            String urlName = "https://www.baidu.com/";
            URL url = new URL(urlName);
            URLConnection urlConnection = url.openConnection();

            //set username,password if specified on command line
            if (args.length > 2) {
                String username = args[0];
                String password = args[1];
                String input = username + ":" + password;
                Base64.Encoder encoder = Base64.getEncoder();
                String encoding = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
                urlConnection.setRequestProperty("Authorization", "Basic" + encoding);
            }
            urlConnection.connect();

            //print headers fields
            Map<String, List<String>> headers = urlConnection.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
                String key = entry.getKey();
                for (String value : entry.getValue()) {
                    System.out.println(key + " = " + value);
                }
            }

            //print convenience functions
            System.out.println("--------------------");
            System.out.println("getContentType : " + urlConnection.getContentType());
            System.out.println("getContentLength : " + urlConnection.getContentLength());
            System.out.println("getContentEncoding : " + urlConnection.getContentEncoding());
            System.out.println("getDate : " + urlConnection.getDate());
            System.out.println("getExpiration : " + urlConnection.getExpiration());
            System.out.println("getLastModified : " + urlConnection.getLastModified());
            System.out.println("--------------------");

            String encoding = urlConnection.getContentEncoding();
            if(encoding == null ) encoding = "utf-8";
            try (Scanner in = new Scanner(urlConnection.getInputStream())) {
                //print first ten lines of contents
                while (in.hasNextLine()) {
                    System.out.println(in.nextLine());
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
