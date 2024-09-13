package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.models.common.response.BaseResponse;

public interface Validator {
    // mst related validator
	//mst country validation
    BaseResponse validateMstCountry(GeoCountryMst country);
    
    //state master validation
    BaseResponse validateMasterState(GeoStateMaster state);
    
    //validate Colony Category
    BaseResponse validateColonyCategory(GeoColonyCategory colonyCategory);

    
  
}
