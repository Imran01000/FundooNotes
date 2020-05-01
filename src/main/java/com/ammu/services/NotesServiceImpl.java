package com.ammu.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.dto.RemainderDto;
import com.ammu.model.NotesModel;
import com.ammu.repository.NoteRepository;
import com.ammu.response.Response;

@Service
@PropertySource("classpath:message.properties")
public class NotesServiceImpl implements NotesService 
{
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
	public Response addNote(NotesDto notesDto) 
	{
		mapper.map(notesDto , notesModel);
		noteRepo.save(notesModel);
		return new Response(enviroment.getProperty("noteAdd.success.text") , enviroment.getProperty("noteAdd.success.code"));
	}

	@Override
	public Response deleteNote(Long id)
	{
		noteRepo.deleteById(id);
		return new Response(enviroment.getProperty("noteDelete.success.text") , enviroment.getProperty("noteDelete.success.code"));
	}


	@Override
	public Response updateNote(NotesDto notesDto , Long id)
	{
		noteRepo.updateTitleAndDescription(notesDto.getTitle(), notesDto.getDescription(), id);
		mapper.map(notesDto, notesModel);
		return new Response(enviroment.getProperty("noteUpdate.success.text") , enviroment.getProperty("noteUpdate.success.code"));	
	}

	@Override
	public List<NotesModel> retriveAllNote() 
	{
		return (List<NotesModel>) noteRepo.findAll();
	}

	@Override
	public Response findByTitle(String title)
	{
		notesModel = noteRepo.findByTitle(title);
		if(title == notesModel.getTitle())
		{
			return new Response(enviroment.getProperty("title.success.text") , enviroment.getProperty("title.success.code"));
		}
		return new Response(enviroment.getProperty("title.error.text") , enviroment.getProperty("title.error.code"));
	}

	@Override
	public Response findByDescription(String description) 
	{
		notesModel = noteRepo.findByDescription(description);
		if(description == notesModel.getDescription());
		{
			return new Response(enviroment.getProperty("data.success.text") , enviroment.getProperty("data.success.code"));
		}
	}

	@Override
	public List<NotesModel> sortByTitle() 
	{
		return (List<NotesModel>) noteRepo.sortByTitle();
	}

	@Override
	public List<NotesModel> sortByDescription() 
	{
		return (List<NotesModel>) noteRepo.sortByDescription();
	}

	@Override
	public LocalDateTime setRemainder() 
	{
		LocalDateTime currentTime = LocalDateTime.now();
		return currentTime;
	}

}
