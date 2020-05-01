package com.ammu.services;

import java.time.LocalDateTime;
import java.util.List;

import com.ammu.dto.NotesDto;
import com.ammu.dto.RemainderDto;
import com.ammu.model.NotesModel;
import com.ammu.response.Response;

public interface NotesService
{
	//DECLARED METHODS.
	public Response addNote(NotesDto notesDto);
	public Response deleteNote(Long id);
	public Response updateNote(NotesDto notesDto , Long id);
	public List<NotesModel> retriveAllNote();
	public Response findByTitle(String title);
	public Response findByDescription(String description);
	public List<NotesModel> sortByTitle();
	public List<NotesModel> sortByDescription();
	public LocalDateTime setRemainder();
}
