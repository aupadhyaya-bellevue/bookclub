/**
 * Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
 *         rights reserved.
 *     Modifiled by Upadhyaya, A. 2024
 */
package com.bookclub.service.impl;

import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("bookOfTheMonthDao")
public class MongoBookOfTheMonthDao implements BookOfTheMonthDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void add(BookOfTheMonth entity) {
        mongoTemplate.save(entity);
    }

    @Override
    public void update(BookOfTheMonth entity) {
        BookOfTheMonth book = mongoTemplate.findById(entity.getId(), BookOfTheMonth.class);

        if (book != null) {
            book.setIsbn(entity.getIsbn());
            book.setMonth(entity.getMonth());
            mongoTemplate.save(book);
        }
    }

    @Override
    public boolean remove(String key) {
        BookOfTheMonth book = mongoTemplate.findById(key, BookOfTheMonth.class);
        if (book != null) {
            mongoTemplate.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public List<BookOfTheMonth> list(String key) {
        int month = Integer.parseInt(key);

        System.out.println("Month: " + month);

        if (month == 999) {
            return mongoTemplate.findAll(BookOfTheMonth.class);
        }

        Query query = new Query();

        query.addCriteria(Criteria.where("month").is(month));

        return mongoTemplate.find(query, BookOfTheMonth.class);
    }

    @Override
    public BookOfTheMonth find(String key) {
        BookOfTheMonth book = mongoTemplate.findById(key, BookOfTheMonth.class);
        return book;
    }
}
