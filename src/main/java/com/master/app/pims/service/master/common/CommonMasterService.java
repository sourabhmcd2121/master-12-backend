package com.master.app.pims.service.master.common;
import java.sql.SQLException;

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
import com.master.app.pims.entities.schemas.hospital.HospitalInfo;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
import com.master.app.pims.entities.schemas.master.OrgWrapper;
import com.master.app.pims.entities.schemas.master.PersRelation;
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
import com.master.app.pims.entities.schemas.property.PropertyType;
import com.master.app.pims.entities.schemas.rbd.RbdFeeRelaxationList;
import com.master.app.pims.entities.schemas.rbd.RbdMstCommonList;
import com.master.app.pims.entities.schemas.rbd.RbdMstDocsCategory;
import com.master.app.pims.entities.schemas.rbd.RbdRefBirthDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefChargeMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDeathDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDocsMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefEducationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefOccupationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRegistrationNumber;
import com.master.app.pims.entities.schemas.rbd.RbdRefRelationMap;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;


public interface CommonMasterService {
	//for GeoCountryMst
    GeoCountryMst saveGeoCountryMst(GeoCountryMst geoCountryMst);
    GeoCountryMst getGeoCountryMstById(String id);
    
    //for master state
    GeoStateMaster saveGeoStateMaster(GeoStateMaster geoStateMaster);
    GeoStateMaster getGeoStateMasterById(String id);
    
    ///for Geo ColonyCategory join with mst schema
    GeoColonyCategory saveGeoColonyCategory(GeoColonyCategory geoColonyCategory);
    GeoColonyCategory getGeoColonyCategoryById(String id);
    
    ///for ApplicationMaster join with mst schema
    ApplicationMaster saveApplicationMaster(ApplicationMaster appMaster);
    ApplicationMaster getApplicationMasterById(String id);
    
    ///for AssessmentYear join with mst schema
    AssessmentYear saveAssessmentYear(AssessmentYear assessmentYear);
    AssessmentYear getAssessmentYearById(String id);
    
    ///for AssociatedChargesInfo join with mst schema
    AssociatedChargesInfo saveAssociatedChargesInfo(AssociatedChargesInfo associatedChargesInfo);
    AssociatedChargesInfo getAssociatedChargesInfoById(String id);
    
