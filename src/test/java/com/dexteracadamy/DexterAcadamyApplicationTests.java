package com.dexteracadamy;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.dexteracadamy.model.Answer;
import com.dexteracadamy.model.Question;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class DexterAcadamyApplicationTests {

	@Test
	public void contextLoads() {
	}

	//@Test
//	public void testReadingUserObjectsFromCsvData() throws Exception {
//		File testFile = new File("/home/zentere/user.csv");
//		List<User> people = DocumentController.readFile(testFile);
//		assertEquals(1, people.size());
//		User person1 = people.get(0);
//		assertEquals("1", person1.getSno());
//		assertEquals("user", person1.getFirstName());
//
//	}

//	@Test
//	public void testReadingPersonObjectsFromCsvData() throws Exception {
//		File testFile = new File("/home/zentere/Documents/people.csv");
//		List<Person> people = DocumentController.readPersonFile(testFile);
//		Person person1 = people.get(0);
//		assertEquals("user", person1.getFirstName());
//		assertEquals("Arun", person1.getLastName());
//
//	}
	
	@Test(expected = JsonMappingException.class)
	public void givenBidirectionRelation_whenSerializing_thenException()
	  throws JsonProcessingException {
	  
	    Question user = new Question("Q1", "This is Questiom?");
	    Answer item = new Answer();
	    item.setAnswer("Ans");
	    user.setAnswers(Arrays.asList(item));
	 
	    new ObjectMapper().writeValueAsString(item);
	}
	
	@Test
	public void givenBidirectionRelation_whenUsingJacksonReferenceAnnotation_thenCorrect()
			throws JsonProcessingException {

		Question user = new Question("Q1", "This is Questiom?");
		Answer item = new Answer();
		item.setAnswer("Ans");
		user.setAnswers(Arrays.asList(item));

		String result = new ObjectMapper().writeValueAsString(item);

		assertThat(result, containsString("book"));
		assertThat(result, containsString("John"));
	}
}
