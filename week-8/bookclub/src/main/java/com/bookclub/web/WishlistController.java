/*
    Liang, Y.D. (2019). Introduction to Java Programming and Data Structures:
        Comprehensive Version (12th ed.). Pearson Education, Inc.
    Modified by Upadhyaya, A. (2024). CIS530-T301 Server-Side Development
    Assignment 3.2 - Controller Aspects and Navigation
 */
package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    WishlistDao wishlistDao = new MongoWishlistDao();

    @Autowired
    public void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showWishList(Model model) {
        return "wishlist/list";
    }

    @GetMapping("/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @PostMapping
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "wishlist/new";
        }

        wishlistItem.setUsername(authentication.getName());
        wishlistDao.add(wishlistItem);
        return "redirect:/wishlist";
    }

    @PostMapping("/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "wishlist/view";
        }
        wishlistItem.setUsername(authentication.getName());
        wishlistDao.update(wishlistItem);
        return "redirect:/wishlist";
    }

    @GetMapping("/remove/{id}")
    public String removeWishlistItem(@PathVariable String id) {
        wishlistDao.remove(id);

        return "redirect:/wishlist";
    }

    @GetMapping("/{id}")
    public String showWishListItem(@PathVariable String id, Model model) {
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "wishlist/view";
    }
}
