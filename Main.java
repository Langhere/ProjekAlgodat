import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        input userInput = new input();
        interfaceProgram interfaceProgram = new interfaceProgram();
        Scanner scanner = new Scanner(System.in);

        interfaceProgram.welcomeInterface(userInput, scanner);

        while (true) {
            System.out.print("[" + userInput.getDomain() + "~" + userInput.getUsername() + "]" +
                    "[" + fileSystem.getCurrentPath() + "] ");
            String command = scanner.next();
            fileSystem.processCommand(command, scanner);
        }
    }
}
