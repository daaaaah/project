package com.example.publicwifi.controller;

import com.example.publicwifi.model.Bookmark;
import com.example.publicwifi.service.BookmarkGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookmarkGroupController {

    private final BookmarkGroupService bookmarkGroupService;

    @Autowired
    public BookmarkGroupController(BookmarkGroupService bookmarkGroupService) {
        this.bookmarkGroupService = bookmarkGroupService;
    }

    @GetMapping("/bookmark-list")
    public String getBookmarkList(Model model) {
        List<Bookmark> bookmarks = bookmarkGroupService.getAllBookmarks();
        model.addAttribute("bookmarks", bookmarks);
        return "bookmark-list";
    }

    @GetMapping("/bookmark-group-list")
    public String bookmarkGroupList(Model model) {
        model.addAttribute("groups", bookmarkGroupService.getAllBookmarkGroups());
        return "bookmark-group-list";
    }

    @GetMapping("/bookmark-add")
    public String showBookmarkAddPage() {
        return "bookmark-group-add"; //
    }

    @PostMapping("/add-bookmark-group")
    public String addBookmarkGroup(@RequestParam String name, @RequestParam int number) {
        bookmarkGroupService.addBookmarkGroup(name, number);
        return "bookmark-group-list";
    }

    @GetMapping("/delete-bookmark/{id}")
    public String showDeleteBookmarkPage(@PathVariable Long id, Model model) {
        Bookmark bookmark = bookmarkGroupService.findBookmarkById(id);
        model.addAttribute("bookmark", bookmark);
        return "delete-bookmark";
    }

    @GetMapping("/delete-bookmark-confirmed/{id}")
    public String deleteBookmark(@PathVariable Long id) {
        bookmarkGroupService.deleteBookmark(id);
        return "redirect:/bookmark-list";
    }

    @GetMapping("/delete-bookmark-group/{id}")
    public String deleteBookmarkGroup(@PathVariable Long id) {
        bookmarkGroupService.deleteBookmarkGroup(id);
        return "redirect:/bookmark-group-list";
    }


}

