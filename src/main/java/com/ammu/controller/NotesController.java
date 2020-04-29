package com.ammu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.NotesDto;
import com.ammu.response.Response;
import com.ammu.services.NotesService;

@RestController
public class NotesController 
{
	@Autowired
	private NotesService noteService;
	
	private Response response;
	
	@PutMapping(path = "/addUserNotes")
	public ResponseEntity<Response> addUserNotes(@RequestBody NotesDto notesDto)
	{
		response = noteService.addNote(notesDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping(value = "/deleteUserNotes")
	public ResponseEntity<Response> deleteNotes(@RequestParam(name = "id") Long id)
	{
		response = noteService.deleteNote(id);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/updateUserNotes/{id}")
	public ResponseEntity<Response> updateUserNotes(@PathVariable(name = "id") Long id , NotesDto notesDto)
	{
		response = noteService.updateNote(notesDto, id);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
}
