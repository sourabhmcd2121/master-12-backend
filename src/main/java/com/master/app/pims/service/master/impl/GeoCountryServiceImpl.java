package com.master.app.pims.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.repositories.master.GeoCountryMasterRepo;
import com.master.app.pims.service.master.GeoCountryService;

@Service
public class GeoCountryServiceImpl implements GeoCountryService {

	
	@Autowired
	private GeoCountryMasterRepo geoCountryMasterRepo;
	
	@Override
	public List<GeoCountryMaster> getCountryMasterList() {
		return geoCountryMasterRepo.findAll();
	}

	@Override
	public GeoCountryMaster submitGeoCountry(GeoCountryMaster geoCountry) {
		return geoCountryMasterRepo.save(geoCountry);
	}

}
