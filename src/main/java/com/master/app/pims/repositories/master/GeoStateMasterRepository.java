package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoStateMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoStateMasterRepository extends JpaRepository<GeoStateMaster, String> {
}
