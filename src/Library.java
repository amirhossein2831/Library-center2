import java.util.*;

public class Library {
    private final String id;
    private final String name;
    private final String year;
    private final int numDesk;
    private final String address;
    private final HashMap<String, Resource> resources;
    private HashMap<String, ArrayList<Borrow>> borrows;

    public Library(String id, String name, String year, int numDesk, String address) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.numDesk = numDesk;
        this.address = address;
        resources = new HashMap<>();
        borrows = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getYear() {
        return year;
    }

    public int getNumDesk() {
        return numDesk;
    }

    public String getAddress() {
        return address;
    }


    public HashMap<String, Resource> getResources() {
        return resources;
    }

    public HashMap<String, ArrayList<Borrow>> getBorrows() {
        return borrows;
    }

    public Resource getResource(String resourceId) {
        return resources.get(resourceId);
    }

    public int countBorrow(String userId) {                 //all borrows by a user
        int count = 0;
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(this.borrows.values())) {
            for (Borrow borrow : borrows1) {
                if (borrow.getUserId().equals(userId)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isBorrowedByUser(String userId, String resourceId) {     //check that user get this resource or not
        ArrayList<Borrow> myBorrow = borrows.get(resourceId);
        if (myBorrow == null) {
            return false;
        }
        for (Borrow borrow : myBorrow) {
            if (borrow.getUserId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    private int countBorrowedResources(String resourceId) {              //all borrows of a resource
        ArrayList<Borrow> myBorrow = borrows.get(resourceId);
        if (myBorrow == null) {
            return 0;
        }
        return myBorrow.size();
    }

    public boolean borrow(Borrow borrow, int userBorrows, User user, Resource resource) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getResourceId());
        if (borrows1 == null) {
            borrows1 = new ArrayList<>();
        }
        if (resource instanceof GanjineBook || resource instanceof SellingBook) {
            return false;                                               //if resource is a ganjineh or selling book  ok
        }
        if (countBorrowedResources(borrow.getResourceId()) >= resource.getNumber()) {
            return false;                                               // all copy is borrowed ok
        }
        if (isBorrowedByUser(borrow.getUserId(), borrow.getResourceId())) {
            return false;                                                   // user get this resource already ok
        }
        if (user instanceof Student) {
            if (userBorrows < 3) {                                    //check able to get more or not ok
                borrows1.add(borrow);
                borrows.put(borrow.getResourceId(), borrows1);
                resource.decreaseRealNum();
                resource.borrowed();
                return true;
            }
            return false;
        } else if (user instanceof Staff || user instanceof Professor) {
            if (userBorrows < 5) {                                       //check able to get more or not ok
                borrows1.add(borrow);
                borrows.put(borrow.getResourceId(), borrows1);
                resource.decreaseRealNum();
                resource.borrowed();
                return true;
            }
            return false;
        }
        return false;                                       //the user in not student or staff
    }

    private int checkDebt(Borrow borrow, Date returnTime, Resource resource, User user,boolean check) {
        long firstMin = borrow.getDate().getTime() / 3600000;
        long secondMin = returnTime.getTime() / 3600000;
        long periodTime = secondMin - firstMin;
        if (check) {
            resource.setDayOfBorrowed((int)Math.ceil(periodTime / 24.0));
        }
        if (user instanceof Student) {
            if (resource instanceof Book) {
                if (periodTime < (10 * 24)) {
                    return 0;
                }
                return (int) ((periodTime - (10 * 24)) * 50);
            }
            if (periodTime < (7 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (7 * 24)) * 50);
        }
        if (resource instanceof Book) {
            if (periodTime < (14 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (14 * 24)) * 100);
        }
        if (periodTime < (10 * 24)) {
            return 0;
        }
        return (int) ((periodTime - (10 * 24)) * 100);
    }

    public boolean hasDelay(Borrow borrow1, Resource resource, User user, String userId) {     //check that user get this resource or not
        int x = 0;
        for (ArrayList<Borrow> myBorrow : borrows.values()) {
            if (myBorrow == null) {
                return false;
            }
            for (Borrow borrow : myBorrow) {
                if (borrow.getUserId().equals(userId)) {
                    x += checkDebt(borrow, borrow1.getDate(), resource, user, false);
                }
            }
        }
        return x > 0;
    }

    public HashSet<String> hasDelay(Date date, HashMap<String, User> users) {     //check that user get this resource or not
        HashSet<String> values = new HashSet<>();
        for (ArrayList<Borrow> myBorrow : borrows.values()) {
            if (myBorrow == null) {
                return null;
            }
            for (Borrow borrow : myBorrow) {
                User user = users.get(borrow.getUserId());
                Resource resource = resources.get(borrow.getResourceId());
                if (checkDebt(borrow, date, resource, user,false) > 0) {
                    values.add(borrow.getResourceId());
                }
            }
        }

        if (values.size() == 0) {
            return null;
        }
        return values;
    }

    public int returning(Borrow borrow, Resource resource, User user) {
        ArrayList<Borrow> borrows = this.borrows.get(borrow.getResourceId());
        Borrow itsBorrow = null;
        if (borrows == null) {
            return -1;
        }
        for (Borrow hold : borrows) {
            if (hold.getUserId().equals(borrow.getUserId())) {
                itsBorrow = hold;
            }
        }
        if (itsBorrow == null) {
            return -1;
        }
        int debt = checkDebt(itsBorrow, borrow.getDate(), resource, user,true);
        user.setDebt(debt);
        borrows.remove(itsBorrow);
        resource.increaseRealNumber();
        return debt;
    }
    public StringBuilder reportPassedDeadLine(Date date, HashMap<String, User> users) {
        HashSet<String> values = hasDelay(date, users);
        if (values == null) {
            return new StringBuilder("none");
        }
        ArrayList<String> hold = new ArrayList<>(values);
        StringBuilder str = new StringBuilder();
        Collections.sort(hold);
        for (String i : hold) {
            str.append(i);
            str.append("|");
        }
        if (str.length() != 0) {
            str.deleteCharAt(str.length() - 1);
        }
        return str;
    }
    public String libraryReport() {
        int bookNum = 0, thesisNum = 0, ganjineNum = 0, sellingBookNum = 0, borrowedBook = 0, borrowedThesis = 0;
        for (Resource resource : resources.values()) {
            if (resource instanceof Thesis) {
                thesisNum+=resource.getRealNum();
            } else if (resource instanceof GanjineBook) {
                ganjineNum+=resource.getRealNum();
            } else if (resource instanceof SellingBook) {
                sellingBookNum += resource.getRealNum();
            } else if (resource instanceof Book) {
                bookNum += resource.getRealNum();
            }
        }
        for (String resourceId : borrows.keySet()) {
            Resource resource = resources.get(resourceId);
            if (resource instanceof Book) {
                borrowedBook += resource.getNumber() - resource.getRealNum();
            } else if (resource instanceof Thesis) {
                borrowedThesis+=resource.getNumber() - resource.getRealNum();
            }
        }
        return "" + bookNum + " " + thesisNum + " " + borrowedBook + " " + borrowedThesis + " " + ganjineNum + " " + sellingBookNum;
    }

    public int[] categoryReport(HashMap<String, Category> categories, String categoryId) {
        int[] hold = {0, 0, 0, 0};
        if (categoryId.equals("null")) {
            for (Resource resource : resources.values()) {
                {
                    if (resource.getCategoryId().equals("null"))
                        if (resource instanceof Thesis) {
                            hold[0]+=resource.getRealNum();
                        } else if (resource instanceof GanjineBook) {
                            hold[1]+=resource.getRealNum();
                        } else if (resource instanceof SellingBook) {
                            hold[2] += resource.getRealNum();
                        } else if (resource instanceof Book) {
                            hold[3] += resource.getRealNum();
                        }
                }
            }
            return hold;
        }
        Category category = categories.get(categoryId);
        if (category == null) {
            return null;
        }

        for (Resource resource : resources.values()) {
            {
                if (resource.getCategoryId().equals(categoryId))
                    if (resource instanceof Thesis) {
                        hold[0]+= resource.getRealNum();
                    } else if (resource instanceof GanjineBook) {
                        hold[1] += resource.getRealNum();
                    } else if (resource instanceof SellingBook) {
                        hold[2] += resource.getRealNum();
                    } else if (resource instanceof Book) {
                        hold[3] += resource.getRealNum();
                    }
            }
        }

        for (String sub : category.getSubs()) {
            int[] temp = categoryReport(categories, sub);
            hold[0] += temp[0];
            hold[1] += temp[1];
            hold[2] += temp[2];
            hold[3] += temp[3];
        }
        return hold;
    }

    public String reportMostPopular() {
        int x = 0;
        int y = 0;
        Resource holdBook = null;
        Resource holdThesis = null;
        for (Resource resource : resources.values()) {
            if (resource instanceof Book) {
                if (resource.getNumOfBorrowed() >= x) {
                    x = resource.getNumOfBorrowed();
                    holdBook = resource;
                }
            } else if (resource instanceof Thesis) {

                if (resource.getNumOfBorrowed() >= y) {
                    y = resource.getNumOfBorrowed();
                    holdThesis = resource;
                }
            }
        }
        for (Resource resource : resources.values()) {
            if (resource instanceof Book) {
                if (resource.getNumOfBorrowed() == holdBook.getNumOfBorrowed()) {
                    if (resource.getDayOfBorrowed() > holdBook.getDayOfBorrowed()) {
                        holdBook = resource;
                    }
                }
            } else if (resource instanceof Thesis) {
                if (resource.getNumOfBorrowed() == holdThesis.getNumOfBorrowed()) {
                    if (resource.getDayOfBorrowed() > holdThesis.getDayOfBorrowed()) {
                        holdThesis = resource;
                    }
                }
            }
        }
        if (holdBook.getNumOfBorrowed() == 0) {
            return "null" + "\n"
                    + holdThesis.getId() + " " + holdThesis.getNumOfBorrowed() + " " + holdThesis.getDayOfBorrowed();
        }
        if (holdThesis.getNumOfBorrowed() == 0) {
            return holdBook.getId() + " " + holdBook.getNumOfBorrowed() + " " + holdBook.getDayOfBorrowed() + "\n"
                    + "null";
        }
        return holdBook.getId() + " " + holdBook.getNumOfBorrowed() + " " + holdBook.getDayOfBorrowed() + "\n"
                + holdThesis.getId() + " " + holdThesis.getNumOfBorrowed() + " " + holdThesis.getDayOfBorrowed();
    }
}
