package com.bookclub;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.impl.MongoWishlistDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MongoWishlistDaoTests {

    @Mock
    MongoTemplate mongoTemplate;

    @InjectMocks
    MongoWishlistDao mongoWishlistDao;

    @Test
    public void listWishlistItemsTest() {
        when(mongoTemplate.find(any(Query.class), eq(WishlistItem.class))).thenReturn(getMockWishlistItems());

        List<WishlistItem> wishlistItems = mongoWishlistDao.list("user");

        assertEquals(wishlistItems.size(), getMockWishlistItems().size());
    }

    @Test
    public void findWishListItemTest() {
        when(mongoTemplate.findById("123", WishlistItem.class)).thenReturn(getMockWishlistItem());

        WishlistItem wishlistItem = mongoWishlistDao.find("123");

        assertEquals(wishlistItem.getTitle(), getMockWishlistItem().getTitle());
    }

    private List<WishlistItem> getMockWishlistItems() {
        List<WishlistItem> wishlistItems = new ArrayList<>();

        wishlistItems.add(new WishlistItem("123", "Test 1", "user"));
        wishlistItems.add(new WishlistItem("124", "Test 2", "user"));

        return wishlistItems;
    }

    private WishlistItem getMockWishlistItem() {
        return new WishlistItem("123", "Test 1", "user");
    }
}
