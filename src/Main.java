import java.text.ParseException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        Parser parser = new Parser();
        while (!input.equals("finish")) {
            input = scanner.nextLine();
            try {
                parser.parsCommand(input);
            } catch (NotFoundException e) {
                System.out.println(e.getMessage());
            } catch (InvalidPassException e) {
                System.out.println(e.getMessage());
            } catch (PermissionDeniedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}