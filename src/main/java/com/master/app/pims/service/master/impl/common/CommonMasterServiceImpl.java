package com.master.app.pims.service.master.impl.common;

import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import com.master.app.pims.repositories.mst.GeoCountryMstRepository;
import com.master.app.pims.service.master.common.CommonMasterService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CommonMasterServiceImpl implements CommonMasterService {

    @Autowired
    private GeoCountryMstRepository geoCountryMstRepository;

    @Override
    public GeoCountryMst saveGeoCountryMst(GeoCountryMst geoCountryMst) {
        return geoCountryMstRepository.save(geoCountryMst);
    }

    @Override
    public GeoCountryMst getGeoCountryMstById(String id) {
        return geoCountryMstRepository.findById(id).orElseThrow(() -> new RuntimeException("Resource not found with guidId : " + id));
    }
}