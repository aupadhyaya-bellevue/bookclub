/*
    Liang, Y.D. (2019). Introduction to Java Programming and Data Structures:
        Comprehensive Version (12th ed.). Pearson Education, Inc.
    Modified by Upadhyaya, A. (2024). CIS530-T301 Server-Side Development
    Assignment 3.2 - Controller Aspects and Navigation
 */
package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {

    @RequestMapping(method = RequestMethod.GET)
    public String showWishList(Model model) {
        WishlistDao wishlistDao = new MemWishlistDao();
        model.addAttribute("wishlist", wishlistDao.list());
        return "wishlist/list";
    }

    @GetMapping("/new")
    public String wishlistForm(Model model) {
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    }

    @PostMapping
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult) {
        System.out.println(wishlistItem);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            return "wishlist/new";
        }

        return "redirect:/wishlist";
    }
}
