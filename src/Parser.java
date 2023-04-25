public class Parser {
    private Center center;

    public Parser() {
        center = new Center();
    }
    public void parsCommand(String input) {
        String[] command = input.split("#");
        String[] args = command[1].split("\\|");
        switch (command[0]) {
            case "add-library":
                addLibrary(args[0],args[1],args[2], args[3], args[4], Integer.parseInt(args[5]), args[6]);
                break;
            case "add-category":
                addCategory(args[0],args[1],args[2], args[3], args[4]);
                break;
            case "add-student":
                addStudent(args[0],args[1],args[2],args[3],args[4],args[5],args[6],args[7],args[8]);
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
                addBook(args[0], args[1], args[2], args[3], args[4], args[5], args[6],Integer.parseInt(args[7]), args[8], args[9]);
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

        }
    }

    public void addLibrary(String adminId,String adminPass,String id, String name, String year, int numDesk, String address) {
        Library library = new Library(id, name, year, numDesk, address);
        System.out.println(center.addLibrary(adminId,adminPass,library));
    }

    public void addCategory(String adminId,String adminPass,String id, String name, String parentId) {
        Category category = new Category(id, name, parentId);
        System.out.println(center.addCategory(adminId,adminPass,category));

    }

    public void addStudent(String adminId,String adminPass,String id,String pass,String firstName,String lastName,String nationalId,String year,String address) {
        Student student = new Student(id, pass, firstName, lastName, nationalId, year, address);
        System.out.println(center.addUser(adminId,adminPass,student));
    }
    public void addStaff(String adminId, String adminPass, String id, String pass, String firstName,
                         String lastName, String nationalId, String year, String address, String type) {
        if (type.equals("staff")) {
            Staff staff = new Staff(id, pass, firstName, lastName, nationalId, year, address);
            System.out.println(center.addUser(adminId,adminPass,staff));
            return;
        }
        Professor professor = new Professor(id, pass, firstName, lastName, nationalId, year, address);
        System.out.println(center.addUser(adminId, adminPass, professor));
    }

    public void addManager(String adminId,String adminPass,String id, String pass, String firstName,
                           String lastName, String nationalId, String year, String address, String libraryId) {
        Manager manager = new Manager(id, pass, firstName, lastName, nationalId, year, address, libraryId);
        System.out.println(center.addUser(adminId,adminPass,manager));

    }
    public void removeUser(String adminId, String adminPass, String id) {
        System.out.println(center.removeUser(adminId, adminPass, id));
    }

    public void addBook(String managerId,String managerPass,String id,String subject,String authorName,String publisher,String year,int number,
                        String categoryId,String libraryId) {
        Book book = new Book(id, subject, authorName, categoryId, libraryId, publisher, number, year);
        System.out.println(center.addResource(managerId, managerPass, book));
    }

    public void addThesis(String managerId, String managerPass, String id, String subject, String authorName, String advisor, String defYear
            , String categoryId, String libraryId) {
        Thesis thesis = new Thesis(id, subject, authorName, categoryId, libraryId, advisor, defYear);
        System.out.println(center.addResource(managerId, managerPass, thesis));
    }
    public void addGanjine(String managerId,String managerPass,String id,String subject,String authorName,String publisher,String year,
                           String donor,String categoryId,String libraryId) {
        GanjineBook ganjineBook = new GanjineBook(id, subject, authorName, categoryId, libraryId, publisher, donor);
        System.out.println(center.addResource(managerId, managerPass, ganjineBook));
    }

    public void addSellingBook(String managerId, String managerPass, String id, String subject, String authorName, String publisher, String year, int number,
             String price, String discount, String categoryId, String libraryId) {
        SellingBook sellingBook = new SellingBook(id, subject, authorName, categoryId, libraryId, publisher, number, year, price, discount);
        System.out.println(center.addResource(managerId, managerPass, sellingBook));
    }
}

