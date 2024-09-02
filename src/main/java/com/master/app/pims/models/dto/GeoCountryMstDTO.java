package com.master.app.pims.models.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Setter
@Getter
public class GeoCountryMstDTO {
    private String countryMstGuid;
    private String masterCountry;
    private String countryMobileCode;
    private String countryCode;
    private String countryNameEn;
    private String countryNameHi;
    private String countryNameRl;
    private Date fromDate;
    private Date toDate;
    private Boolean isRecordActive;
    private String createdByGuid;
    private Date createdDate;
    private String createrIp;
    private String createrMacId;
    private String createrRemarks;
    private String modifiedByGuid;
    private Date modifiedDate;
    private String modifierIp;
    private String modifierMacId;
    private String modiferRemarks;
    private String countryMasterGuid;
}