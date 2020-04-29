package com.ammu.services;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ammu.dto.NotesDto;
import com.ammu.model.NotesModel;
import com.ammu.repository.NoteRepository;
import com.ammu.response.Response;

@Service
public class NotesServiceImpl implements NotesService 
{
	@Autowired
	NoteRepository noteRepo;

	@Autowired
	NotesService noteService;

	ModelMapper mapper = new ModelMapper();

	NotesModel notesModel = new NotesModel();

	@Override
	public Response addNote(NotesDto notesDto) 
	{
		mapper.map(notesDto , notesModel);
		noteRepo.save(notesModel);
		return new Response("Successfully note added", 200);
	}

	@Override
	public Response deleteNote(Long id)
	{
		noteRepo.deleteById(id);
		return new Response("Successfully note deleted", 200);
	}


	@Override
	public Response updateNote(NotesDto notesDto , Long id)
	{
		noteRepo.updateTitleAndDescription(notesDto.getTitle(), notesDto.getDescription(), id);
		mapper.map(notesDto, notesModel);
		return new Response("Successfully note updated", 200);	
	}

}
