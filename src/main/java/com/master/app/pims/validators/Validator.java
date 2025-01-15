package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.master.OrgPrimary;
import com.master.app.pims.entities.schemas.master.OrgRadius;
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
import com.master.app.pims.entities.schemas.mst.RefDocsCategoryMap;
import com.master.app.pims.entities.schemas.mst.ReligiousPlaces;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.entities.schemas.mst.SmsEmailTemplate;
import com.master.app.pims.entities.schemas.mst.SubmittedRequestStage;
import com.master.app.pims.entities.schemas.mst.UnitArea;
import com.master.app.pims.entities.schemas.usr.RefUserDocsMap;
import com.master.app.pims.models.common.response.BaseResponse;

public interface Validator {
    
	//mst country validation
    BaseResponse validateMstCountry(GeoCountryMst country);
    
    //state master validation
    BaseResponse validateMasterState(GeoStateMaster state);
    
    //validate Colony Category
    BaseResponse validateColonyCategory(GeoColonyCategory colonyCategory);
    
    
    //ApplicationMaster validation
    BaseResponse validateApplicationMaster(ApplicationMaster appMaster);
    
    //AssessmentYear validation
    BaseResponse validateAssessmentYear(AssessmentYear assessmentYear);
    
    //AssociatedChargesInfo validation
    BaseResponse validateAssociatedChargesInfo(AssociatedChargesInfo associatedChargesInfo);
    
    //DocsSubmissionInfo validation
    BaseResponse validateDocsSubmissionInfo(DocsSubmissionInfo docsSubmissionInfo);
    
    //RequestSubmissionType validation
    BaseResponse validateRequestSubmissionType(RequestSubmissionType requestSubmissionType);
    
    //SubmittedRequestStage validation
    BaseResponse validateSubmittedRequestStage(SubmittedRequestStage submittedRequestStage);
    
    //UnitArea validation
    BaseResponse validateUnitArea(UnitArea unitArea);
    
    //MstChargeDetails validation
    BaseResponse validateMstChargeDetails(MstChargeDetails mstChargeDetails);
    
    //OccupationType validation
    BaseResponse validateOccupationType(OccupationType occupationType);

    //EducationLevel validation
    BaseResponse validateEducationLevel(EducationLevel educationLevel);
    
    //ReligiousPlaces validation
    BaseResponse validateReligiousPlaces(ReligiousPlaces religiousPlaces);

    //DocsCategoryInfo validation
    BaseResponse validateDocsCategoryInfo(DocsCategoryInfo docsCategoryInfo);
    
    //CommonMasterProcessStatus validation
    BaseResponse validateCommonMasterProcessStatus(CommonMasterProcessStatus commonMasterProcessStatus);
    
    //SmsEmailTemplate validation
    BaseResponse validateSmsEmailTemplate(SmsEmailTemplate smsEmailTemplate);

    //OrgPrimary validation
    BaseResponse validateOrgPrimary(OrgPrimary orgPrimary);
    
    //OrgWrapper validation
    BaseResponse validateOrgWrapper(OrgWrapper orgWrapper);
    
  //GeoZoneMCD validation
    BaseResponse validateGeoZoneMCD(GeoZoneMCD geoZoneMcd);
    
    //OrgRadius validation
    BaseResponse validateOrgRadius(OrgRadius orgRadius);
    
    //RefDocsCategoryMap validation
    BaseResponse validateRefDocsCategoryMap(RefDocsCategoryMap refDocsCategoryMap);
    
    //RefDocsCategoryMap validation
    BaseResponse validateRefUserDocsMap(RefUserDocsMap refUserDocsMap);
    
   
}
