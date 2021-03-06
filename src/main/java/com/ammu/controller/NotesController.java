package com.ammu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping(path = "/note")
@CrossOrigin(origins = "*")
public class NotesController 
{
	@Autowired
	private NotesService noteService;
	
	private Response response;
	
	@PostMapping("/note")
	public ResponseEntity<Response> addUserNotes(@RequestBody NotesDto notesDto, @RequestHeader("token") String token)
	{
		response = noteService.addNote(notesDto , token);		
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}
	
	@DeleteMapping("/note/{id}")
	public ResponseEntity<Response> deleteNotes( @RequestHeader("token") String token, @PathVariable("id") int noteId)
	{
		response = noteService.deleteNote(token , noteId);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/updatenote/{id}")
	public ResponseEntity<Response> updateUserNotes(@PathVariable(name = "id") int id, @RequestBody NotesDto notesDto
			,@RequestHeader(name = "token") String token)
	{
		response = noteService.updateNote(notesDto, id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/showall")
	public List<NotesModel> retriveAllNote(@RequestHeader("token")String token) 
	{
		return noteService.retriveAllNote(token);
	}
	
	@GetMapping(path = "/archivenotes")
	public List<NotesModel> showAllArchiveNotes(@RequestHeader("token")String token) 
	{
		return noteService.showAllArchiveNote(token);
	}
	
	@GetMapping(path = "/trash")
	public List<NotesModel> showAllTrashNotes(@RequestHeader("token")String token) 
	{
		return noteService.showAllTrashNote(token);
	}
	
	@GetMapping(value = "/findbytitle")
	public ResponseEntity<Response> findByData(@RequestParam(name = "data")String data, @RequestHeader("token") String token)
	{
		response = noteService.findByTitle(data, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@GetMapping(path = "/sortbytitle")
	public List<NotesModel> sortByTitle(@RequestHeader("token") String token) 
	{
		return noteService.sortByTitle(token);
	}
	
	@GetMapping(path = "/sortbydescription")
	public List<NotesModel> sortByDescription(@RequestHeader("token") String token) 
	{
		return noteService.sortByDescription(token);
	}
	
	@PutMapping(path = "/archive/{noteId}")
	public ResponseEntity<Response> setArchive(@PathVariable int noteId ,@RequestHeader("token") String token)
	{
		response = noteService.archive(noteId, token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/trash/{noteId}")
	public ResponseEntity<Response> setTrash(@PathVariable int noteId ,@RequestHeader("token") String token)
	{
		response = noteService.trashAndUnTrash(noteId, token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/reminder/{noteId}")
	public ResponseEntity<Response> setRemainder(@RequestBody ReminderDto reminderDto, @RequestHeader("token") String token, @PathVariable int noteId)
	{
		response = noteService.setReminder(reminderDto, token, noteId);
		System.out.println(reminderDto.getReminder());
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/color/{id}")
	public ResponseEntity<Response> setColor(@RequestParam String color, @PathVariable int id, @RequestHeader("token") String token)
	{
		response = noteService.addColor(token, id, color);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/restorenote/{noteId}")
	public ResponseEntity<Response> restoreNote(@PathVariable int noteId ,@RequestHeader("token") String token)
	{
		response = noteService.restoreNote(noteId, token);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
	
	@PutMapping(path = "/removereminder/{noteId}")
	public ResponseEntity<Response> removedRemainder(@RequestHeader("token") String token, @PathVariable int noteId)
	{
		response = noteService.removeReminder(token, noteId);
		return new ResponseEntity<Response>(response , HttpStatus.OK);
	}
}
