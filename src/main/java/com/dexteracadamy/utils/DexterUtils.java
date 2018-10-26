/**
 * 
 */
package com.dexteracadamy.utils;

/**
 * @author zentere
 *
 */
public class DexterUtils {

	public static String getQuestionNumber(String string) {
		String questionNumber = string.substring(0, 3);
		return questionNumber;
	}
	
	public static String getQuestionNum(String string) {
		String questionNumber = string.substring(0, 4);
		return questionNumber;
	}
	
	public static String getQuestion(String question) {
		return question.replace(getQuestionNum(question), "");
	}
	
	public static String getAnswer(String answer) {
		return answer.replace(getOptions(answer), "");
	}
	
	public static String getForAnswer(String answer) {
		return answer.replace(getOptionsForAnswer(answer), "");
	}

	public static String getOption(String string) {
		String option = string.substring(0, 2).replaceAll("[^\\w\\s]", "");
		return option;
	}
	
	public static String getOptions(String string) {
		String option = string.substring(0, 2);
		return option;
	}
	
	public static String getOptionsForAnswer(String string) {
		String option = string.substring(0, 3);
		return option;
	}
	
	public static void main(String[] args) {
		String question2 = "a) Interference of light";
		System.out.println(question2.replace(getOptions("a) Interference of light"), "").trim());
	}
}
