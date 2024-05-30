/*
    Liang, Y.D. (2019). Introduction to Java Programming and Data Structures:
        Comprehensive Version (12th ed.). Pearson Education, Inc.
    Modified by Upadhyaya, A. (2024). CIS530-T301 Server-Side Development
    Assignment 3.2 - Controller Aspects and Navigation
 */
package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {
    private List<WishlistItem> wishList;

    public MemWishlistDao() {
        wishList = new ArrayList<>();
        wishList.add(new WishlistItem("9781338878929",
                "Harry Potter and the Sorcerer’s Stone"));
        wishList.add(new WishlistItem("9781338878936",
                "Harry Potter and the Chamber of Secrets"));
        wishList.add(new WishlistItem("9781338815283",
                "Harry Potter and the Prisoner of Azkaban"));
        wishList.add(new WishlistItem("9781338878950",
                "Harry Potter and the Goblet of Fire"));
        wishList.add(new WishlistItem("9781338299182",
                "Harry Potter and the Order of the Phoenix"));
    }

    @Override
    public List<WishlistItem> list() {
        return wishList;
    }

    @Override
    public WishlistItem find(String isbn) {
        return wishList.stream()
                .filter(wishlistItem -> wishlistItem.getIsbn().equals(isbn))
                .findFirst()
                .orElse(new WishlistItem());
    }
}
