package com.dexteracadamy;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
}
