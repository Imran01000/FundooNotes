package com.ammu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.NotesDto;
import com.ammu.dto.ReminderDto;
import com.ammu.model.NotesModel;
import com.ammu.response.Response;
import com.ammu.services.NotesService;

@RestController
@RequestMapping(path = "/user")
public class NotesController 
{
	@Autowired
	private NotesService noteService;
	
	private Response response;
	
	@PostMapping("/addNote")
	public ResponseEntity<Response> addUserNotes(@RequestBody NotesDto notesDto ,@RequestHeader("token") String token)
	{
		response = noteService.addNote(notesDto , token);		
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping("/deleteNote/{id}")
	public ResponseEntity<Response> deleteNotes( @RequestHeader("token") String token , @PathVariable("id") int noteId)
	{
		response = noteService.deleteNote(token , noteId);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/updateNote/{id}")
	public ResponseEntity<Response> updateUserNotes(@PathVariable(name = "id") int id , NotesDto notesDto
			,@RequestHeader(name = "token") String token)
	{
		response = noteService.updateNote(notesDto, id , token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@GetMapping(path = "/showAll")
	public List<NotesModel> retriveAllNote(@RequestHeader("token")String token) 
	{
		return noteService.retriveAllNote(token);
	}
	
	@GetMapping(value = "/findByTitle")
	public ResponseEntity<Response> findByTitle(@RequestParam(name = "title")String data , @RequestHeader("token") String token)
	{
		
		response = noteService.findByTitle(data , token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@GetMapping(path = "/findByDescription")
	public ResponseEntity<Response> findByDescription(@RequestParam(name = "description") String data , @RequestHeader("token") String token)
	{
		response = noteService.findByDescription(data , token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
		
	}
	
	@GetMapping(path = "/sortByTitle")
	public List<NotesModel> sortByTitle(@RequestHeader("token") String token) 
	{
		return noteService.sortByTitle(token);
	}
	
	@GetMapping(path = "/sortByDescription")
	public List<NotesModel> sortByDescription(@RequestHeader("token") String token) 
	{
		return noteService.sortByDescription(token);
	}
	
	@PutMapping(path = "/remainder")
	public ResponseEntity<Response> setRemainder(ReminderDto reminderDto , @RequestHeader("token") String token)
	{
		response = noteService.setReminder(reminderDto, token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
}
