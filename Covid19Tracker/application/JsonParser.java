package application;

import java.io.*;

import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

class JsonParser {

	private Patient[] patients;
	
	
	
	public Patient[] getPatients() {
		return patients;
	}


	public void liveURLToJSONToPatientObj() {

		try {
			InputStream in = new URL("https://api.covid19india.org/raw_data.json").openStream();
			Files.copy(in, Paths.get("jsoncovid19.json"), StandardCopyOption.REPLACE_EXISTING);
			parseJSONFile();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void parseJSONFile() {

		try {
			Object obj = new JSONParser().parse(new FileReader("jsoncovid19.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray rawData = (JSONArray) jo.get("raw_data");

			patients = new Patient[rawData.size()];

			for (int i = 0; i < patients.length; i++) {
				// PatientItem has all info about each patient
				JSONObject patientItem = (JSONObject) rawData.get(i);
				patients[i] = new Patient();

				// age
				String age = (String) patientItem.get("agebracket");
				// to handle values like 1.5 , 25-30 and empty strings
				if (age.equals("") || age.contains("-") || age.contains(".")) {
					patients[i].setAge(-1);
				} else {
					int temp = Integer.parseInt(age);
					patients[i].setAge(Integer.valueOf(temp));
				}

				// currentStatus
				if (patientItem.get("currentstatus").equals("")) {
					patients[i].setCurrentStatus("?");
				} else {
					String currentStatus = (String) patientItem.get("currentstatus");
					patients[i].setCurrentStatus(currentStatus);
				}

				// dateAnnounced
				if (patientItem.get("dateannounced").equals("")) {
					patients[i].setDateAnnounced("?");
				} else {
					String dateAnnounced = (String) patientItem.get("dateannounced");
					patients[i].setDateAnnounced(dateAnnounced);
				}

				// detectedDistrict
				String tempDetectedDistrict = (String) patientItem.get("detecteddistrict");
				if (tempDetectedDistrict.equals("")) {
					patients[i].setDetectedDistrict("?");
				} else if (tempDetectedDistrict.contains("*")) {
					patients[i].setDetectedDistrict("o");
				} else {
					String detectedDistrict = (String) patientItem.get("detecteddistrict");
					patients[i].setDetectedDistrict(detectedDistrict);
				}

				// gender
				if (patientItem.get("gender").equals("")) {
					patients[i].setGender('?');
				} else {
					String genderString = (String) patientItem.get("gender");
					char gender = genderString.charAt(0);
					patients[i].setGender(gender);
				}

				// nationality
				if (patientItem.get("nationality").equals("")) {
					patients[i].setNationality("?");
				} else {
					String nationality = (String) patientItem.get("nationality");
					patients[i].setNationality(nationality);
				}

				// patientNumber
				String patientNumber = (String) patientItem.get("patientnumber");
				// to handle values like 1.5 , 25-30 and empty strings
				if (patientNumber.equals("") || patientNumber.contains("-") || patientNumber.contains(".")) {
					patients[i].setPatientNumber(-1);
				} else {
					int temp = Integer.parseInt(patientNumber);
					patients[i].setPatientNumber(Integer.valueOf(temp));
				}
				
				//statecode
				if (patientItem.get("statecode").equals("")) {
					patients[i].setStateCode("?");
				} else {
					String statecode = (String) patientItem.get("statecode");
					patients[i].setStateCode(statecode);
				}
				
				//typeoftransmission
				if (patientItem.get("typeoftransmission").equals("")) {
					patients[i].setTypeoftransmission("?");
				} else {
					String typeoftransmission = (String) patientItem.get("typeoftransmission");
					patients[i].setTypeoftransmission(typeoftransmission);
				}
				
				//statuschangedate
				if (patientItem.get("statuschangedate").equals("")) {
					patients[i].setStatusChangeDate("?");
				} else {
					String statusChangeDate = (String) patientItem.get("statuschangedate");
					patients[i].setStatusChangeDate(statusChangeDate);
				}

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
