package com.master.app.pims.service.master.common;

import com.master.app.pims.entities.schemas.mst.GeoCountryMst;

public interface CommonMasterService {
    GeoCountryMst saveGeoCountryMst(GeoCountryMst geoCountryMst);
    GeoCountryMst getGeoCountryMstById(String id);
}
