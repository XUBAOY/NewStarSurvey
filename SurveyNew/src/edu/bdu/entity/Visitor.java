package edu.bdu.entity;

import java.util.Date;

/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */

public class Visitor {
	
	private String visitorIP;//�����ʾ������ip��ַ
	
	private Date visiteDateTime;//�����ʾ�����߷���ʱ��
	
	private int visitorNumber;//�����ʾ�������ܱ�š�����Ψһ��־ 
	
	private int visiteSurveyID;//�����ʾ�����߷��ʵĵ����ʾ�ID
	
	private String visiteSurveyData;//�����ʾ�����߻ش�ѡ���ѡ������Ĵ�

	////////////////������ip//////////////////////////////////
	public String getVisitorIP() {
		return visitorIP;
	}

	public void setVisitorIP(String visitorIP) {
		this.visitorIP = visitorIP;
	}

	///////////////�����߷���ʱ��////////////////////////////
	public Date getVisiteDateTime() {
		return visiteDateTime;
	}

	public void setVisiteDateTime(Date visiteDateTime) {
		this.visiteDateTime = visiteDateTime;
	}

	////////////////�����߱��/////////////////////////////
	public int getVisitorNumber() {
		return visitorNumber;
	}

	public void setVisitorNumber(int visitorNumber) {
		this.visitorNumber = visitorNumber;
	}

	/////////////////�����߷��ʵĵ����ʾ�id//////////////////
	public int getVisiteSurveyID() {
		return visiteSurveyID;
	}

	public void setVisiteSurveyID(int visiteSurveyID) {
		this.visiteSurveyID = visiteSurveyID;
	}

	/////////////////�����߻ش�ѡ����ѡ�����///////////////
	public String getVisiteSurveyData() {
		return visiteSurveyData;
	}

	public void setVisiteSurveyData(String visiteSurveyData) {
		this.visiteSurveyData = visiteSurveyData;
	}
	
	
	
	
	

}
