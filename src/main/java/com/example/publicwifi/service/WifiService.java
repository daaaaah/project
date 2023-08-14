package com.example.publicwifi.service;

import com.example.publicwifi.dto.WifiInfoDto;
import com.example.publicwifi.model.Bookmark;
import com.example.publicwifi.model.BookmarkGroup;
import com.example.publicwifi.model.LocationHistory;
import com.example.publicwifi.model.WifiInfo;
import com.example.publicwifi.repository.BookmarkGroupRepository;
import com.example.publicwifi.repository.LocationHistoryRepository;
import com.example.publicwifi.repository.WifiInfoRepository;
import com.example.publicwifi.utils.XmlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class WifiService {
    private static final String SERVICE_NAME = "TbPublicWifiInfo";
    private static final String API_URL = "http://openapi.seoul.go.kr:8088/665465787164613036327967416374/xml/" + SERVICE_NAME + "/";
    private static final int PAGE_SIZE = 1000;

    private final WifiInfoRepository wifiInfoRepository;
    private final LocationHistoryRepository locationHistoryRepository;
    private final BookmarkGroupRepository bookmarkGroupRepository;


    @Autowired
    private XmlParser xmlParser;



    @Autowired
    public WifiService(WifiInfoRepository wifiInfoRepository, LocationHistoryRepository locationHistoryRepository, BookmarkGroupRepository bookmarkGroupRepository) {
        this.wifiInfoRepository = wifiInfoRepository;
        this.locationHistoryRepository = locationHistoryRepository;
        this.bookmarkGroupRepository = bookmarkGroupRepository;
    }


    public WifiInfoDto findWifiById(Long id) {
        WifiInfo wifiInfo = wifiInfoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("WiFi entry not found for ID: " + id));
        return new WifiInfoDto(wifiInfo, 0);
    }

    public List<WifiInfoDto> findNearestWifi(Double lat, Double lng) {
        List<WifiInfo> allWifi = wifiInfoRepository.findAll();

        List<WifiInfoDto> sortedWifiInfo = allWifi.stream()
                .map(info -> {
                    double distance = calculateDistance(lat, lng, Double.parseDouble(info.getLat()), Double.parseDouble(info.getLng()));
                    return new WifiInfoDto(info, distance);
                })
                .sorted(Comparator.comparingDouble(WifiInfoDto::getDistance))
                .limit(20)
                .collect(Collectors.toList());

        return sortedWifiInfo;
    }


    public void saveUserLocation(Double lat, Double lng) {
        LocationHistory locationHistory = new LocationHistory();
        locationHistory.setLat(lat);
        locationHistory.setLng(lng);
        locationHistoryRepository.save(locationHistory);
    }


    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }


    public void fetchAndSaveWifiInfo() {
        RestTemplate restTemplate = new RestTemplate();

        int startIndex = 1;
        int endIndex = PAGE_SIZE;

        while (startIndex <= 25000) {
            String result = restTemplate.getForObject(API_URL + startIndex + "/" + endIndex, String.class);

            List<WifiInfoDto> wifiInfoDtoList = xmlParser.parse(result);

            for (WifiInfoDto wifiInfoDto : wifiInfoDtoList) {
                WifiInfo wifiInfo = new WifiInfo();
                wifiInfo.setManager_no(wifiInfoDto.getManager_no());
                wifiInfo.setWard_office(wifiInfoDto.getWard_office());
                wifiInfo.setMain_name(wifiInfoDto.getMain_name());
                wifiInfo.setAddress1(wifiInfoDto.getAddress1());
                wifiInfo.setAddress2(wifiInfoDto.getAddress2());
                wifiInfo.setInstall_floor(wifiInfoDto.getInstall_floor());
                wifiInfo.setInstall_type(wifiInfoDto.getInstall_type());
                wifiInfo.setInstall_by(wifiInfoDto.getInstall_by());
                wifiInfo.setService_type(wifiInfoDto.getService_type());
                wifiInfo.setNetwork_type(wifiInfoDto.getNetwork_type());
                wifiInfo.setConstruction_year(wifiInfoDto.getConstruction_year());
                wifiInfo.setIn_out_door(wifiInfoDto.getIn_out_door());
                wifiInfo.setRemarks(wifiInfoDto.getRemarks());
                wifiInfo.setLat(wifiInfoDto.getLat());
                wifiInfo.setLng(wifiInfoDto.getLng());
                wifiInfo.setWork_datetime(wifiInfoDto.getWork_datetime());

                wifiInfoRepository.save(wifiInfo);

            }
            startIndex += PAGE_SIZE;
            endIndex += PAGE_SIZE;
        }
    }

}