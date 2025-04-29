package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefOccupationMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRelationMap;

public interface RbdRefOccupationMapRepo extends JpaRepository<RbdRefOccupationMap, String>{

}
