import java.util.Scanner;

public class AnalyticsTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AnalyticsImplement analytics = new AnalyticsImplement();
        String fileName;

        System.out.println("Welcome to Bloomberg Text Analytics Program!");
        System.out.println("Enter name of document to be analyzed: \n");
        fileName = scanner.nextLine();

        System.out.println();
        System.out.println("Processing...");
        System.out.println("Opening file...");

        analytics.fileInputRead(fileName);
        analytics.fileAnalyticsResultWrite();

        System.out.println();
        System.out.println("Thank you for using Bloomberg Text Analytics Program!");
        System.out.println("You may view the results now.");
    }
}
