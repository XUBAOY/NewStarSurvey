package edu.bdu.entity;

/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */
public class RadioItem {
	
	private int radioID;//����������� 
	
	private int radioOwnerID;//�������������������id
	
	private int radioIndex;//����������ڴ����е�˳��
	
	private String radioCaption;//������������
	
	private int defaultSelected;//Ĭ���Ƿ�ѡ��
	
	private int selectCount;//���������¼

	///////////////////////�����������/////////////////////////////
	public int getRadioID() {
		return radioID;
	}

	public void setRadioID(int radioID) {
		this.radioID = radioID;
	}

	/////////////////////////�������������������ı��/////////////////
	public int getRadioOwnerID() {
		return radioOwnerID;
	}

	public void setRadioOwnerID(int radioOwnerID) {
		this.radioOwnerID = radioOwnerID;
	}

	/////////////////////�����ڴ˵������е�˳��////////////////////////
	public int getRadioIndex() {
		return radioIndex;
	}

	public void setRadioIndex(int radioIndex) {
		this.radioIndex = radioIndex;
	}

	//////////////////////����ı���/////////////////////////////
	public String getRadioCaption() {
		return radioCaption;
	}

	public void setRadioCaption(String radioCaption) {
		this.radioCaption = radioCaption;
	}

	////////////////////Ĭ���Ƿ�ѡ��////////////////////////////////////
	public int getDefaultSelected() {
		return defaultSelected;
	}

	public void setDefaultSelected(int defaultSelected) {
		this.defaultSelected = defaultSelected;
	}

	///////////////////�������///////////////////////////////////////
	public int getSelectCount() {
		return selectCount;
	}

	public void setSelectCount(int selectCount) {
		this.selectCount = selectCount;
	}
	
	
	
	

}
