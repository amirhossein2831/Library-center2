package com.Library.Controller;

import com.Library.Models.Borrow;
import com.Library.Models.Interface.Buy;
import com.Library.Models.Interface.Comment;
import com.Library.Models.Interface.Read;
import com.Library.Models.Library;
import com.Library.Models.LibraryCenter;
import com.Library.Models.Resources.GanjineBook;
import com.Library.Models.Resources.Resource;
import com.Library.Models.Resources.SellingBook;
import com.Library.Models.Users.Manager;
import com.Library.Models.Users.Professor;
import com.Library.Models.Users.Student;
import com.Library.Models.Users.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FeatureController extends BaseController {
    public String borrow(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        Borrow borrow = new Borrow(args[0], args[3], args[2], date);
        User user = LibraryCenter.getUsers().get(args[0]);
        Library library = LibraryCenter.getLibraries().get(args[2]);
        Resource resource = library.getResource(args[3]);
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        if (checkDelay(borrow, resource, user)) {
            return "not-allowed";
        }
        if (!library.borrow(borrow, countBorrow(borrow.getUserId()), user, resource)) {
            return "not-allowed";
        }
        return "success";
    }

    public String returning(String[] args) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        User user = LibraryCenter.getUsers().get(args[0]);
        Library library = LibraryCenter.getLibraries().get(args[2]);
        Resource resource = library.getResource(args[3]);
        Borrow borrow = new Borrow(args[0], args[3], args[2], date);
        int hold = library.returning(borrow, resource, user);
        if (hold < 0) {
            return "not-found";
        } else if (hold > 0) {
            return "" + hold;
        }
        return "success";
    }

    public String buy(String[] args) {
        User user = LibraryCenter.getUsers().get(args[0]);
        Library library = LibraryCenter.getLibraries().get(args[2]);
        Resource resource = library.getResource(args[3]);
        if (!(resource instanceof SellingBook)) {
            return "not-allowed";
        }
        if (user instanceof Manager) {
            return "permission-denied";
        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        if (resource.getRealNum() == 0) {
            return "not-allowed";
        }
        Buy action = (Buy) user;
        action.buy((SellingBook) resource);
        resource.decreaseRealNum();
        return "success";
    }

    public String read(String[] args) throws ParseException {
        User user = LibraryCenter.getUsers().get(args[0]);
        Library library = LibraryCenter.getLibraries().get(args[2]);
        Resource resource = library.getResource(args[3]);
        Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(args[4] + " " + args[5]);
        if (!(resource instanceof GanjineBook)) {
            return "not-allowed";
        }
        if (!(user instanceof Professor)) {
            return "permission-denied";
        }
        if (user.getDebt() != 0) {
            return "not-allowed";
        }
        Read action = (Read) user;
        if (!(action.read((GanjineBook) resource, date))) {
            return "not-allowed";
        }
        return "success";
    }

    public String addComment(String[] args) {
        User user = LibraryCenter.getUsers().get(args[0]);
        Library library = LibraryCenter.getLibraries().get(args[2]);
        Resource resource = library.getResource(args[3]);
        if (user instanceof Manager) {
            return "permission-denied";
        }
        if (!(user instanceof Student || user instanceof Professor)) {
            return "permission-denied";
        }
        Comment com = (Comment) user;
        com.addComment(args[4], resource);
        return "success";
    }

    private boolean checkDelay(Borrow borrow, Resource resource, User user) {
        for (Library library : LibraryCenter.getLibraries().values()) {
            if (library.hasDelay(borrow, resource, user, borrow.getUserId())) {
                return true;
            }
        }
        return false;
    }

}
