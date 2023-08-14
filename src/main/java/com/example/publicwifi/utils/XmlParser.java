package com.example.publicwifi.utils;

import com.example.publicwifi.dto.WifiInfoDto;
import org.springframework.stereotype.Component;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

@Component
public class XmlParser {

 public List<WifiInfoDto> parse(String xmlString) {
        List<WifiInfoDto> wifiInfoDtoList = new ArrayList<>();

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xmlString));
            Document doc = builder.parse(is);
            NodeList list = doc.getElementsByTagName("row");

            // Parse elements from XML string
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    WifiInfoDto wifiInfoDto = new WifiInfoDto();

                    // Update each field of the DTO only if the corresponding XML element is not null
                    NodeList manager_noNode = element.getElementsByTagName("X_SWIFI_MGR_NO");
                    if(manager_noNode.getLength() > 0 && manager_noNode.item(0) != null) {
                        wifiInfoDto.setManager_no(manager_noNode.item(0).getTextContent());
                    }

                    NodeList ward_officeNode = element.getElementsByTagName("X_SWIFI_WRDOFC");
                    if(ward_officeNode.getLength() > 0 && ward_officeNode.item(0) != null) {
                        wifiInfoDto.setWard_office(ward_officeNode.item(0).getTextContent());
                    }

                    NodeList main_nameNode = element.getElementsByTagName("X_SWIFI_MAIN_NM");
                    if(main_nameNode.getLength() > 0 && main_nameNode.item(0) != null) {
                        wifiInfoDto.setMain_name(main_nameNode.item(0).getTextContent());
                    }

                    NodeList address1Node = element.getElementsByTagName("X_SWIFI_ADRES1");
                    if(address1Node.getLength() > 0 && address1Node.item(0) != null) {
                        wifiInfoDto.setAddress1(address1Node.item(0).getTextContent());
                    }

                    NodeList address2Node = element.getElementsByTagName("X_SWIFI_ADRES2");
                    if(address2Node.getLength() > 0 && address2Node.item(0) != null) {
                        wifiInfoDto.setAddress2(address2Node.item(0).getTextContent());
                    }

                    NodeList install_floorNode = element.getElementsByTagName("X_SWIFI_INSTL_FLOOR");
                    if(install_floorNode.getLength() > 0 && install_floorNode.item(0) != null) {
                        wifiInfoDto.setInstall_floor(install_floorNode.item(0).getTextContent());
                    }

                    NodeList install_typeNode = element.getElementsByTagName("X_SWIFI_INSTL_TY");
                    if(install_typeNode.getLength() > 0 && install_typeNode.item(0) != null) {
                        wifiInfoDto.setInstall_type(install_typeNode.item(0).getTextContent());
                    }

                    NodeList install_byNode = element.getElementsByTagName("X_SWIFI_INSTL_MBY");
                    if(install_byNode.getLength() > 0 && install_byNode.item(0) != null) {
                        wifiInfoDto.setInstall_by(install_byNode.item(0).getTextContent());
                    }

                    NodeList service_typeNode = element.getElementsByTagName("X_SWIFI_SVC_SE");
                    if(service_typeNode.getLength() > 0 && service_typeNode.item(0) != null) {
                        wifiInfoDto.setService_type(service_typeNode.item(0).getTextContent());
                    }

                    NodeList network_typeNode = element.getElementsByTagName("X_SWIFI_CMCWR");
                    if(network_typeNode.getLength() > 0 && network_typeNode.item(0) != null) {
                        wifiInfoDto.setNetwork_type(network_typeNode.item(0).getTextContent());
                    }

                    NodeList construction_yearNode = element.getElementsByTagName("X_SWIFI_CNSTC_YEAR");
                    if(construction_yearNode.getLength() > 0 && construction_yearNode.item(0) != null) {
                        wifiInfoDto.setConstruction_year(construction_yearNode.item(0).getTextContent());
                    }

                    NodeList in_out_doorNode = element.getElementsByTagName("X_SWIFI_INOUT_DOOR");
                    if(in_out_doorNode.getLength() > 0 && in_out_doorNode.item(0) != null) {
                        wifiInfoDto.setIn_out_door(in_out_doorNode.item(0).getTextContent());
                    }

                    NodeList remarksNode = element.getElementsByTagName("X_SWIFI_REMARS3");
                    if(remarksNode.getLength() > 0 && remarksNode.item(0) != null) {
                        wifiInfoDto.setRemarks(remarksNode.item(0).getTextContent());
                    }

                    NodeList latNode = element.getElementsByTagName("LAT");
                    if(latNode.getLength() > 0 && latNode.item(0) != null) {
                        wifiInfoDto.setLat(latNode.item(0).getTextContent());
                    }

                    NodeList lngNode = element.getElementsByTagName("LNT");
                    if(lngNode.getLength() > 0 && lngNode.item(0) != null) {
                        wifiInfoDto.setLng(lngNode.item(0).getTextContent());
                    }

                    NodeList work_datetimeNode = element.getElementsByTagName("WORK_DTTM");
                    if(work_datetimeNode.getLength() > 0 && work_datetimeNode.item(0) != null) {
                        wifiInfoDto.setWork_datetime(work_datetimeNode.item(0).getTextContent());
                    }

                    wifiInfoDtoList.add(wifiInfoDto);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wifiInfoDtoList;
    }
}
