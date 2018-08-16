package com.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import android.test.AndroidTestCase;
import android.util.Log;

import com.domain.Person;
import com.service.DOMPersonService;
import com.service.PullPersonService;
import com.service.SAXForHandler;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTest";
	public void testSAXGetPersons() throws Throwable{
		InputStream inputStream = this.getClass().getClassLoader().
				getResourceAsStream("wang.xml");
		SAXForHandler saxForHandler = new SAXForHandler();
		SAXParserFactory spf = SAXParserFactory.newInstance();
    	SAXParser saxParser = spf.newSAXParser();
    	saxParser.parse(inputStream, saxForHandler);
    	List<Person> persons = saxForHandler.getPersons();
    	inputStream.close();
    	for(Person person:persons){
    		Log.i(TAG, person.toString());
    	}
	}
	
	public void testDOMgetPersons() throws Throwable{
		InputStream inStream = this.getClass().getClassLoader().
				getResourceAsStream("wang.xml");
		List<Person> persons = DOMPersonService.getPersons(inStream);
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testPullgetPersons() throws Throwable{
		InputStream inStream = this.getClass().getClassLoader().getResourceAsStream("wang.xml");
		List<Person> persons = PullPersonService.getPersons(inStream);
		for(Person person : persons){
			Log.i(TAG, person.toString());
		}
	}
	
	public void testSave() throws Throwable{
		File file = new File(this.getContext().getFilesDir(), "wang.xml");
		FileOutputStream outStream = new FileOutputStream(file);
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person(90, "wang", (short)13));
		persons.add(new Person(35, "jia", (short)23));
		persons.add(new Person(78, "Android", (short)33));
		PullPersonService.save(persons, outStream);
	}
}
