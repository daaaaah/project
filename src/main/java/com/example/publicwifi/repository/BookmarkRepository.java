package com.example.publicwifi.repository;

import com.example.publicwifi.model.Bookmark;
import com.example.publicwifi.model.BookmarkGroup;
import com.example.publicwifi.model.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long>{
    Optional<Bookmark> findById(Long id);
    void deleteByBookmarkGroup(BookmarkGroup bookmarkGroup);
}
