package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.models.common.response.BaseResponse;

public interface Validator {
    // mst related validator
    BaseResponse validateMstCountry(GeoCountryMst country);
    BaseResponse validateMasterState(GeoStateMaster state);
    //stateValidator
    // district validator
    // zone
}
