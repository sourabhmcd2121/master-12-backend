package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.citizenmaster.OfficerImageComment;
import com.master.app.pims.entities.schemas.citizenmaster.TextFlash;

public interface TextFlashRepo extends JpaRepository<TextFlash, String>{

}
