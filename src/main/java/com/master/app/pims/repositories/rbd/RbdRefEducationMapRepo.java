package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefEducationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefOccupationMap;

public interface RbdRefEducationMapRepo extends JpaRepository<RbdRefEducationMap, String>{

}
