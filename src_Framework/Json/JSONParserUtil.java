package Json;

import java.io.File;
import java.io.FileInputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import Json.TestCaseObject;

public class JSONParserUtil {

	public static List<TestCaseObject> allTestCases;
	
	public static void writeJsonFile(TestCaseObject obj) {
		if(allTestCases == null) {
			getAllTestCases();
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			
			if(searchObjectById(obj.getId()) != null){
				updateObject(obj);
			}else {
				allTestCases.add(obj);
			}
			
			mapper.writeValue(new File(Constants_JSON.JSON_FILE_PATH), allTestCases);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public static void updateObject(TestCaseObject obj) {
		boolean encontrado = false;

		for(int i = 0; i < allTestCases.size() && !encontrado; i++) {
			if (allTestCases.get(i).getId().equalsIgnoreCase(obj.getId())) {
				allTestCases.remove(i);
				allTestCases.add(obj);
				encontrado = true;
				//writeJsonFile(obj);
			}
		}
	}

	public static TestCaseObject searchObjectById(String id) {
		if (allTestCases == null) {
			getAllTestCases();	
		}
		Iterator<TestCaseObject> it = allTestCases.iterator();
		TestCaseObject retorno = null;
		boolean encontrado = false;

		while (it.hasNext() && !encontrado) {
			TestCaseObject object = (TestCaseObject) it.next();
			if (object.getId().equalsIgnoreCase(id)) {
				retorno = object;
				encontrado = true;
			}

		}
		return retorno;
	}

	public static String getAttribute(String attr, String id) {
		TestCaseObject object = searchObjectById(id);
		if(object != null) {
			return (String) object.getAttributes().get(attr);
		}
		return null;
	}

	public static List<TestCaseObject> getAllTestCases() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			FileInputStream fileInputStream = new FileInputStream(Constants_JSON.JSON_FILE_PATH);
			allTestCases = mapper.readValue(fileInputStream, new TypeReference<List<TestCaseObject>>() {});
			return allTestCases;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		allTestCases = new ArrayList<TestCaseObject>();
		return allTestCases;
	}
	
	public static void writeLog(String message) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.enable(SerializationFeature.INDENT_OUTPUT);
			StringBuilder fullMessage = new StringBuilder();
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			LocalDateTime now = LocalDateTime.now();
			fullMessage.append(dtf.format(now)).append(message);
			mapper.writeValue(new File(Constants_JSON.JSON_LOG_PATH), allTestCases);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