    ///for DocsSubmissionInfo join with mst schema
    DocsSubmissionInfo saveDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo);
    DocsSubmissionInfo getDocsSubmissionInfoById(String id);
    
    ///for RequestSubmissionType join with mst schema
    RequestSubmissionType saveRequestSubmissionType(RequestSubmissionType requestSubmissionType);
    RequestSubmissionType getRequestSubmissionTypeById(String id);
    
    ///for RequestSubmissionType join with mst schema
    SubmittedRequestStage saveSubmittedRequestStage(SubmittedRequestStage submittedRequestStage);
    SubmittedRequestStage getSubmittedRequestStageById(String id);
    
    ///for RequestSubmissionType join with mst schema
    UnitArea saveUnitArea(UnitArea unitArea);
    UnitArea getUnitAreaById(String id);
    
    ///for MstChargeDetails join with mst schema
    MstChargeDetails saveMstChargeDetails(MstChargeDetails mstChargeDetails);
    MstChargeDetails getMstChargeDetailsById(String id);
    
    ///for OccupationType join with mst schema
    OccupationType saveOccupationType(OccupationType occupationType);
    OccupationType getOccupationTypeById(String id);
    
    ///for EducationLevel join with mst schema
    EducationLevel saveEducationLevel(EducationLevel educationLevel);
    EducationLevel getEducationLevelById(String id);
    
    ///for ReligiousPlaces join with mst schema
    ReligiousPlaces saveReligiousPlaces(ReligiousPlaces religiousPlaces);
    ReligiousPlaces getReligiousPlacesById(String id);
    
    ///for DocsCategoryInfo join with mst schema
    DocsCategoryInfo saveDocsCategoryInfo(DocsCategoryInfo docsCategoryInfo);
    DocsCategoryInfo getDocsCategoryInfoById(String id);
    
    ///for CommonMasterProcessStatus join with mst schema
    CommonMasterProcessStatus saveCommonMasterProcessStatus(CommonMasterProcessStatus commonMasterProcessStatus);
    CommonMasterProcessStatus getCommonMasterProcessStatusById(String id);
    
    ///for SmsEmailTemplate join with mst schema
    SmsEmailTemplate saveSmsEmailTemplate(SmsEmailTemplate smsEmailTemplate);
    SmsEmailTemplate getSmsEmailTemplateById(String id);
    
    ///for OrgPrimary join with master schema
    OrgPrimary saveOrgPrimary(OrgPrimary orgPrimary);
    OrgPrimary getOrgPrimaryById(String id);
    
    ///for OrgWrapper join with master schema
    OrgWrapper saveOrgWrapper(OrgWrapper orgWrapper);
    OrgWrapper getOrgWrapperById(String id);
    
    ///for OrgWrapper join with master schema
    GeoZoneMCD saveGeoZoneMCD(GeoZoneMCD geoZoneMCD);
    GeoZoneMCD getGeoZoneMCDById(String id);
    
    ///for PersRelation join with master schema
    PersRelation savePersRelation(PersRelation persRelation);
    PersRelation getPersRelationById(String id);
    
    ///for OrgRadius join with attendance schema
    OrgRadius saveOrgRadius(OrgRadius orgRadius);
    OrgRadius getOrgRadiusById(String id);
    
  ///for RefDocsCategoryMap join with mst schema
    RefDocsCategoryMap saveRefDocsCategoryMap(RefDocsCategoryMap refDocsCategoryMap);
    RefDocsCategoryMap getRefDocsCategoryMapById(String id);
    
    ///for RefUserDocsMap join with usr schema
    RefUserDocsMap saveRefUserDocsMap(RefUserDocsMap refUserDocsMap);
    RefUserDocsMap getRefUserDocsMapById(String id);
    
    ///for GeoWardMCD join with mst schema
    GeoWardMCD saveGeoWardMCD(GeoWardMCD geoWardMCD);
    GeoWardMCD getGeoWardMCDById(String id);
    
    ///for GeoColonyMCD join with mst schema
    GeoColonyMCD saveGeoColonyMCD(GeoColonyMCD geoColonyMCD);
    GeoColonyMCD getGeoColonyMCDById(String id);
    
    ///for CommonMasterAppAlert join with mst schema
    CommonMasterAppAlert saveCommonMasterAppAlert(CommonMasterAppAlert commonMasterAppAlert);
    CommonMasterAppAlert getCommonMasterAppAlertById(String id);
    
    ///for IntramcMenuMaster join with intramc schema
    IntramcMenuMaster saveIntramcMenuMaster(IntramcMenuMaster intramcMenuMaster);
    IntramcMenuMaster getIntramcMenuMasterById(String id);
    
    ///for IntramcRoleMenuMap join with intramc schema
    IntramcRoleMenuMap saveIntramcRoleMenuMap(IntramcRoleMenuMap intramcRoleMenuMap);
    IntramcRoleMenuMap getIntramcRoleMenuMapById(String id);
    
    ///for CommonMasterTradeClassification join with mst schema
    CommonMasterTradeClassification saveCommonMasterTradeClassification(CommonMasterTradeClassification tradeClassification);
    CommonMasterTradeClassification getCommonMasterTradeClassificationById(String id);
    
    ///for CommonMasterTradeType join with mst schema
    CommonMasterTradeType saveCommonMasterTradeType(CommonMasterTradeType commonMasterTradeType);
    CommonMasterTradeType getCommonMasterTradeTypeById(String id);
    
    ///for CommonMasterIndustryArea join with mst schema
    CommonMasterIndustryArea saveCommonMasterIndustryArea(CommonMasterIndustryArea industryArea);
    CommonMasterIndustryArea getCommonMasterIndustryAreaById(String id);
    
    ///for MstRefSla join with mst schema
    MstRefSla saveMstRefSla(MstRefSla refSla);
    MstRefSla getMstRefSlaById(String id);
    
    
    /////////////////////////////////////////////////Portal Master////////////////
    
    ///for AdminDetail join with citizen_app schema
    AdminDetail saveAdminDetail(AdminDetail adminDetail);
    AdminDetail getAdminDetailById(String id);
    
    ///for FooterRibbon join with citizen_app schema
    FooterRibbon saveFooterRibbon(FooterRibbon footerRibbon);
    FooterRibbon getFooterRibbonById(String id);
    
    ///for FooterMenu join with citizen_app schema
    FooterMenu saveFooterMenu(FooterMenu footerMenu);
    FooterMenu getFooterMenuById(String id);
    
    ///for FooterRibbon join with citizen_app schema
    HelplineNumbers saveHelplineNumbers(HelplineNumbers helplineNumbers);
    HelplineNumbers getHelplineNumbersById(String id);
    
    ///for LogoDeptName join with citizen_app schema
    LogoDeptName saveLogoDeptName(LogoDeptName logoDeptName);
    LogoDeptName getLogoDeptNameById(String id);
    
    ///for NoteMenu join with citizen_app schema
    NoteMenu saveNoteMenu(NoteMenu noteMenu);
    NoteMenu getNoteMenuById(String id);
    
    ///for PhotoGallery join with citizen_app schema
    PhotoGallery savePhotoGallery(PhotoGallery photoGallery);
    PhotoGallery getPhotoGalleryById(String id);
    
    ///for SocialLinks join with citizen_app schema
    SocialLinks saveSocialLinks(SocialLinks socialLinks);
    SocialLinks getSocialLinksById(String id);
    
    ///for OfficerImageComment join with citizen_app schema
    OfficerImageComment saveOfficerImageComment(OfficerImageComment officerImageComment);
    OfficerImageComment getOfficerImageCommentById(String id);
    
    ///for TextFlash join with citizen_app schema
    TextFlash saveTextFlash(TextFlash textFlash);
    TextFlash getTextFlashById(String id);
    
    ///for TextFlash join with citizen_app schema
    FlashImage saveFlashImage(FlashImage flashImage);
    FlashImage getFlashImageById(String id);
    
    ///for BgImage join with citizen_app schema
    BgImage saveBgImage(BgImage bgImage);
    BgImage getBgImageById(String id);
    
    ///for TenderDetails join with citizen_app schema
    TenderDetails saveTenderDetails(TenderDetails tenderDetails);
    TenderDetails getTenderDetailsById(String id);
    
    ///for WebInfoManager join with citizen_app schema
    WebInfoManager saveWebInfoManager(WebInfoManager webInfoManager);
    WebInfoManager getWebInfoManagerById(String id);
    
    ///for VideoGallery join with citizen_app schema
    VideoGallery saveVideoGallery(VideoGallery videoGallery);
    VideoGallery getVideoGalleryById(String id);
    
    ///for HeaderRibbon join with citizen_app schema
    HeaderRibbon saveHeaderRibbon(HeaderRibbon headerRibbon);
    HeaderRibbon getHeaderRibbonById(String id);
    
    ///for Menu join with citizen_app schema
    Menu saveMenu(Menu menu);
    Menu getMenuById(String id);
    
    /////////////////////////////Property Master//////////////////////////
    
    ///for PropertyAgeFactor join with property schema
    PropertyAgeFactor savePropertyAgeFactor(PropertyAgeFactor propertyAgeFactor);
    PropertyAgeFactor getPropertyAgeFactorById(String id);
    
    ///for PropertyExemption join with property schema
    PropertyExemption savePropertyExemption(PropertyExemption propertyExemption);
    PropertyExemption getPropertyExemptionById(String id);
    
    ///for PropertyFloor join with property schema
    PropertyFloor savePropertyFloor(PropertyFloor propertyFloor);
    PropertyFloor getPropertyFloorById(String id);
    
    ///for PropertyFloor join with property schema
    PropertyOccupancyFactor savePropertyOccupancyFactor(PropertyOccupancyFactor propertyOccupancyFactor);
    PropertyOccupancyFactor getPropertyOccupancyFactorById(String id);
    
    ///for OwnerCategory join with property schema
    OwnerCategory saveOwnerCategory(OwnerCategory ownerCategory);
    OwnerCategory getOwnerCategoryById(String id);
    
    ///for OwnerType join with property schema
    OwnerType saveOwnerType(OwnerType ownerType);
    OwnerType getOwnerTypeById(String id);
    
    ///for PropertyCategory join with property schema
    PropertyCategory savePropertyCategory(PropertyCategory propertyCategory);
    PropertyCategory getPropertyCategoryById(String id);
    
    ///for PropertyType join with property schema
    PropertyType savePropertyType(PropertyType propertyType);
    PropertyType getPropertyTypeById(String id);
    
    ///for RbdMstCommonList join with rbd schema
    RbdMstCommonList saveRbdMstCommonList(RbdMstCommonList rbdMstCommonList);
    RbdMstCommonList getRbdMstCommonListById(String id);
    
    ///for RbdMstDocsCategory join with rbd schema
    RbdMstDocsCategory saveRbdMstDocsCategory(RbdMstDocsCategory rbdMstDocsCategory);
    RbdMstDocsCategory getRbdMstDocsCategoryById(String id);
    
    ///for RbdRefBirthDocsMap join with rbd schema
    RbdRefBirthDocsMap saveRbdMstDocsCategory(RbdRefBirthDocsMap rbdRefBirthDocsMap);
    RbdRefBirthDocsMap getRbdRefBirthDocsMapById(String id);
    
    ///for RbdRefBirthDocsMap join with rbd schema
    RbdRefDeathDocsMap saveRbdRefDeathDocsMap(RbdRefDeathDocsMap rbdRefDeathDocsMap);
    RbdRefDeathDocsMap getRbdRefDeathDocsMapById(String id);
    
    ///for RbdRefBirthDocsMap join with rbd schema
    RbdRefChargeMap saveRbdRefChargeMap(RbdRefChargeMap rbdRefChargeMap);
    RbdRefChargeMap getRbdRefChargeMapById(String id);
    
    ///for RbdRefRelationMap join with rbd schema
    RbdRefRelationMap saveRbdRefRelationMap(RbdRefRelationMap rbdRefRelationMap);
    RbdRefRelationMap getRbdRefRelationMapById(String id);
    
    ///for RbdRefOccupationMap join with rbd schema
    RbdRefOccupationMap saveRbdRefOccupationMap(RbdRefOccupationMap rbdRefOccupationMap);
    RbdRefOccupationMap getRbdRefOccupationMapById(String id);
    
    ///for RbdRefEducationMap join with rbd schema
    RbdRefEducationMap saveRbdRefEducationMap(RbdRefEducationMap rbdRefEducationMap);
    RbdRefEducationMap getRbdRefEducationMapById(String id);
    
    ///for RbdRefRegistrationNumber join with rbd schema
    RbdRefRegistrationNumber saveRbdRefRegistrationNumber(RbdRefRegistrationNumber rbdRefRegistrationNumber);
    RbdRefRegistrationNumber getRbdRefRegistrationNumberById(String id);
    
    ///for RbdRefRegistrationNumber join with rbd schema
    RbdRefDocsMap saveRbdRefDocsMap(RbdRefDocsMap rbdRefDocsMap);
    RbdRefDocsMap getRbdRefDocsMapById(String id);
    
    ///for RbdRefRegistrationNumber join with rbd schema
    RbdFeeRelaxationList saveRbdFeeRelaxationList(RbdFeeRelaxationList rbdFeeRelaxationList);
    RbdFeeRelaxationList getRbdFeeRelaxationListById(String id);
    
    
    
    
    
    
    ////////////////////////////////////////////////Hospital Master//////////////////////////
    ///for HospitalInfo join with hospital schema
    HospitalInfo saveHospitalInfo(HospitalInfo hospitalInfo);
    HospitalInfo getHospitalInfoById(String id);
    
    
    
}
