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

import com.dexteracadamy.model.Answer;
import com.dexteracadamy.model.Person;
import com.dexteracadamy.model.Question;
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
	
	@PostMapping("/uploadDocxFile")
	public List<Question> uploadDocxFile(@RequestParam("file") MultipartFile file) {
		return personService.parseDocxFile(file);
	}
	
	@GetMapping("/getQuestions/{page}/{size}")
	public List<Question> getQuestions(@PathVariable int page, @PathVariable int size) {
		List<Question> questions = personService.getQuestions(page, size);
		return questions;
	}
	
	@GetMapping("/getAnswers/{page}/{size}")
	public List<Answer> getAnswers(@PathVariable int page, @PathVariable int size) {
		List<Answer> answers = personService.getAnswers(page, size);
		return answers;
	}
	
	@GetMapping("/getAnswerByQuestion/{id}")
	public List<Answer> getAnswersByQuestion(@PathVariable int id) {
		List<Answer> answers = personService.getAnswerByQuestion(id);
		return answers;
	}
	
	@GetMapping("/persons/{page}/{size}")
	public List<Person> persons(@PathVariable int page, @PathVariable int size) {
		return personService.findAll(page, size);
	}
}
