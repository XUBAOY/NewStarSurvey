package edu.bdu.entity;

/**
 * �������ڣ�2011��8��18��
 * �汾1.0
 * @author lenov
 *
 */
public class TextItem {

	private int textID;//���ִ�����
	
	private int textOwnerID;//���ִ���������������
	
	private String textCaption;//���ִ������
	
	private String textContent;//���ִ�������
	
	

	///////////////////////���ִ�����///////////////////////////
	public int getTextID() {
		return textID;
	}

	public void setTextID(int textID) {
		this.textID = textID;
	}

	////////////////////���ִ�������������ı��//////////////////
	public int getTextOwnerID() {
		return textOwnerID;
	}

	public void setTextOwnerID(int textOwnerID) {
		this.textOwnerID = textOwnerID;
	}

	////////////////////���ִ������///////////////////////////
	public String getTextCaption() {
		return textCaption;
	}

	public void setTextCaption(String textCaption) {
		this.textCaption = textCaption;
	}

	////////////////////���ִ�������///////////////////////////
	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}
	
}
