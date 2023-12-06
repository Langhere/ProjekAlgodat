public class Banner {
    public static void main(String[] args) {
        printBanner("");
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
}
