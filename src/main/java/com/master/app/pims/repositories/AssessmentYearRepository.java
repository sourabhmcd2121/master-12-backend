package com.master.app.pims.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.master.app.pims.entities.schemas.mst.AssessmentYear;

@Repository
public interface AssessmentYearRepository extends JpaRepository<AssessmentYear, String> {

    // Check if Assessment Code exists for a given guid
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM AssessmentYear a WHERE a.assessmentYearCode = :code AND a.assessmentYearGuid != :guid")
    boolean isExistAssessmentCode(@Param("code") String code, @Param("guid") String guid);
    
   

}
