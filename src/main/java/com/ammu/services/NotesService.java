package com.ammu.services;

import com.ammu.dto.NotesDto;
import com.ammu.response.Response;

public interface NotesService
{
	//DECLARED METHODS.
	public Response addNote(NotesDto notesDto);
	public Response deleteNote(Long id);
	public Response updateNote(NotesDto notesDto , Long id);
}
