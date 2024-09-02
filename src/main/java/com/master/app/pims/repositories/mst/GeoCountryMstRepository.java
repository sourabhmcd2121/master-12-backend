package com.master.app.pims.repositories.mst;


import com.master.app.pims.entities.schemas.mst.GeoCountryMst;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoCountryMstRepository extends JpaRepository<GeoCountryMst, String> {
}