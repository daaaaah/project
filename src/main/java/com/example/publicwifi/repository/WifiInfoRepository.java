package com.example.publicwifi.repository;

import com.example.publicwifi.model.WifiInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WifiInfoRepository extends JpaRepository<WifiInfo, Long> {
}
