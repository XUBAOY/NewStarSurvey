package edu.bdu.entity;

/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */

import java.util.Date;

public class Survey {
	
	private int surveyID;//�����ʾ���
	
	private String surveyTitle;//�����ʾ����
	
	private int surveyOwnerID;//�����ʾ����߱��
	
	private String surveyLink;//�����ʾ�˵������������
	
	private Date surveyCreateDate;//�����ʾ���ʱ��
	
	private String surveyExpirationDate;//�����ʾ����ʱ������//ExpirationDate
	
	

	///////////////////////////�����ʾ�id/////////////////////////////////
	public int getSurveyID() {
		return surveyID;
	}

	public void setSurveyID(int surveyID) {
		this.surveyID = surveyID;
	}
   
	/////////////////////////�����ʾ����/////////////////////////////////
	public String getSurveyTitle() {
		return surveyTitle;
	}

	public void setSurveyTitle(String surveyTitle) {
		this.surveyTitle = surveyTitle;
	}

	///////////////////////�����ʾ���id////////////////////////////////////
	public int getSurveyOwnerID() {
		return surveyOwnerID;
	}

	public void setSurveyOwnerID(int surveyOwnerID) {
		this.surveyOwnerID = surveyOwnerID;
	}
  
	/////////////////////�����ʾ��������///////////////////////////////////
	public String getSurveyLink() {
		return surveyLink;
	}

	public void setSurveyLink(String surveyLink) {
		this.surveyLink = surveyLink;
	}

	////////////////////�����ʾ���ʱ��////////////////////////////////////
	public Date getSurveyCreateDate() {
		return surveyCreateDate;
	}

	public void setSurveyCreateDate(Date surveyCreateDate) {
		this.surveyCreateDate = surveyCreateDate;
	}

	////////////////////�����ʾ����ʱ������///////////////////////////////
	public String getSurveyExpirationDate() {
		return surveyExpirationDate;
	}

	public void setSurveyExpirationDate(String surveyExpirationDate) {
		this.surveyExpirationDate = surveyExpirationDate;
	}
	
	

}
