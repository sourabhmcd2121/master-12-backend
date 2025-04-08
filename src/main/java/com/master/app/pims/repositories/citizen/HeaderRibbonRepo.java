package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.citizenmaster.HeaderRibbon;

public interface HeaderRibbonRepo extends JpaRepository<HeaderRibbon, String>{

}
