package com.master.app.pims.repositories.rbd;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.rbd.RbdRefChargeMap;
import com.master.app.pims.entities.schemas.rbd.RbdRefDeathDocsMap;

public interface RbdRefChargeMapRepo extends JpaRepository<RbdRefChargeMap, String>{

}
