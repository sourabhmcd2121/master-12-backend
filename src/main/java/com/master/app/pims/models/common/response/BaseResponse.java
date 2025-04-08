package com.master.app.pims.models.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.citizenmaster.BgImage;
import com.master.app.pims.entities.schemas.citizenmaster.FlashImage;
import com.master.app.pims.entities.schemas.citizenmaster.FooterMenu;
import com.master.app.pims.entities.schemas.citizenmaster.FooterRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HeaderRibbon;
import com.master.app.pims.entities.schemas.citizenmaster.HelplineNumbers;
import com.master.app.pims.entities.schemas.citizenmaster.LogoDeptName;
import com.master.app.pims.entities.schemas.citizenmaster.Menu;
import com.master.app.pims.entities.schemas.citizenmaster.NoteMenu;
import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;
import com.master.app.pims.entities.schemas.citizenmaster.PhotoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.SocialLinks;
import com.master.app.pims.entities.schemas.citizenmaster.TenderDetails;
import com.master.app.pims.entities.schemas.citizenmaster.TextFlash;
import com.master.app.pims.entities.schemas.citizenmaster.VideoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.WebInfoManager;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.DesignationAppointmentType;
import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.entities.schemas.master.GeoDistrict;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgUnit;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.CommonMasterAppAlert;
import com.master.app.pims.entities.schemas.mst.CommonMasterIndustryArea;
import com.master.app.pims.entities.schemas.mst.CommonMasterProcessStatus;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeClassification;
import com.master.app.pims.entities.schemas.mst.CommonMasterTradeType;
import com.master.app.pims.entities.schemas.mst.DocsCategoryInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoColonyMCD;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.GeoWardMCD;
import com.master.app.pims.entities.schemas.mst.GeoZoneMCD;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.MstRefSla;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.entities.schemas.property.OwnerCategory;
import com.master.app.pims.entities.schemas.property.OwnerType;
import com.master.app.pims.entities.schemas.property.PropertyAgeFactor;
import com.master.app.pims.entities.schemas.property.PropertyCategory;
import com.master.app.pims.entities.schemas.property.PropertyExemption;
import com.master.app.pims.entities.schemas.property.PropertyFloor;
import com.master.app.pims.entities.schemas.property.PropertyOccupancyFactor;
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
    private List<GeoWardMCD> geoWardMCD;
    private List<GeoColonyMCD> geoColonyMCD;
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
    private List<CommonMasterAppAlert> commonMasterAppAlert;
    private List<RefUserDocsMap> refUserDocsMap;
    private List<DesignationAppointmentType> designation;
    private List<IntramcMenuMaster> intramcMenuMaster;
    private List<IntramcRoleMenuMap> intramcRoleMenuMap; 
    private List<CommonMasterTradeClassification> tradeClassification; 
    private List<CommonMasterTradeType> commonMasterTradeType; 
    private List<CommonMasterIndustryArea> industryArea; 
    private List<MstRefSla> refSla; 
    
    ///////////////////////////Citizen Portal/////////////
    private List<AdminDetail> adminDetail; 
    private List<FooterRibbon> footerRibbon; 
    private List<FooterMenu> footerMenu; 
    private List<HelplineNumbers> helplineNumbers; 
    private List<LogoDeptName> logoDeptName; 
    private List<NoteMenu> noteMenu; 
    private List<PhotoGallery> photoGallery; 
    private List<SocialLinks> socialLinks; 
    private List<OfficerImageComment> officerImageComment;   
    private List<TextFlash> textFlash; 
    private List<FlashImage> flashImage; 
    private List<BgImage> bgImage; 
    private List<TenderDetails> tenderDetails; 
    private List<WebInfoManager> webInfoManager;
    private List<VideoGallery> videoGallery;
    private List<HeaderRibbon> headerRibbon;
    private List<Menu> menu;
    
    
    
    ///////////////////////////////////////Property Master/////////////////////
    private List<PropertyAgeFactor> propertyAgeFactor;
    private List<PropertyExemption> propertyExemption;
    private List<PropertyFloor> propertyFloor;
    private List<PropertyOccupancyFactor> propertyOccupancyFactor;
    private List<OwnerCategory> ownerCategory;
    private List<OwnerType> ownerType;
    private List<PropertyCategory> propertyCategory;
    
    
    
    
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
