package edu.bdu.entity;
/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */
public class Item {
	
	private int itemID;//��������
	
	private String itemCaption;//���������
	
	private int itemType;//���������͡���ѡ����ѡ�������ʴ𣬵�ǰ�׶�δ��
	
	private int itemAttribute;//���������ԡ�������˽�У��ֽ׶�δ�á�Ĭ��Ϊ˽��
	
	private int itemOwnerID;//��������߱��,��ǰ�׶�δ��
	
	private int itemOwnerSurveyID;//���������������ʾ�
	
	private int radioCount;//���������������˽׶�δ��
	
	
	

	//////////////////��������////////////////////////////
	public int getItemID() {
		return itemID;
	}

	public void setItemID(int itemID) {
		this.itemID = itemID;
	}

	////////////////���������///////////////////////////////
	public String getItemCaption() {
		return itemCaption;
	}

	public void setItemCaption(String itemCaption) {
		this.itemCaption = itemCaption;
	}

	//////////////////����������/////////////////////////////
	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}

	///////////////////���������Ա��/////////////////////////
	public int getItemAttribute() {
		return itemAttribute;
	}

	public void setItemAttribute(int itemAttribute) {
		this.itemAttribute = itemAttribute;
	}

	////////////////////��������߱��///////////////////////
	public int getItemOwnerID() {
		return itemOwnerID;
	}

	public void setItemOwerID(int itemOwnerID) {
		this.itemOwnerID = itemOwnerID;
	}

	///////////////////���������������ʾ�id////////////////////
	public int getItemOwnerSurveyID() {
		return itemOwnerSurveyID;
	}

	public void setItemOwnerSurveyID(int itemOwnerSurveyID) {
		this.itemOwnerSurveyID = itemOwnerSurveyID;
	}
 
	//////////////////������������///////////////////////////
	public int getRadioCount() {
		return radioCount;
	}

	public void setRadioCount(int radioCount) {
		this.radioCount = radioCount;
	}

}
