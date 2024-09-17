package com.master.app.pims.service.master.common;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;
import com.master.app.pims.entities.schemas.mst.GeoColonyCategory;
import com.master.app.pims.entities.schemas.mst.GeoCountryMst;

public interface CommonMasterService {
    GeoCountryMst saveGeoCountryMst(GeoCountryMst geoCountryMst);
    GeoCountryMst getGeoCountryMstById(String id);
    
    //for master state
    GeoStateMaster saveGeoStateMaster(GeoStateMaster geoStateMaster);
    GeoStateMaster getGeoStateMasterById(String id);
    
    ///for Geo ColonyCategory join with mst schema
    GeoColonyCategory saveGeoColonyCategory(GeoColonyCategory geoColonyCategory);
    GeoColonyCategory getGeoColonyCategoryById(String id);
    
    ///for Geo ColonyCategory join with mst schema
    ApplicationMaster saveApplicationMaster(ApplicationMaster appMaster);
    ApplicationMaster getApplicationMasterById(String id);
}
