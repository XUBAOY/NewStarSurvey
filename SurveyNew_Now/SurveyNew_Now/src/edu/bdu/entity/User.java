package edu.bdu.entity;

/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */
public class User {
	
	private int userID;//�û����
	
	private String userName;//�û�����
	
	private String userPassword;//�û�����
	
	private int userType;//�û����ʹ���

	///////////////////userid////////////////////////////////////////////
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
//////////////////////username////////////////////////////////////////
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	////////////////////////userpassword///////////////////////////////
	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	////////////////////////usertype////////////////////////////////////////
	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}
	

}
