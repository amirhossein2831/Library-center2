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
                addLibrary(args[0], args[1], args[2], Integer.parseInt(args[3]), args[4]);
                break;

        }
    }

    public void addLibrary(String id, String name, String year, int numDesk, String address) {
        Library library = new Library(id, name, year, numDesk, address);
        System.out.println(center.addLibrary(library));
    }

}
