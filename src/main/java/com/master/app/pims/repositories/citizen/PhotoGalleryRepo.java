package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.PhotoGallery;

public interface PhotoGalleryRepo extends JpaRepository<PhotoGallery, String> {

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.photoGalleryNameEn = :photoGalleryNameEn AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryNameEn(@Param("photoGalleryNameEn") String photoGalleryNameEn, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.photoGalleryNameHi = :photoGalleryNameHi AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryNameHi(@Param("photoGalleryNameHi") String photoGalleryNameHi, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.photoGalleryNameRl = :photoGalleryNameRl AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryNameRl(@Param("photoGalleryNameRl") String photoGalleryNameRl, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.imageHeadingEn = :imageHeadingEn AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryImageHeadingEn(@Param("imageHeadingEn") String imageHeadingEn, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.imageHeadingHi = :imageHeadingHi AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryImageHeadingHi(@Param("imageHeadingHi") String imageHeadingHi, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.imageHeadingRl = :imageHeadingRl AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryImageHeadingRl(@Param("imageHeadingRl") String imageHeadingRl, @Param("photoGalleryGuid") String photoGalleryGuid);
    
    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM PhotoGallery p WHERE p.orderNumber = :orderNumber AND p.photoGalleryGuid != :photoGalleryGuid")
    boolean isExistPhotoGalleryOrderNumber(@Param("orderNumber") Long orderNumber, @Param("photoGalleryGuid") String photoGalleryGuid);
}
