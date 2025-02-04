package com.master.app.pims.service.master.common;
import java.sql.SQLException;

import com.master.app.pims.entities.schemas.citizenmaster.AdminDetail;
import com.master.app.pims.entities.schemas.intramc.IntramcMenuMaster;
import com.master.app.pims.entities.schemas.intramc.IntramcRoleMenuMap;
import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
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
    
    ///for AdminDetail join with mst schema
    AdminDetail saveAdminDetail(AdminDetail adminDetail);
    AdminDetail getAdminDetailById(String id);
    // byte[] getImageByteArrayAdminDetail(String adminDetailGuid) throws SQLException;
	
    
}
