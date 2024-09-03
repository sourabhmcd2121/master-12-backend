package com.master.app.pims.validators;

import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.models.common.response.BaseResponse;

public interface Validator {
    // mst related validator
    BaseResponse validateMstCountry(GeoCountryMst country);
    //stateValidator
    // district validator
    // zone
}
