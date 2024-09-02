package com.master.app.pims.repositories.master;

import com.master.app.pims.entities.schemas.master.GeoState;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeoStateRepo extends JpaRepository<GeoState, String> {
}
