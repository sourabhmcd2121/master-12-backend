package com.master.app.pims.models.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;

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
    
    
    
    private List<DesignationAppointmentType> designation;
    List<GeoDistrict> districtList;

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
