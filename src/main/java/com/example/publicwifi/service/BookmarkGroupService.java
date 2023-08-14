package com.example.publicwifi.service;

import com.example.publicwifi.model.Bookmark;
import com.example.publicwifi.model.BookmarkGroup;
import com.example.publicwifi.model.WifiInfo;
import com.example.publicwifi.repository.BookmarkRepository;
import com.example.publicwifi.repository.BookmarkGroupRepository;
import com.example.publicwifi.repository.WifiInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookmarkGroupService {
    private final BookmarkGroupRepository bookmarkGroupRepository;
    private final BookmarkRepository bookmarkRepository;
    private final WifiInfoRepository wifiInfoRepository;

    @Autowired
    public BookmarkGroupService(WifiInfoRepository wifiInfoRepository, BookmarkGroupRepository bookmarkGroupRepository, BookmarkRepository bookmarkRepository) {
        this.wifiInfoRepository = wifiInfoRepository;
        this.bookmarkGroupRepository = bookmarkGroupRepository;
        this.bookmarkRepository  = bookmarkRepository;
    }

    public void addBookmarkGroup(String name, int number) {
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.setName(name);
        bookmarkGroup.setNumber(number);
        bookmarkGroup.setRegistrationDate(LocalDateTime.now());

        bookmarkGroupRepository.save(bookmarkGroup);
    }

    public List<BookmarkGroup> getAllBookmarkGroups() {
        return bookmarkGroupRepository.findAll();
    }


    public void addBookmark(Long wifiInfoId,String bookmarkGroupId) {
        WifiInfo wifiInfo = wifiInfoRepository.findById(wifiInfoId).orElseThrow(() -> new EntityNotFoundException("WifiInfo not found"));
        Bookmark bookmark = new Bookmark();
        bookmark.setgrname(bookmarkGroupId);
        bookmark.setName(wifiInfo.getMain_name());
        bookmark.setRegistrationTime(LocalDateTime.now());
        bookmarkRepository.save(bookmark);
    }


    public List<Bookmark> getAllBookmarks() {
        return bookmarkRepository.findAll(); // 예상 반환 타입: List<Bookmark>
    }

    public BookmarkGroup findBookmarkGroupById(Long id) {
        return bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new BookmarkGroupNotFoundException("Bookmark Group not found"));
    }

    public Bookmark findBookmarkById(Long id) {
        return bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException("북마크를 찾을 수 없습니다. ID: " + id));
    }

    public void deleteBookmark(Long id) {
        bookmarkRepository.deleteById(id);
    }

    @Transactional
    public void deleteBookmarkGroup(Long id) {
        BookmarkGroup bookmarkGroup = bookmarkGroupRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("BookmarkGroup not found with id: " + id));

        bookmarkRepository.deleteByBookmarkGroup(bookmarkGroup);

        bookmarkGroupRepository.deleteById(id);
    }

    public class BookmarkNotFoundException extends RuntimeException {
        public BookmarkNotFoundException(String message) {
            super(message);
        }
    }

    public void updateBookmarkGroup(Long id, String name, int number) {
        BookmarkGroup group = bookmarkGroupRepository.findById(id).orElseThrow(() -> new BookmarkGroupNotFoundException("Bookmark Group not found"));
        group.setName(name);
        group.setNumber(number);
        group.setEditDate(LocalDateTime.now());
        bookmarkGroupRepository.save(group);
    }
    public class BookmarkGroupNotFoundException extends RuntimeException {
        public BookmarkGroupNotFoundException(String message) {
            super(message);
        }
    }

}