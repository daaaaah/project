package com.example.publicwifi.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookmark")
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String grname;
    private String name;

    private LocalDateTime registrationTime;

    @ManyToOne
    private WifiInfo wifiInfo;

    @ManyToOne
    @JoinColumn(name = "bookmark_group_id")
    private BookmarkGroup bookmarkGroup;

    public String getGrname() {
        return grname;
    }

    public void setGrname(String grname) {
        this.grname = grname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getgrname() {
        return grname;
    }

    public void setgrname(String grname) {
        this.grname = grname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getRegistrationTime() {
        return registrationTime;
    }

    public void setRegistrationTime(LocalDateTime registrationTime) {
        this.registrationTime = registrationTime;
    }

    public WifiInfo getWifiInfo() {
        return wifiInfo;
    }

    public void setWifiInfo(WifiInfo wifiInfo) {
        this.wifiInfo = wifiInfo;
    }

    public BookmarkGroup getBookmarkGroup() {
        return bookmarkGroup;
    }

    public void setBookmarkGroup(BookmarkGroup bookmarkGroup) {
        this.bookmarkGroup = bookmarkGroup;
    }


}