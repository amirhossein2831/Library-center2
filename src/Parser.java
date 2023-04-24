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
        System.out.println(center.addStudent(adminId,adminPass,student));

    }

    public void addStaff(String adminId, String adminPass, String id, String pass, String firstName,
                         String lastName, String nationalId, String year, String address, String type) {
        if (type.equals("staff")) {
            Staff staff = new Staff(id, pass, firstName, lastName, nationalId, year, address);
            System.out.println(center.addStaff(adminId,adminPass,staff));
            return;
        }
        Professor professor = new Professor(id, pass, firstName, lastName, nationalId, year, address);
        System.out.println(center.addProfessor(adminId, adminPass, professor));
    }

    public void addManager(String adminId,String adminPass,String id, String pass, String firstName,
                           String lastName, String nationalId, String year, String address, String libraryId) {
        Manager manager = new Manager(id, pass, firstName, lastName, nationalId, year, address, libraryId);
        System.out.println(center.addManager(adminId,adminPass,manager));

    }

    public void removeUser(String adminId, String adminPass, String id) {
        System.out.println(center.removeUser(id));
    }
}

