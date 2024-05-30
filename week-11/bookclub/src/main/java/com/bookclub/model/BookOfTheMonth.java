package com.bookclub.model;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.data.annotation.Id;

public class BookOfTheMonth {

    @Id
    private String Id;
    private Integer month;

    @NotEmpty(message = "ISBN is required")
    private String isbn;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "BookOfTheMonth{" +
                "Id='" + Id + '\'' +
                ", month=" + month +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
