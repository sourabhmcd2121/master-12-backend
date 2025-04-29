package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefChargeMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefRelationMap;

public interface RbdRefRelationMapRepo extends JpaRepository<RbdRefRelationMap, String>{

}
