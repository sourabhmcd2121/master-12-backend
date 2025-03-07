package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;

import com.master.app.pims.entities.schemas.citizenmaster.VideoGallery;
import com.master.app.pims.entities.schemas.citizenmaster.WebInfoManager;

public interface VideoGalleryRepo  extends JpaRepository<VideoGallery, String>{

}
