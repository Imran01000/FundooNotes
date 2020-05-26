package com.ammu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.LabelDto;
import com.ammu.response.Response;
import com.ammu.services.LabelService;

@RestController
@RequestMapping(path = "/user")
public class LabelController
{
	@Autowired
	LabelService labelService;
	
	private Response response;

	@PostMapping("/add")
	public ResponseEntity<Response> add(@RequestBody LabelDto labelDto) 
	{
		response = labelService.add(labelDto);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}

	@PutMapping("/edite/{id}")
	public ResponseEntity<Response> edite(@PathVariable("id") long id, @RequestBody LabelDto labelDto)
	{
		response = labelService.edite(labelDto, id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") long id)
	{

		response = labelService.delete(id);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}

	@GetMapping("/show")
	public ResponseEntity<Response> show()
	{

		response = labelService.show();
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}
}
