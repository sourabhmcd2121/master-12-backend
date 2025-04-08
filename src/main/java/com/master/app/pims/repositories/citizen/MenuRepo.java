package com.master.app.pims.repositories.citizen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.Menu;

public interface MenuRepo extends JpaRepository<Menu, String> {
	
	@Query("SELECT m FROM Menu m WHERE m.primaryMenuGuid IS NULL AND m.isActive = true")
	List<Menu> findByPrimaryMenuGuidIsNullAndIsActiveTrue();

	  // Updated query to filter based on primaryMenuGuid and active status
    @Query("SELECT m FROM Menu m WHERE m.isActive = true AND (m.primaryMenuGuid = :primaryMenuGuid OR :primaryMenuGuid IS NULL) ORDER BY m.menuNameEn")
    List<Menu> getSecondryMenuList(@Param("primaryMenuGuid") String primaryMenuGuid);
    
    // Updated query to filter based on primaryMenuGuid and active status for ternary menus
    @Query("SELECT m FROM Menu m WHERE m.isActive = true AND (m.primaryMenuGuid = :secondryMenuGuid OR :secondryMenuGuid IS NULL) ORDER BY m.menuNameEn")
    List<Menu> getTernaryMenuList(@Param("secondryMenuGuid") String secondryMenuGuid);
    
    @Query("SELECT  m FROM Menu m WHERE m.isActive = true AND (m.primaryMenuGuid = :ternaryMenuGuid OR :ternaryMenuGuid IS NULL) ORDER BY m.menuNameEn")
     List<Menu> getFourthMenuList(@Param("ternaryMenuGuid") String ternaryMenuGuid);
    
    @Query("SELECT m FROM Menu m WHERE m.menuGuid = :primaryMenuGuid")
    Menu getMenuForPrimaryMenuGuid(@Param("primaryMenuGuid") String primaryMenuGuid);

}

