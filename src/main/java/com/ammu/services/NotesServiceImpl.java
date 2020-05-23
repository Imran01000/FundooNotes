package com.ammu.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.exceptionHandling.NoteIdNotFoundException;
import com.ammu.exceptionHandling.NotesTitleNotFoundException;
import com.ammu.model.NotesModel;
import com.ammu.model.UserModel;
import com.ammu.repository.NoteRepository;
import com.ammu.repository.UserRepository;
import com.ammu.response.Response;
import com.ammu.security.utility.JwtToken;

@Service
@PropertySource("classpath:message.properties")
public class NotesServiceImpl implements NotesService 
{

	@Autowired
	UserRepository userRepository;

	@Autowired
	NoteRepository noteRepo;

	@Autowired
	NotesService noteService;

	@Autowired
	ModelMapper mapper;

	@Autowired
	NotesModel notesModel;

	@Autowired
	Environment enviroment;

	@Override
	public Response addNote(NotesDto notesDto , String token) 
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		NotesModel noteData = mapper.map(notesDto , NotesModel.class);
		noteData.setUserModel(userData.get());
		noteRepo.save(noteData);
		return new Response(enviroment.getProperty("noteAdd.success.text") , enviroment.getProperty("noteAdd.success.code"));
	}

	@Override
	public Response deleteNote(String token , int noteId)
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		if(id == 0)
			throw new NoteIdNotFoundException("This ID is not present","400" );
		UserModel user = userData.get();
		Optional<NotesModel> noteData = noteRepo.findByIdAndUserModel(noteId , user);
		
		if(noteData.isEmpty())
			return new Response("This note id is not present", "400");
		noteRepo.deleteById(noteId);
		
		if(noteRepo.findById(noteId).isPresent())
			return new Response(enviroment.getProperty("noteDelete.success.text") , enviroment.getProperty("noteDelete.success.code"));
		return new Response("Note is not deleted", "400");
	}

	@Override
	public Response updateNote(NotesDto notesDto , int noteId , String token) 
	{

		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		UserModel user = userData.get();
		Optional<NotesModel> noteData = noteRepo.findByIdAndUserModel(noteId, user);
		if(noteData.isPresent())
		{
			NotesModel notesModel = mapper.map(notesDto, NotesModel.class);
			noteData.get().setTitle(notesDto.getTitle());
			noteData.get().setDescription(notesDto.getDescription());
			noteRepo.save(notesModel);
		}
		
		return new Response(enviroment.getProperty("noteUpdate.success.text") , enviroment.getProperty("noteUpdate.success.code"));	
	}

//	@Override
//	public List<NotesModel> retriveAllNote() 
//	{
//		//		 User user = userData.get();
//		//	        List<Note> allNotes = noteRepository.findAllByUser(user).stream()
//		//	                .filter(u -> !(u.isArchived() || u.isTrashed())).collect(Collectors.toList());
//		//	        return allNotes;
//		
//		return (List<NotesModel>) noteRepo.findAll();
//	}

	@Override
	public Response findByTitle(String title , String token) 
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		UserModel user = userData.get();
		Optional<NotesModel> noteData = noteRepo.findByTitleAndUserModel(title , user);
		if(noteData.get().getTitle().isEmpty())
		{
			throw new NotesTitleNotFoundException("Title is not present", "400");
		}
		return new Response(enviroment.getProperty("title.success.text") , enviroment.getProperty("title.success.code"));
	}

	@Override
	public Response findByDescription(String description , String token)
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		UserModel user = userData.get();
		Optional<NotesModel> noteData = noteRepo.findByTitleAndUserModel(description, user);
		if(noteData.get().getDescription().isEmpty())
		{
			return new Response("not present", "400");
		}
		return new Response(enviroment.getProperty("data.success.text") , enviroment.getProperty("data.success.code"));
	}

//	@Override
//	public List<NotesModel> sortByTitle() 
//	{
//		return (List<NotesModel>) noteRepo.sortByTitle();
//	}
//
//	@Override
//	public List<NotesModel> sortByDescription() 
//	{
//		return (List<NotesModel>) noteRepo.sortByDescription();
//	}
//
//	@Override
//	public LocalDateTime setRemainder() 
//	{
//		LocalDateTime currentTime = LocalDateTime.now();
//		return currentTime;
//	}

}
