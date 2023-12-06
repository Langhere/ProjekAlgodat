import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();
        Banner banner = new Banner();
        banner.printBanner(" M1kasha");
        input userInput = new input();
        interfaceProgram interfaceProgram = new interfaceProgram();
        Scanner scanner = new Scanner(System.in);

        interfaceProgram.welcomeInterface(userInput, scanner);
                // Membuat objek Banner

            while (true) {
            System.out.print("[" + userInput.getDomain() + " ~ " + userInput.getUsername() + "]" +
                    "[" + fileSystem.getCurrentPath() + "] ");
            String command = scanner.next();
            fileSystem.processCommand(command, scanner);
        }

    }
}
