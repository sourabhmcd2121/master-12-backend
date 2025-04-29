package com.master.app.pims.repositories.property;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.property.PropertyStructureFactor;

public interface PropertyStructureFactorRepo extends JpaRepository<PropertyStructureFactor, String>{

}
