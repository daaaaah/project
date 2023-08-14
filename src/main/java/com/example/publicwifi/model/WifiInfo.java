package com.example.publicwifi.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "WifiInfo")
public class WifiInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String Distance;
    private String Manager_no;
    private String Ward_office;
    private String Main_name;
    private String Address1;
    private String Address2;
    private String Install_floor;
    private String Install_type;
    private String Install_by;
    private String Service_type;
    private String Network_type;
    private String Construction_year;
    private String In_out_door;
    private String Remarks;
    private String Lat;
    private String Lng;
    private String Work_datetime;

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }



    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getManager_no() {
        return Manager_no;
    }

    public void setManager_no(String Manager_no) {
        this.Manager_no = Manager_no;
    }

    public String getWard_office() {
        return Ward_office;
    }

    public void setWard_office(String Ward_office) {
        this.Ward_office = Ward_office;
    }

    public String getMain_name() {
        return Main_name;
    }

    public void setMain_name(String Main_name) {
        this.Main_name = Main_name;
    }

    public String getAddress1() {
        return Address1;
    }

    public void setAddress1(String Address1) {
        this.Address1 = Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public void setAddress2(String Address2) {
        this.Address2 = Address2;
    }

    public String getInstall_floor() {
        return Install_floor;
    }

    public void setInstall_floor(String Install_floor) {
        this.Install_floor = Install_floor;
    }

    public String getInstall_type() {
        return Install_type;
    }

    public void setInstall_type(String Install_type) {
        this.Install_type = Install_type;
    }

    public String getInstall_by() {
        return Install_by;
    }

    public void setInstall_by(String Install_by) {
        this.Install_by = Install_by;
    }

    public String getService_type() {
        return Service_type;
    }

    public void setService_type(String Service_type) {
        this.Service_type = Service_type;
    }

    public String getNetwork_type() {
        return Network_type;
    }

    public void setNetwork_type(String Network_type) {
        this.Network_type = Network_type;
    }

    public String getConstruction_year() {
        return Construction_year;
    }

    public void setConstruction_year(String Construction_year) {
        this.Construction_year = Construction_year;
    }

    public String getIn_out_door() {
        return In_out_door;
    }

    public void setIn_out_door(String In_out_door) {
        this.In_out_door = In_out_door;
    }

    public String getRemarks() {
        return Remarks;
    }

    public void setRemarks(String Remarks) {
        this.Remarks = Remarks;
    }

    public String getLat() {
        return Lat;
    }

    public void setLat(String Lat) {
        this.Lat = Lat;
    }

    public String getLng() {
        return Lng;
    }

    public void setLng(String Lng) {
        this.Lng = Lng;
    }

    public String getWork_datetime() {
        return Work_datetime;
    }

    public void setWork_datetime(String Work_datetime) {
        this.Work_datetime = Work_datetime;
    }

}
