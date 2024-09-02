package com.master.app.pims.service.master;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;

import java.util.List;

public interface GeoCountryService {
    public List<GeoCountryMaster> getCountryMasterList();

    public GeoCountryMaster submitGeoCountry(GeoCountryMaster geoCountry);
}
