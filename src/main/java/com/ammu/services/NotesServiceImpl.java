package com.ammu.services;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.dto.ReminderDto;
import com.ammu.exceptionHandling.NoteException;
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
		return new Response(enviroment.getProperty("noteAdd.success.text") , enviroment.getProperty("noteAdd.success.code"), token);
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
		if(!userData.isPresent())
			return new Response("User is not valid" , "401");

		if(noteData.isPresent())
		{
			noteData.get().setTitle(notesDto.getTitle());
			noteData.get().setDescription(notesDto.getDescription());
			NotesModel notesModel = mapper.map(notesDto, NotesModel.class);
			noteRepo.save(notesModel);
			return new Response(enviroment.getProperty("noteUpdate.success.text") , enviroment.getProperty("noteUpdate.success.code"));	
		}
		return new Response("Failed to update", "401");
	}

	@Override
	public List<NotesModel> retriveAllNote( String token) 
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		if(userData.isPresent())
		{
			List<NotesModel> allNotes = noteRepo.findAll().stream()
					.filter(i -> !(i.isArchive() || i.isTrash())).collect(Collectors.toList());
			return allNotes;
		}

		return (List<NotesModel>) null;
	}

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

	@Override
	public Response trashAndUnTrash(int noteId, String token)
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		UserModel user = userData.get();
		Optional<NotesModel> noteData = noteRepo.findByIdAndUserModel(noteId, user);
		return null;
	}

	@Override
	public List<NotesModel> sortByTitle(String token) 
	{
		return (List<NotesModel>) noteRepo.findByOrderByTitleAsc();
	}

	@Override
	public List<NotesModel> sortByDescription(String token) 
	{
		return (List<NotesModel>) noteRepo.findByOrderByDescriptionAsc();
	}

	@Override
	public Response archive(int noteId, String token) 
	{
		int id = JwtToken.retrieveIdFromToken(token);
		userRepository.findById(id);
		Optional<NotesModel> noteData = noteRepo.findById(noteId);
		if(noteData.isPresent())
		{
			NotesModel note = noteData.get();
			note.setArchive(true);
			noteRepo.saveAndFlush(note);
			return new Response("Archive set successful", "200");
		}
		return new Response("something went wrong", "401");
	}

	@Override
	public Response setpined(int noteId, String token) 
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		if(userData.isPresent())
		{
			Optional<NotesModel> noteData = noteRepo.findById(noteId);
			noteData.get().setPined(true);
			return new Response("pined set successful", "200");
		}

		return new Response("something went wrong", "401");
	}

	@Override
	public Response setReminder(ReminderDto remainderDto, String token) 
	{
		LocalDateTime ldt = LocalDateTime.of(remainderDto.getYear(), remainderDto.getMonth(), remainderDto.getDay(),
				remainderDto.getHour(), remainderDto.getMinute());

		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);

		if(userData.isEmpty())
			return new Response("Can't set reminder Invalid user", "401");

		DateTimeFormatter format = DateTimeFormatter.BASIC_ISO_DATE;

		String formatOfDateTime = ldt.format(format);

		Optional<NotesModel> notesData = noteRepo.findById(id);

		if (notesData.isPresent()) 
		{

			notesData.get().setReminder(formatOfDateTime);
			noteRepo.save(notesData.get());
			return new Response("Reminder set", "200");
		}
		else
		{
			throw new NoteException("Can't able to set reminder" , "400");
		}
	}

	@Override
	public Response addColor(String token, int noteId, String color)
	{
		int id = JwtToken.retrieveIdFromToken(token);
		Optional<UserModel> userData = userRepository.findById(id);
		if(!userData.isPresent())
			return new Response("User is not present", "401");
		
		UserModel user = userData.get();
		NotesModel note = noteRepo.findByIdAndUserModel(noteId, user).get();
		note.setColor(color);
		note = noteRepo.saveAndFlush(note);
		if(note == null)
		{
			return new Response("note is not present", "401");
		}
		else
		{
			return new Response("note color set", "201");
		}
	}
}
