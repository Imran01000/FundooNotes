package com.ammu.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
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

}
