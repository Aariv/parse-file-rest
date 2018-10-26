/**
 * 
 */
package com.dexteracadamy.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dexteracadamy.model.Question;

/**
 * @author zentere
 *
 */
public interface QuestionBankRepository extends PagingAndSortingRepository<Question, Integer>{

}
