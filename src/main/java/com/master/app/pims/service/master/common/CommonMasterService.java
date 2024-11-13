package com.master.app.pims.service.master.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.EducationLevel;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.MstChargeDetails;
import com.master.app.pims.entities.schemas.mst.OccupationType;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.repositories.mst.MstChargeDetailsRepository;

public interface CommonMasterService {
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
    
    ///for OccupationType join with mst schema
    EducationLevel saveEducationLevel(EducationLevel educationLevel);
    EducationLevel getEducationLevelById(String id);
    
  
    
}
