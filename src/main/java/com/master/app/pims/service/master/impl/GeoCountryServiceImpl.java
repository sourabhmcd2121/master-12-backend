package com.master.app.pims.service.master.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import com.master.app.pims.repositories.MasterGeoCountryRepository;
import com.master.app.pims.service.master.GeoCountryService;

@Service
public class GeoCountryServiceImpl implements GeoCountryService {

	
	@Autowired
	private MasterGeoCountryRepository masterGeoCountryRepository;
	
	@Override
	public List<GeoCountryMaster> getCountryMasterList() {
		return masterGeoCountryRepository.findAll();
	}

	@Override
	public GeoCountryMaster submitGeoCountry(GeoCountryMaster geoCountry) {
		return masterGeoCountryRepository.save(geoCountry);
	}

}
