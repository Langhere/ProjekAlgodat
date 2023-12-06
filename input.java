// input.java
import java.util.Scanner;

public class input {
    private String domain;
    private String username;

    
    public void createUser(Scanner scanner) {
        System.out.println("Enter Domain Name");
        domain = scanner.next();
        System.out.println("Enter Username");
        username = scanner.next();
    }

    public String getDomain() {
        return domain;
    }

    public  void defaultUsr(){
        domain = "guess";
        username = "guess";
        getDomain();
        getUsername();
    }

    public void setDomain(String newDomain) {
        domain = newDomain;
    }

    public void setUsername(String newUsername) {
        username = newUsername;
    }

 
    public String getUsername() {
        return username;
    }
}
