/**
 * 
 */
package com.dexteracadamy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dexteracadamy.model.Person;
import com.dexteracadamy.service.IPersonService;

/**
 * @author zentere
 *
 */
@RestController
@RequestMapping("/api")
public class DocumentController {

	@Autowired
	private IPersonService personService;

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestParam("file") MultipartFile file) {
		personService.parseTextFile(file);
		return "Success";
	}
	
	@GetMapping("/persons/{page}/{size}")
	public List<Person> persons(@PathVariable int page, @PathVariable int size) {
		return personService.findAll(page, size);
	}
}
