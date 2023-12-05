import java.util.Scanner;

public class interfaceProgram {
    public void welcomeInterface(input userInput, Scanner scanner) {
        int choose;
        System.out.println("Welcome To Nakiri Ayame");
        System.out.println("===LOGIN AS GUEST OR MAKE USERNAME===");
        System.out.println("[1] Login As GUEST");
        System.out.println("[2] Login As Any User");
        System.out.print(">> ");
        
        choose = scanner.nextInt();

        if (choose == 1) {
            System.out.println("Welcome Guest!");
            userInput.defaultUsr();
        } else if (choose == 2) {
            userInput.createUser(scanner);
            System.out.println("Welcome " + userInput.getDomain() + "~" + userInput.getUsername());
        }
    }
}
