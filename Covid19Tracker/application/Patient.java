package application;

class Patient {

	/*
	 * int and long, -1 means not filled in JSON Data file
	 * Strings, "?" means not filled in JSON Data file
	 *        , FOR detectedDistrict "o" means OTHER DISTRICTS OUT OF INDIA
	 * char, '?' means not filled in JSON Data file
	 * 
	 * */
	
	private int age;					//": "25" , -1 means not filled
	private String currentStatus;		//": "Recovered" , "?" means not filled
	private String dateAnnounced;		//": "30/01/2020", truncate last 5 characters while displaying , "?" means not filled
	private String detectedDistrict;	//": "Thrissur" , "?" means not filled , o means other detected district
	private char gender;				// m/f , '?' means not filled
	private String nationality;			//"India" , "?" means not filled
	private long patientNumber;			// 8904 , -1 means not filled
	private String stateCode; 			//"KL" , "?" means not filled , State codes to names on https://kb.bullseyelocations.com/support/solutions/articles/5000695302-india-state-codes
	private String typeoftransmission; 	//"Imported" , "?" means not filled
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(String currentstatus) {
		this.currentStatus = currentstatus;
	}
	public String getDateAnnounced() {
		return dateAnnounced;
	}
	public void setDateAnnounced(String dateannounced) {
		this.dateAnnounced = dateannounced;
	}
	public String getDetectedDistrict() {
		return detectedDistrict;
	}
	public void setDetectedDistrict(String detectedDistrict) {
		this.detectedDistrict = detectedDistrict;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public long getPatientNumber() {
		return patientNumber;
	}
	public void setPatientNumber(long patientnumber) {
		this.patientNumber = patientnumber;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getTypeoftransmission() {
		return typeoftransmission;
	}
	public void setTypeoftransmission(String typeoftransmission) {
		this.typeoftransmission = typeoftransmission;
	}
	
	
	
}
