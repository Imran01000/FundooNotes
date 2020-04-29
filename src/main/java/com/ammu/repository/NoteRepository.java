package com.ammu.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ammu.model.NotesModel;

@Repository
public interface NoteRepository extends CrudRepository<NotesModel , Long >
{
	@Modifying
	@Query(value = "UPDATE user_notes SET title=? , description=? where id=?" ,nativeQuery = true )
	void updateTitleAndDescription(String title , String description , Long id);
}
