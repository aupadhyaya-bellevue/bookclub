/*
    Liang, Y.D. (2019). Introduction to Java Programming and Data Structures:
        Comprehensive Version (12th ed.). Pearson Education, Inc.
    Modified by Upadhyaya, A. (2024). CIS530-T301 Server-Side Development
    Assignment 3.2 - Controller Aspects and Navigation
 */
package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MemBookDao implements BookDao {
    private  List<Book> books;

    public MemBookDao() {
        books = new ArrayList<>();
        books.add(new Book("9781338878929",
                "Harry Potter and the Sorcerer’s Stone",
                "Harry Potter #1",
                223,
                Arrays.asList("Rowling, J. K.")));

        books.add(new Book("9781338878936",
                "Harry Potter and the Chamber of Secrets",
                "Harry Potter #2",
                352,
                Arrays.asList("Rowling, J. K.")));

        books.add(new Book("9781338815283",
                "Harry Potter and the Prisoner of Azkaban",
                "Harry Potter #3",
                480,
                Arrays.asList("Rowling, J. K.")));

        books.add(new Book("9781338878950",
                "Harry Potter and the Goblet of Fire",
                "Harry Potter #4",
                752,
                Arrays.asList("Rowling, J. K.")));

        books.add(new Book("9781338299182",
                "Harry Potter and the Order of the Phoenix",
                "Harry Potter #5",
                912,
                Arrays.asList("Rowling, J. K.")));
    }

    @Override
    public List<Book> list() {
        return books;
    }

    @Override
    public Book find(String key) {
        for(Book book: books) {
            if(book.getIsbn().equals(key)) {
                return book;
            }
        }
        return new Book();
    }
}
