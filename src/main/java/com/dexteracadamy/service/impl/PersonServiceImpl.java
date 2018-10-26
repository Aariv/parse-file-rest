/**
 * 
 */
package com.dexteracadamy.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dexteracadamy.dao.AnswerRepository;
import com.dexteracadamy.dao.PersonRepository;
import com.dexteracadamy.dao.QuestionBankRepository;
import com.dexteracadamy.model.Answer;
import com.dexteracadamy.model.Person;
import com.dexteracadamy.model.Question;
import com.dexteracadamy.service.IPersonService;
import com.dexteracadamy.utils.DexterUtils;
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

	@Autowired
	private QuestionBankRepository questionRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@Override
	public void parseTextFile(MultipartFile file) {
		try {
			userRepository.saveAll(readPersonFile(convert(file)));
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

	@SuppressWarnings("deprecation")
	@Override
	public List<Person> findAll(int page, int size) {
		return userRepository.findAll(new PageRequest(page, size)).getContent();
	}

	public List<Question> readDocFile(File file) {
		try {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());

			XWPFDocument document = new XWPFDocument(fis);

			List<XWPFParagraph> paragraphs = document.getParagraphs();
			List<Question> questionBanks = new ArrayList<>();
			StringBuilder question = new StringBuilder();
			List<Answer> options = new ArrayList<Answer>();
			Answer correctAnswer = new Answer();
			for (XWPFParagraph para : paragraphs) {
				String text = para.getText();
				if (text.startsWith("Q")) {
					question.append(text);
				} else if (text.contains("*")) {
					// answer
					Answer option = new Answer();
					option.setAnswer(DexterUtils.getForAnswer(text.trim()));
					option.setOption(DexterUtils.getOption(text).trim());
					options.add(option);
					correctAnswer.setAnswer(DexterUtils.getForAnswer(text.trim()));
					correctAnswer.setOption(DexterUtils.getOption(text).trim());
				} else {
					// option
					Answer option = new Answer();
					option.setAnswer(DexterUtils.getAnswer(text.trim()));
					option.setOption(DexterUtils.getOption(text).trim());
					options.add(option);
				}
				if (text.startsWith("d")) {
					// add a exam object
					Question bank = new Question();
					bank.setQuestionNumber(DexterUtils.getQuestionNumber(question.toString()));
					bank.setQuestion(DexterUtils.getQuestion(question.toString().trim()));
					answerRepository.save(correctAnswer);
					answerRepository.saveAll(options);
					bank.setCorrectAnswer(correctAnswer);
					bank.setAnswers(options);
					bank.setSuccess(Boolean.TRUE);
					questionRepository.save(bank);
					questionBanks.add(bank);
					System.out.println("Question: " + bank.toString());
					options = new ArrayList<Answer>();
					question = new StringBuilder();
					correctAnswer = new Answer();
				}
			}
			return questionBanks;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Question> parseDocxFile(MultipartFile file) {
		try {
			return readDocFile(convert(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Question> getQuestions(int page, int size) {
		return questionRepository.findAll(new PageRequest(page, size)).getContent();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<Answer> getAnswers(int page, int size) {
		return answerRepository.findAll(new PageRequest(page, size)).getContent();
	}

	@Override
	public List<Answer> getAnswerByQuestion(int id) {
		return answerRepository.findByQuestion(questionRepository.findById(id).get());
	}
}
