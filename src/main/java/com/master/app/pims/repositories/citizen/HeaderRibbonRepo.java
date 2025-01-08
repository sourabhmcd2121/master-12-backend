package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.citizen.HeaderRibbon;
import com.master.app.pims.entities.schemas.mst.ApplicationMaster;

public interface HeaderRibbonRepo extends JpaRepository<HeaderRibbon, String>{

}
