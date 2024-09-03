package com.master.app.pims.repositories.master;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.master.app.pims.models.common.response.SelectOption;
import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.master.GeoCountryMaster;
import org.springframework.data.jpa.repository.Query;

public interface GeoCountryMasterRepo extends JpaRepository<GeoCountryMaster, String> {
	// For country code validation
	boolean existsByCountryCodeIgnoreCaseAndCountryMasterGuidNot(String countryCode, String countryMasterGuid);

	// For country name En validation
	boolean existsByCountryNameEnIgnoreCaseAndCountryMasterGuidNot(String countryNameEn, String countryMasterGuid);

	// For country name Hi validation
	boolean existsByCountryNameHiIgnoreCaseAndCountryMasterGuidNot(String countryNameHi, String countryMasterGuid);

	// For country name Rl validation
	boolean existsByCountryNameRlIgnoreCaseAndCountryMasterGuidNot(String countryNameRl, String countryMasterGuid);

	// For country code and guid validation
	Optional<GeoCountryMaster> findByCountryCodeAndCountryMasterGuidNotIgnoreCase(String countryCode,
                                                                                  String countryMasterGuid);

	// For country name En and guid validation
	Optional<GeoCountryMaster> findByCountryNameEnAndCountryMasterGuidNotIgnoreCase(String countryNameEn,
                                                                                    String countryMasterGuid);

	// For country name Hi and guid validation
	Optional<GeoCountryMaster> findByCountryNameHiAndCountryMasterGuidNotIgnoreCase(String countryNameHi,
                                                                                    String countryMasterGuid);

	// For country name Rl and guid validation
	Optional<GeoCountryMaster> findByCountryNameRlAndCountryMasterGuidNotIgnoreCase(String countryNameRl,
                                                                                    String countryMasterGuid);

	List<GeoCountryMaster> findAllByOrderByCountryNameEn();

	@Query("SELECT g.countryMasterGuid, g.countryNameEn " +
			"FROM GeoCountryMaster g " +
			"WHERE g.isRecordActive = true " +
			"ORDER BY g.countryNameEn")
	List<Object[]> getGeoMasterCountryListRaw();

	default HashMap<String, String> getGeoMasterCountryList() {
		List<Object[]> results = getGeoMasterCountryListRaw();
		HashMap<String, String> map = new HashMap<>();
		for (Object[] result : results) {
			map.put((String) result[0], (String) result[1]);
		}
		return map;
	}
}
