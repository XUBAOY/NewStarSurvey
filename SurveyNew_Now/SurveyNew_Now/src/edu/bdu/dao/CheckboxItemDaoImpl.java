package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.CheckboxItem;

/**
 * �������ڣ�2011��8��21��
 * �汾 1.0
 * @author lenov
 *
 */
public class CheckboxItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * ���������ض�ѡ������б�
	 * @return
	 */
	public List getCheckboxList()
	{
		   List retList = new ArrayList();//��ѯ��ѡ���б�
		   
			String sql = "select * from CheckboxTable";//��ѡ��������ѯ
			
			super.openConn();
			
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					CheckboxItem item = new CheckboxItem();//��ѡ����ʵ����
					
					item.setCheckboxID(rs.getInt("CheckboxID"));//��ö�ѡ������
					
					item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//��ö�ѡ����������������
					
					item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//��ö�ѡ���������������еı��
					
					item.setCheckboxCaption(rs.getString("CheckboxCaption"));//��ö�ѡ�����
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//��ö�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
					
					item.setSelectCount(rs.getInt("SelectedCount"));//��ö�ѡ�ѡ�����
					
					retList.add(item);//���ѡ����
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
		   return retList;//���ض�ѡ���б�
	}
	
	
	/**
	 * ���ݶ�ѡ������������ı�Ų�ѯ��ѡ��
	 * @param CheckboxOwnerID
	 * @return
	 */
	public List getCheckboxList(int CheckboxOwnerID)
	{
		    List retList = new ArrayList();//��ѯ��ѡ���б�
		   
			String sql = "select * from CheckboxTable where CheckboxOwnerID = " + CheckboxOwnerID;//��ѡ��������ѯ
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					CheckboxItem item = new CheckboxItem();//��ѡ����ʵ����
					
					item.setCheckboxID(rs.getInt("CheckboxID"));//��ö�ѡ������
					
					item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//��ö�ѡ����������������
					
					item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//��ö�ѡ���������������еı��
					
					item.setCheckboxCaption(rs.getString("CheckboxCaption"));//��ö�ѡ�����
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//��ö�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
					
					item.setSelectCount(rs.getInt("SelectedCount"));//��ö�ѡ�ѡ�����
					
					retList.add(item);//���ѡ���� 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
		   return retList;//���ض�ѡ���б�
	}
	
	/**
	 * ���ݶ�ѡ���Ų�ѯ��ѡ��
	 * @param CheckboxID
	 * @return
	 */
	public CheckboxItem getCheckbox(int CheckboxID)
	{
		CheckboxItem item = null;//��ѡ���ʼ��
		
		String sql = "select * from CheckboxTable where CheckboxID = " + CheckboxID;//��ѡ��������ѯ
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				item = new CheckboxItem();//��ѡ����ʵ����
				
				item.setCheckboxID(rs.getInt("CheckboxID"));//��ö�ѡ������
				
				item.setCheckboxOwnerID(rs.getInt("CheckboxOwnerID"));//��ö�ѡ����������������
				
				item.setCheckboxIndex(rs.getInt("CheckboxIndex"));//��ö�ѡ���������������еı��
				
				item.setCheckboxCaption(rs.getString("CheckboxCaption"));//��ö�ѡ�����
				
				item.setDefaultSelected(rs.getInt("DefaultSelected"));//��ö�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
				
				item.setSelectCount(rs.getInt("SelectedCount"));//��ö�ѡ�ѡ�����
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return item;//���ض�ѡ��ʵ��
	}

    /**
     * ���ݶ�ѡ�����޸Ķ�ѡ��
     * @param item
     * @return
     */
	public boolean updateCheckboxItem(CheckboxItem item)
    {
		 boolean flag = false;//�޸Ľ�����
		  
		  String updateSql = "update CheckboxTable set CheckboxIndex = ?,CheckboxCaption = ?,SelectedCount = ?" +
		  		" where CheckboxID = ?";//���µ�sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
			 
			  super.pstmt.setInt(1, item.getCheckboxIndex());//���ö�ѡ����
			  
			  super.pstmt.setString(2, item.getCheckboxCaption());//���ö�ѡ�����
			  
			  super.pstmt.setInt(3, item.getSelectCount());//��ѡ��ѡ�����
			  
			  super.pstmt.setInt(4, item.getCheckboxID());//��ѡ����
			  
			  int updateRows = super.pstmt.executeUpdate();//ִ�и������
			  
			  if(updateRows == 1)
			  {
			    flag = true;//��־���³ɹ�
			  }
			  
			}
			catch(SQLException e)
			{
				
			   e.printStackTrace();//������Ϣ��ӡ 
			}
			
			super.closeAll();//�ر����ݿ��������ݿ�����
		  
		   return flag;//���ؽ�����
    }

	
	/**
	 * ���ݶ�ѡ����ɾ����ѡ��
	 * @param CheckboxID
	 * @return
	 */
	public boolean deleteCheckboxItem(int CheckboxID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from CheckboxTable where CheckboxID = ?";//ɾ����ѡ���sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, CheckboxID);//Ϊsql����ѡ��id��ֵ
			  
			  int deleteRows = super.pstmt.executeUpdate();//ִ�и������
			  
			  if(deleteRows == 1)
			  {
			    flag = true;//��־ɾ���ɹ�
			  }
			  
			}
			catch(SQLException e)
			{
			   e.printStackTrace();//������Ϣ��ӡ 
			}
			
			super.closeAll();//�ر����ݿ��������ݿ�����
		  
		   return flag;//���ؽ�����
	}
	
	/**
	 * ���ݶ�ѡ��������������ɾ����ѡ��
	 * @param CheckboxOwnerID
	 * @return
	 */
	public boolean deleteCheckboxItemByOwner(int CheckboxOwnerID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from CheckboxTable where CheckboxOwnerID = ?";//ɾ����ѡ���sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, CheckboxOwnerID);//Ϊsql����ѡ��id��ֵ
			  
			  int deleteRows = super.pstmt.executeUpdate();//ִ�и������
			  
			  if(deleteRows >= 1)
			  {
			    flag = true;//��־ɾ���ɹ�
			  }
			  
			}
			catch(SQLException e)
			{
			   e.printStackTrace();//������Ϣ��ӡ 
			}
			
			super.closeAll();//�ر����ݿ��������ݿ�����
		  
		   return flag;//���ؽ�����
	}

	/**
	 * ��Ӷ�ѡ��
	 * @param item
	 * @return
	 */
	public boolean InsertCheckboxItem(CheckboxItem item)
	{
      boolean flag = false;//��ӽ�����
  	  
  	  String insertSql = "insert into CheckboxTable(CheckboxOwnerID,CheckboxIndex,CheckboxCaption,DefaultSelected," +
  	  		"SelectedCount) values(?,?,?,0,0)";//��ӵ�sql���
  	  
  	  super.openConn();//�����ݿ�����
  		
  		try
  		{
  		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
  		  
  		  super.pstmt.setInt(1, item.getCheckboxOwnerID());//���ö�ѡ�����������ʾ���
  		 
  		  super.pstmt.setInt(2,item.getCheckboxIndex());//���ö�ѡ��˳���
  		  
  		  super.pstmt.setString(3, item.getCheckboxCaption());//���ö�ѡ�����
  		  
  		  //super.pstmt.setInt(4, item.getSelectCount());//���ö�ѡ��������
  		  
  		  int insertRows = super.pstmt.executeUpdate();//ִ�и������
  		  
  		  if(insertRows == 1)
  		  {
  		    flag = true;//��־����ɹ�
  		  }
  		  
  		}
  		catch(SQLException e)
  		{
  			
  		   e.printStackTrace();//������Ϣ��ӡ 
  		}
  		
  		super.closeAll();//�ر����ݿ��������ݿ�����
  	  
  	  return flag;//���ؽ�����
	}
}
