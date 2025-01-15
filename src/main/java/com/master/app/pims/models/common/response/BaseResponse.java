package com.master.app.pims.models.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgUnit;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.*;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    boolean status;
    String message;
    private Integer totalDataCount;
    private List<GeoCountryMst> data;
    private List<GeoCountryMaster> masterCountry;
    private List<GeoStateMaster> masterState;
    private List<GeoColonyCategory> colonyCategory;
    private List<ApplicationMaster> applicationMaster;
    private List<AssessmentYear> assessmentYear;
    private List<AssociatedChargesInfo> associatedChargesInfo;
    private List<DocsSubmissionInfo> docsSubmissionInfo;
    private List<RequestSubmissionType> requestSubmissionType;
    private List<SubmittedRequestStage> submittedRequestStage;
    private List<UnitArea> unitArea;
    private List<MstChargeDetails> mstChargeDetails;
    private List<OccupationType> occupationType;
    private List<EducationLevel> educationLevel;
    private List<ReligiousPlaces> religiousPlaces;
    private List<DocsCategoryInfo> docsCategoryInfo;
    private List<CommonMasterProcessStatus> commonMasterProcessStatus;
    private List<SmsEmailTemplate> smsEmailTemplate;
    private List<OrgPrimary> orgPrimary;
    private List<OrgWrapper> orgWrapper;
    private List<GeoZoneMCD> geoZoneMCD;
    private List<OrgRadius> orgRadius;
    private List<OrgUnit> orgUnit;
    private List<RefDocsCategoryMap> refDocsCategoryMap;
    private List<RefUserDocsMap> refUserDocsMap;
    private List<GeoWardView> geoWardViews;
    
    
    private List<DesignationAppointmentType> designation;
    List<GeoDistrict> districtList;

    public List<GeoWardView> getGeoWardViews() {
        return geoWardViews;
    }

    public void setGeoWardViews(List<GeoWardView> geoWardViews) {
        this.geoWardViews = geoWardViews;
    }

    public List<GeoDistrict> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(List<GeoDistrict> districtList) {
        this.districtList = districtList;
    }

    public List<DesignationAppointmentType> getDesignation() {
        return designation;
    }

    public void setDesignation(List<DesignationAppointmentType> designation) {
        this.designation = designation;
    }

    public boolean isStatus() {
        return status;
    }

    public List<GeoCountryMst> getData() {
        return data;
    }

    public void setData(List<GeoCountryMst> data) {
        this.data = data;
    }

    public Integer getTotalDataCount() {
        return totalDataCount;
    }

    public void setTotalDataCount(Integer totalDataCount) {
        this.totalDataCount = totalDataCount;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}
