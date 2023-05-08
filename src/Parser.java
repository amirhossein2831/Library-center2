import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

public class Parser {
    private final Center center;

    public Parser() {
        center = new Center();
    }

    public void parsCommand(String input) throws ParseException {
        String[] command = input.split("#");
        if (command.length == 1) {
            return;
        }
        String[] args = command[1].split("\\|");
        switch (command[0]) {
            case "add-library":
                addLibrary(args[0], args[1], args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
                break;
            case "add-category":
                addCategory(args[0], args[1], args[2], args[3], args[4]);
                break;
            case "add-student":
                addStudent(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
                break;
            case "add-staff":
                addStaff(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
                break;
            case "add-manager":
                addManager(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
                break;
            case "remove-user":
                removeUser(args[0], args[1], args[2]);
                break;
            case "add-book":
                addBook(args[0], args[1], args[2], args[3], args[4], args[5], args[6], Integer.parseInt(args[7]), args[8], args[9]);
                break;
            case "add-thesis":
                addThesis(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8]);
                break;
            case "add-ganjineh-book":
                addGanjine(args[0], args[1], args[2], args[3], args[4], args[5], args[6], args[7], args[8], args[9]);
                break;
            case "add-selling-book":
                addSellingBook(args[0], args[1], args[2], args[3], args[4], args[5], args[6], Integer.parseInt(args[7]), args[8], args[9], args[10], args[11]);
                break;
            case "remove-resource":
                removeResource(args[0], args[1], args[2], args[3]);
                break;
            case "borrow":
                borrow(args[0], args[1], args[2], args[3], args[4], args[5]);
                break;
            case "return":
                returning(args[0], args[1], args[2], args[3], args[4], args[5]);
                break;
            case "buy":
                buy(args[0], args[1], args[2], args[3]);
                break;
            case "read":
                read(args[0], args[1], args[2], args[3], args[4], args[5]);
                break;
            case "add-comment":
                addComment(args[0], args[1], args[2], args[3], args[4]);
                break;
            case "search":
                search(args[0]);
                break;
            case "search-user":
                searchUser(args[0], args[1], args[2]);
                break;
            case "report-penalties-sum":
                reportPenalties(args[0], args[1]);
                break;
            case "report-passed-deadline":
                reportPassedDeadLine(args[0], args[1], args[2], args[3], args[4]);
                break;

        }
    }

    public void addLibrary(String adminId, String adminPass, String id, String name, String year, int numDesk, String address) {
        Library library = new Library(id, name, year, numDesk, address);
        System.out.println(center.addLibrary(adminId, adminPass, library));
    }

    public void addCategory(String adminId, String adminPass, String id, String name, String parentId) {
        Category category = new Category(id, name, parentId);
        System.out.println(center.addCategory(adminId, adminPass, category));

    }

    public void addStudent(String adminId, String adminPass, String id, String pass, String firstName, String lastName, String nationalId, String year, String address) {
        Student student = new Student(id, pass, firstName, lastName, nationalId, year, address);
        System.out.println(center.addUser(adminId, adminPass, student));
    }

    public void addStaff(String adminId, String adminPass, String id, String pass, String firstName, String lastName, String nationalId, String year, String address, String type) {
        if (type.equals("staff")) {
            Staff staff = new Staff(id, pass, firstName, lastName, nationalId, year, address);
            System.out.println(center.addUser(adminId, adminPass, staff));
            return;
        }
        Professor professor = new Professor(id, pass, firstName, lastName, nationalId, year, address);
        System.out.println(center.addUser(adminId, adminPass, professor));
    }

    public void addManager(String adminId, String adminPass, String id, String pass, String firstName, String lastName, String nationalId, String year, String address, String libraryId) {
        Manager manager = new Manager(id, pass, firstName, lastName, nationalId, year, address, libraryId);
        System.out.println(center.addUser(adminId, adminPass, manager));

    }

    public void removeUser(String adminId, String adminPass, String id) {
        System.out.println(center.removeUser(adminId, adminPass, id));
    }

    public void addBook(String managerId, String managerPass, String id, String subject, String authorName, String publisher, String year, int number, String categoryId, String libraryId) {
        Book book = new Book(id, subject, authorName, categoryId, libraryId, publisher, number, year);
        System.out.println(center.addResource(managerId, managerPass, book));
    }

    public void addThesis(String managerId, String managerPass, String id, String subject, String authorName, String advisor, String defYear, String categoryId, String libraryId) {
        Thesis thesis = new Thesis(id, subject, authorName, categoryId, libraryId, advisor, defYear);
        System.out.println(center.addResource(managerId, managerPass, thesis));
    }

    public void addGanjine(String managerId, String managerPass, String id, String subject, String authorName, String publisher, String year, String donor, String categoryId, String libraryId) {
        GanjineBook ganjineBook = new GanjineBook(id, subject, authorName, categoryId, libraryId, publisher,year, donor);
        System.out.println(center.addResource(managerId, managerPass, ganjineBook));
    }

    public void addSellingBook(String managerId, String managerPass, String id, String subject, String authorName, String publisher, String year, int number, String price, String discount, String categoryId, String libraryId) {
        SellingBook sellingBook = new SellingBook(id, subject, authorName, categoryId, libraryId, publisher, number, year, price, discount);
        System.out.println(center.addResource(managerId, managerPass, sellingBook));
    }

    public void removeResource(String managerId, String managerPass, String resourceId, String libraryId) {
        System.out.println(center.removeResource(managerId, managerPass, resourceId, libraryId));
    }

    public void borrow(String userId, String userPass, String libraryId, String resourceId, String strDate, String hour) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " + hour);
        Borrow borrow = new Borrow(userId, resourceId, libraryId, date);
        System.out.println(center.borrow(borrow, userPass));
    }

    public void returning(String userId, String userPass, String libraryId, String resourceId, String strDate, String hour) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " + hour);
        Borrow borrow = new Borrow(userId, resourceId, libraryId, date);
        System.out.println(center.returning(borrow, userPass));
    }
    public void buy(String userId, String pass, String libraryId, String resourceId) {
        System.out.println(center.buy(userId, pass, libraryId, resourceId));
    }

    public void read(String userId, String pass, String libraryId, String resourceId, String strDate, String hour) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " + hour);
        System.out.println(center.read(userId, pass, libraryId, resourceId, date));
    }

    public void addComment(String userId,String pass,String libraryId,String bookId,String comment) {
        System.out.println(center.addComment(userId, pass, libraryId, bookId, comment));
    }

    public void search(String key) {
        System.out.println(center.search(key));
    }

    public void searchUser(String userId, String pass, String key) {
        System.out.println(center.searchUser(userId, pass, key));
    }

    public void reportPenalties(String userId, String pass) {
        System.out.println(center.reportPenalties(userId, pass));
    }

    public void reportPassedDeadLine(String userId, String pass, String libraryId, String strDate, String hour) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " + hour);
        System.out.println(center.reportPassedDeadLine(userId, pass, libraryId, date));

    }
}

