package com.ammu.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.NotesDto;
import com.ammu.model.NotesModel;
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
	
	@GetMapping(path = "/showAll")
	public List<NotesModel> retriveAllNote()
	{
		return noteService.retriveAllNote();
	}
	
	@GetMapping(value = "/findByTitle")
	public ResponseEntity<Response> findByTitle(@RequestParam(name = "title")String data )
	{
		response = noteService.findByTitle(data);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@GetMapping(path = "/findBydata")
	public ResponseEntity<Response> findByDescription(@RequestParam(name = "description") String data)
	{
		response = noteService.findByDescription(data);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/sortByTitle")
	public List<NotesModel> sortByTitle()
	{
		return noteService.sortByTitle();
	}
	
	@GetMapping(path = "/sortByDescription")
	public List<NotesModel> sortByDescription()
	{
		return noteService.sortByDescription();
	}
	
	@GetMapping(path = "/remainder")
	public LocalDateTime setRemainder()
	{
		return noteService.setRemainder();
	}
}
