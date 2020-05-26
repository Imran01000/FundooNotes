package com.ammu.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.dto.ReminderDto;
import com.ammu.model.NotesModel;
import com.ammu.response.Response;

@Service
public interface NotesService
{
	//DECLARED METHODS.
	public Response addNote(NotesDto notesDto , String token); 
	public Response deleteNote(String token , int noteId);
	public Response updateNote(NotesDto notesDto , int noteId , String token); 
	public List<NotesModel> retriveAllNote(String token);
	public Response findByTitle(String title , String token);
	public Response findByDescription(String description , String token);
	public Response trashAndUnTrash(int noteId , String token);
	public List<NotesModel> sortByTitle(String token);
	public List<NotesModel> sortByDescription(String token);
	public Response setReminder(ReminderDto remainderDto , String token);
}
