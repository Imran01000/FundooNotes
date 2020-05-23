package com.ammu.services;

import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.response.Response;

@Service
public interface NotesService
{
	//DECLARED METHODS.
	public Response addNote(NotesDto notesDto , String token); 
	public Response deleteNote(String token , int id);
	public Response updateNote(NotesDto notesDto , int id , String token); 
//	public List<NotesModel> retriveAllNote();
	public Response findByTitle(String title , String token);
	public Response findByDescription(String description , String token);
//	public List<NotesModel> sortByTitle();
//	public List<NotesModel> sortByDescription();
//	public LocalDateTime setRemainder();
}
