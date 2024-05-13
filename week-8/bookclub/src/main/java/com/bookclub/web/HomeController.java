/*
    Liang, Y.D. (2019). Introduction to Java Programming and Data Structures:
        Comprehensive Version (12th ed.). Pearson Education, Inc.
    Modified by Upadhyaya, A. (2024). CIS530-T301 Server-Side Development
    Assignment 2.2 - Controller Aspects and Navigation
 */
package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        RestBookDao bookDao = new RestBookDao();
        List<Book> books = bookDao.list();

        for (Book book: books) {
            System.out.println(book.toString());
        }

        model.addAttribute("books", books);
        return "index";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs() {
        return "about";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs() {
        return "contact";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        String isbn = id;
        System.out.println(id);

        RestBookDao bookDao = new RestBookDao();
        Book book = bookDao.find(id);

        System.out.println(book.toString());

        model.addAttribute("book", book);

        return "monthly-books/view";
    }
}
