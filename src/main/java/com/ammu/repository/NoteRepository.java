package com.ammu.repository;

import java.util.List;
import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ammu.model.NotesModel;
import com.ammu.model.UserModel;


@Repository
public interface NoteRepository extends JpaRepository<NotesModel , Integer>
{
	 Optional<NotesModel> findByTitleAndUserModel(String title , UserModel userModel);
	 Optional<NotesModel> findByIdAndUserModel(int noteId , UserModel userModel);
	 Optional<NotesModel> findByTitleAndDescription( String title , String description);
	 Optional<NotesModel> findByDescription(String description);
	 List<NotesModel> findByOrderByTitleAsc();
	 List<NotesModel> findByOrderByDescriptionAsc();
}
