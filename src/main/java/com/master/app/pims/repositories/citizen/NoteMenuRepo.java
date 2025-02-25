package com.master.app.pims.repositories.citizen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.master.app.pims.entities.schemas.citizenmaster.NoteMenu;

public interface NoteMenuRepo extends JpaRepository<NoteMenu, String>{
	 @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM NoteMenu n WHERE n.noteMenuNameEn = :noteMenuNameEn AND n.noteMenuGuid != :noteMenuGuid")
	    boolean isExistNoteMenuNameEn(@Param("noteMenuNameEn") String noteMenuNameEn, @Param("noteMenuGuid") String noteMenuGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM NoteMenu n WHERE n.noteMenuNameHi = :noteMenuNameHi AND n.noteMenuGuid != :noteMenuGuid")
	    boolean isExistNoteMenuNameHi(@Param("noteMenuNameHi") String noteMenuNameHi, @Param("noteMenuGuid") String noteMenuGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM NoteMenu n WHERE n.noteMenuNameRl = :noteMenuNameRl AND n.noteMenuGuid != :noteMenuGuid")
	    boolean isExistNoteMenuNameRl(@Param("noteMenuNameRl") String noteMenuNameRl, @Param("noteMenuGuid") String noteMenuGuid);
	    
	    @Query("SELECT CASE WHEN COUNT(n) > 0 THEN true ELSE false END FROM NoteMenu n WHERE n.orderNumber = :orderNumber AND n.noteMenuGuid != :noteMenuGuid")
	    boolean isExistNoteMenuOrderNumber(@Param("orderNumber") Long orderNumber, @Param("noteMenuGuid") String noteMenuGuid);

}
