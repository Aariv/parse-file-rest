/**
 * 
 */
package com.dexteracadamy.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.dexteracadamy.model.Person;

/**
 * @author zentere
 *
 */
public interface PersonRepository extends PagingAndSortingRepository<Person, Integer>{

}
