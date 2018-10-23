/**
 * 
 */
package com.dexteracadamy.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dexteracadamy.dao.PersonRepository;
import com.dexteracadamy.model.Person;
import com.dexteracadamy.service.IPersonService;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;

/**
 * @author zentere
 *
 */
@Service
public class PersonServiceImpl implements IPersonService {

	@Autowired
	private PersonRepository userRepository;

	@Override
	public void parseTextFile(MultipartFile file) {
		try {
			userRepository.saveAll(readPersonFile(convert(file)));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static List<Person> readPersonFile(File csvFile) throws Exception {
		try {
			MappingIterator<Person> personIter = new CsvMapper().readerWithTypedSchemaFor(Person.class)
					.readValues(csvFile);

			return personIter.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File convert(MultipartFile file) throws IOException {
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
	}

	@Override
	public List<Person> findAll(int page, int size) {
		return userRepository.findAll(new PageRequest(page, size)).getContent();
	}
}
