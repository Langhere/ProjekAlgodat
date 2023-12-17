import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckWebsitePaths {
    public static void checkPath(String websiteUrl, String path) {
        try {
            URL url = new URL(websiteUrl + path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Path " + path + " exists on " + websiteUrl);
                System.out.println("Vulnerable URL: " + url);
            } else {
                System.out.println("Path " + path + " does not exist on " + websiteUrl);
            }

        } catch (IOException e) {
            System.out.println("Error checking path " + path + " on " + websiteUrl + ": " + e.getMessage());
        }
    }
}
