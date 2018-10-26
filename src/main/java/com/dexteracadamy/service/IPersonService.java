/**
 * 
 */
package com.dexteracadamy.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dexteracadamy.model.Answer;
import com.dexteracadamy.model.Person;
import com.dexteracadamy.model.Question;

/**
 * @author zentere
 *
 */
public interface IPersonService {

	public void parseTextFile(MultipartFile file);
	
	public List<Question> parseDocxFile(MultipartFile file);
	
	public List<Question> getQuestions(int page, int size);
	
	public List<Answer> getAnswers(int page, int size);
	
	public List<Answer> getAnswerByQuestion(int id);

	public List<Person> findAll(int page, int size);
}
