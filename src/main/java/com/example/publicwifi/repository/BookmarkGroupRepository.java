package com.example.publicwifi.repository;

import com.example.publicwifi.model.Bookmark;
import com.example.publicwifi.model.BookmarkGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
}
