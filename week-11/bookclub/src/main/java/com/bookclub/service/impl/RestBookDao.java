/*
    Krasso, R., (2021). CIS 505 Intermediate Java Programming. Bellevue University, all
        rights reserved.
    Modifiled by Upadhyaya, A. 2024
 */
package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestBookDao implements BookDao {

    private static final String OPEN_LIBRARY_URL = "https://openlibrary.org/api/books";

    public RestBookDao() {
    }

    @Override
    public List<Book> list(String key) {
        Object doc = getBooksDoc(key);

        List<Book> books = new ArrayList<>();

        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");

        for (int index = 0; index < titles.size(); index++) {
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }

        return books;
    }

    @Override
    public Book find(String key) {
        Object docs = getBooksDoc(key);

        List<String> titles = JsonPath.read(docs, "$..title");
        List<String> isbns = JsonPath.read(docs, "$..bib_key");
        List<String> infoUrls = JsonPath.read(docs, "$..info_url");
        List<String> subtitles = JsonPath.read(docs, "$..details.subtitle");
        List<Integer> pages = JsonPath.read(docs, "$..details.number_of_pages");

        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        String subtitle = subtitles.size() > 0 ? subtitles.get(0) : "N/A";
        int numberOfPages = pages.size() > 0 ? pages.get(0) : 0;

        return new Book(isbn, title, subtitle, infoUrl, numberOfPages);
    }

    private Object getBooksDoc(String isbnString) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(OPEN_LIBRARY_URL)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        System.out.println(responseEntity.getBody());

        return Configuration.defaultConfiguration().jsonProvider().parse(responseEntity.getBody());
    }
}
