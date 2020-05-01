package com.ammu.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ammu.model.NotesModel;

@Repository
public interface NoteRepository extends CrudRepository<NotesModel , Long >
{
	@Modifying
	@Query(value = "UPDATE user_notes SET title=? , description=? where id=?" , nativeQuery = true )
	public void updateTitleAndDescription(String title , String description , Long id);
	
	public NotesModel findByTitle(String title);
	
	public NotesModel findByDescription(String description);
	
	@Query(value = "SELECT * FROM user_notes ORDER BY title" , nativeQuery = true)
	public List<NotesModel> sortByTitle();
	
	@Query(value = "SELECT * FROM user_notes ORDER BY description" , nativeQuery = true)
	public List<NotesModel> sortByDescription();

	

}
