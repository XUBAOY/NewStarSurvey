package edu.bdu.entity;
/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */

public class CheckboxItem {
	
	private int checkboxID;//�������ѡ������
	
	private int checkboxOwnerID;//��ѡ����������������
	
	private int checkboxIndex;//��ѡ�����������������е�˳��
	
	private String checkboxCaption;//��ѡ�������
	
	private int defaultSelected;//Ĭ���Ƿ�ǰѡ����
	
	private int selectCount;//��ѡ����������

	//////////////////��ѡ������////////////////////////
	public int getCheckboxID() {
		return checkboxID;
	}

	public void setCheckboxID(int checkboxID) {
		this.checkboxID = checkboxID;
	}

	/////////////////��ѡ����������������//////////////////
	public int getCheckboxOwnerID() {
		return checkboxOwnerID;
	}

	public void setCheckboxOwnerID(int checkboxOwnerID) {
		this.checkboxOwnerID = checkboxOwnerID;
	}

	////////////////��ѡ�������������е�˳����///////////////
	public int getCheckboxIndex() {
		return checkboxIndex;
	}

	public void setCheckboxIndex(int checkboxIndex) {
		this.checkboxIndex = checkboxIndex;
	}

	///////////////////��ѡ�������////////////////////////////
	public String getCheckboxCaption() {
		return checkboxCaption;
	}

	public void setCheckboxCaption(String checkboxCaption) {
		this.checkboxCaption = checkboxCaption;
	}

	/////////////////////��ѡ�����Ƿ�Ĭ��ѡ��//////////////////////
	public int getDefaultSelected() {
		return defaultSelected;
	}

	public void setDefaultSelected(int defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	////////////////////��ѡ����������/////////////////////////
	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}
	
	

}
