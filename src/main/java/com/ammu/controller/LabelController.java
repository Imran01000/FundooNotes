package com.ammu.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ammu.dto.LabelDto;
import com.ammu.model.LabelModel;
import com.ammu.response.Response;
import com.ammu.services.LabelService;

@RestController
@RequestMapping(path = "/note")
@CrossOrigin(origins = "*")
public class LabelController
{
	@Autowired
	LabelService labelService;
	
	private Response response;

	@PostMapping("/label")
	public ResponseEntity<Response> add(@RequestBody LabelDto labelDto, @RequestHeader String token)
	{
		response = labelService.add(labelDto, token);		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

//	@PutMapping("/edit/{id}")
//	public ResponseEntity<Response> edit(@PathVariable("id") long id, @RequestBody LabelDto labelDto)
//	{
//		response = labelService.edite(labelDto, id);
//		return new ResponseEntity<Response>(response, HttpStatus.OK);	
//	}
//
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Response> delete(@PathVariable("id") int id, @RequestHeader String token)
	{
		response = labelService.delete(id, token);
		return new ResponseEntity<Response>(response, HttpStatus.OK);	
	}

	@GetMapping("/getall")
	public ResponseEntity<Set<LabelModel>> getAll(@RequestHeader("token") String token)
	{
		Set<LabelModel> obj;
        obj = labelService.getAllLabels(token);
        return new ResponseEntity<Set<LabelModel>>(obj,HttpStatus.OK);	
	}
}
