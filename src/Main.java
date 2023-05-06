import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Parser parser = new Parser();
        while (!input.equals("finish")) {
            input = scanner.nextLine();
            parser.parsCommand(input);
        }
    }
}