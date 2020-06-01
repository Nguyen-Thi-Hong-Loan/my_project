package load_file;

import java.sql.Date;

public class InformationPerson {
	private int stt, phone, CMND;
	private Date dateBirth;
	private String fName, lName, gender, personal, address, email;

	public InformationPerson() {

	}
	public InformationPerson(int stt, String lName, String fName, String gender, Date dateBirth, String personal,
			String address, String email, int phone, int CMND) {
		super();
		this.stt = stt;
		this.phone = phone;
		this.CMND = CMND;
		this.dateBirth = dateBirth;
		this.fName = fName;
		this.lName = lName;
		this.gender = gender;
		this.personal = personal;
		this.address = address;
		this.email = email;
	}

	public int getStt() {
		return stt;
	}

	public int getPhone() {
		return phone;
	}

	public int getCMND() {
		return CMND;
	}

	public Date getDateBirth() {
		return dateBirth;
	}

	public String getfName() {
		return fName;
	}

	public String getlName() {
		return lName;
	}

	public String getGender() {
		return gender;
	}

	public String getPersonal() {
		return personal;
	}
	

	public void setStt(int stt) {
		this.stt = stt;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public void setCMND(int cMND) {
		CMND = cMND;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public void setPersonal(String personal) {
		this.personal = personal;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "InformationPerson [stt=" + stt + ", phone=" + phone + ", CMND=" + CMND + ", dateBirth=" + dateBirth
				+ ", fName=" + fName + ", lName=" + lName + ", gender=" + gender + ", personal=" + personal
				+ ", address=" + address + ", email=" + email + "]";
	}

	
}
