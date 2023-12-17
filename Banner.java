public class Banner {
    public static void main(String[] args) {
        printBanner("");
        printBanner2();
    }

    public static void printBanner(String text) {
    String asciiArtString = "                                                  \n" +
        " _____     _   _     _    _____                   \n" +
        "|   | |___| |_|_|___|_|  |  _  |_ _ ___ _____ ___ \n" +
        "| | | | .'| '_| |  _| |  |     | | | .'|     | -_|\n" +
        "|_|___|__,|_,_|_|_| |_|  |__|__|_  |__,|_|_|_|___|\n" +
        "                               |___|              ";
        System.out.println(asciiArtString);
        System.out.println("Created" + text);
    }

    public static void printBanner2(){
        String customAsciiArt = "   ____ _   _  ___  ____ _______        _______ ____  \n" +
                                "  / ___| | | |/ _ \\/ ___|_   _\\ \\      / / ____| __ \\ \n" +
                                " | |  _| |_| | | | \\___ \\ | |  \\ \\ /\\ / /|  _| |  _ \\ \n" +
                                " | |_| |  _  | |_| |___) || |   \\ V  V / | |___| |_) |\n" +
                                "  \\____|_| |_|\\___/|____/ |_|    \\_/\\_/  |_____|____/ \n";
        System.out.println(customAsciiArt);
        System.out.println("23.1.1");
    }
    
}
