package com.example.publicwifi.controller;

import com.example.publicwifi.dto.WifiInfoDto;
import com.example.publicwifi.model.BookmarkGroup;
import com.example.publicwifi.model.LocationHistory;
import com.example.publicwifi.repository.BookmarkRepository;
import com.example.publicwifi.repository.LocationHistoryRepository;
import com.example.publicwifi.service.BookmarkGroupService;
import com.example.publicwifi.service.WifiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class WifiController {
    private final WifiService wifiService;
    private final LocationHistoryRepository locationHistoryRepository;
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkGroupService bookmarkGroupService;

    @Autowired
    public WifiController(WifiService wifiService, LocationHistoryRepository locationHistoryRepository, BookmarkGroupService bookmarkGroupService,BookmarkRepository bookmarkRepository) {
        this.wifiService = wifiService;
        this.locationHistoryRepository = locationHistoryRepository;
        this.bookmarkGroupService = bookmarkGroupService;
        this.bookmarkRepository = bookmarkRepository;
    }

    @GetMapping("/fetch-and-save-wifi-info")
    public ModelAndView fetchAndSaveWifiInfo() {
        wifiService.fetchAndSaveWifiInfo();
        return new ModelAndView("wififinish");
    }

    @GetMapping("/wifi")
    public String showWifiPage(@RequestParam(required = false) Double lat, @RequestParam(required = false) Double lng, Model model) {
        if (lat != null && lng != null) {
            List<WifiInfoDto> nearestWifi = wifiService.findNearestWifi(lat, lng);
            model.addAttribute("wifiList", nearestWifi);
        }
        return "wifi";
    }


    @PostMapping("/near")
    public String getUserLocation(@RequestParam Double lat, @RequestParam Double lng, RedirectAttributes redirectAttributes) {
        wifiService.saveUserLocation(lat, lng);
        return "redirect:/wifi?lat=" + lat + "&lng=" + lng;
    }

    @GetMapping("/location-history")
    public String showLocationHistory(Model model) {
        List<LocationHistory> locationHistory = locationHistoryRepository.findAll();
        model.addAttribute("locationHistory", locationHistory);
        return "locationhistory";
    }

    @PostMapping("/delete-location")
    public String deleteLocation(@RequestParam Long locationId) {
        locationHistoryRepository.deleteById(locationId);
        return "bookmark-list";
    }

    @GetMapping("/wifi-details/{id}")
    public String showWifiDetails(@PathVariable Long id, Model model) {
        WifiInfoDto wifiInfo = wifiService.findWifiById(id);
        List<BookmarkGroup> groups = bookmarkGroupService.getAllBookmarkGroups();
        model.addAttribute("wifi", wifiInfo);
        model.addAttribute("wifiId", wifiInfo.getId());
        model.addAttribute("groups", groups);
        return "wifi-details";
    }

    @PostMapping("/add-bookmark/{wifiInfoId}")
    public String addBookmark(@PathVariable Long wifiInfoId, @RequestParam String bookmarkGroupId, RedirectAttributes redirectAttributes) {
        bookmarkGroupService.addBookmark(wifiInfoId, bookmarkGroupId);
        redirectAttributes.addAttribute("id", wifiInfoId);
        return "redirect:/wifi-details/{id}";
    }

    @GetMapping("/edit-bookmark-group/{id}")
    public String editBookmarkGroup(@PathVariable Long id, Model model) {
        BookmarkGroup group = bookmarkGroupService.findBookmarkGroupById(id);
        model.addAttribute("group", group);
        return "bookmark-group-edit";
    }

    @PostMapping("/update-bookmark-group/{id}")
    public String updateBookmarkGroup(@PathVariable Long id, @RequestParam String name, @RequestParam int number) {
        bookmarkGroupService.updateBookmarkGroup(id, name, number);
        return "wifi";
    }
}