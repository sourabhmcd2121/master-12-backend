package com.master.app.pims.service.master.common;

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
    
}
