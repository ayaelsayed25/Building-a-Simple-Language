package javacc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;

class LanguageTest {
	

	@Test
	void test1() {
		String expected = testing("test1.txt");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test2() {//empty input
		String expected = testingString("");
		assertEquals(expected,"Syntax error");
	}
	
	@Test
	void test3() {
		String expected = testing("test3.txt");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test4() {
		
		String expected = testingString("iff d == 5 then skip else skip");
		assertEquals(expected,"Syntax error");
	}
	
	@Test
	void test5() {
		String expected = testingString("if ff then f := 500 else f := f + 1");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test6() {
		String expected = testingString("if d == 5 then fff := 500 else skip");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test7() {
		String expected = testing("test7.txt");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test8() {
		String expected = testingString("ff := 5");
		assertEquals(expected,"Syntax error");
	}
	
	@Test
	void test9() {
		String expected = testingString("while tt do while ff do skip");
		assertEquals(expected,"Parsed correctly");
	}
	
	@Test
	void test10() {
		String expected = testingString("while tt ^ ff ^ ! f == 5 do while ! ff ^ a == 5 + 1 do skip");
		assertEquals(expected,"Parsed correctly");
	}
	
	static Language parser = null ;
	static String testing(String fileName) {
		try { 	
			String ss = System.getProperty("user.dir") +"\\src\\javacc\\Tests\\";
			File initialFile = new File(ss+fileName);
			
		    InputStream targetStream = new FileInputStream(initialFile);
		    
		    if( parser == null) {
		    	parser = new Language(targetStream);
		    }
		    else {
		    	parser.ReInit(targetStream);
		    } 
		    
			String temp = parser.words();
			return temp;
		} catch (Exception e) {
			return "Syntax error";
		}
	}
	static String testingString(String text) {
	    try { 	
    	    InputStream targetStream = new ByteArrayInputStream(text.getBytes());
		    if( parser == null) {
		    	parser = new Language(targetStream);
		    }
		    else {
		    	parser.ReInit(targetStream);
		    } 
			String temp = parser.words();
			return temp;
		} catch (ParseException e) {
			return "Syntax error";
		}  
	}
}
