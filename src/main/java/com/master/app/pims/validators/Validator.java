package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.AssessmentYear;
import com.master.app.pims.entities.schemas.mst.AssociatedChargesInfo;
import com.master.app.pims.entities.schemas.mst.DocsSubmissionInfo;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.entities.schemas.mst.RequestSubmissionType;
import com.master.app.pims.models.common.response.BaseResponse;

public interface Validator {
    // mst related validator
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
    
    //DocsSubmissionInfo validation
    BaseResponse validateRequestSubmissionType(RequestSubmissionType requestSubmissionType);


    
  
}
