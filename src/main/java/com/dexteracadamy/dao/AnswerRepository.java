/**
 * 
 */
package com.dexteracadamy.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dexteracadamy.model.Answer;
import com.dexteracadamy.model.Question;

/**
 * @author zentere
 *
 */
public interface AnswerRepository extends PagingAndSortingRepository<Answer, Integer>{

	List<Answer> findByQuestion(Question question);
}
