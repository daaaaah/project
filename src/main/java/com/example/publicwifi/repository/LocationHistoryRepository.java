package com.example.publicwifi.repository;

import com.example.publicwifi.model.LocationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import javax.xml.stream.Location;

@Repository
public interface LocationHistoryRepository extends JpaRepository<LocationHistory, Long> {
}
