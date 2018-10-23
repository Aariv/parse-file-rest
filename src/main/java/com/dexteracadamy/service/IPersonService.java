/**
 * 
 */
package com.dexteracadamy.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.dexteracadamy.model.Person;

/**
 * @author zentere
 *
 */
public interface IPersonService {

	public void parseTextFile(MultipartFile file);

	public List<Person> findAll(int page, int size);
}
