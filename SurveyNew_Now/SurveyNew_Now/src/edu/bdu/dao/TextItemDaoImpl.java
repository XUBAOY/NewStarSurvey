package edu.bdu.dao;
/**
 * �������ڣ�2011��8��21��
 * �汾 1.0
 * @author lenov
 *
 */
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import edu.bdu.entity.TextItem;
public class TextItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * ������ֵ������б�
	 * @return
	 */
	public List getTextList()
	{
	   List retList = new ArrayList();//��ѯ���ֵ������б�
	   
		String sql = "select * from TextTable";//���ֵ�������ѯ
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				TextItem item = new TextItem();//ʵ�������������
				
				item.setTextID(rs.getInt("TextID"));//������ִ�����
				
				item.setTextOwnerID(rs.getInt("TextOwnerID"));//�����������
				
				item.setTextCaption(rs.getString("TextCaption"));//������ִ������
				
				item.setTextContent(rs.getString("TextContent"));//������ִ�������
				
				retList.add(item);//�����ִ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
	   return retList;//�����������б�
	}

	/**
	 * �����������Ų�ѯ������
	 * @param TextID
	 * @return item
	 */
	public TextItem getText(int TextID)
	{
		   TextItem item = null;//���������
		   
			String sql = "select * from TextTable where TextID = " + TextID;//���ֵ�������Ų�ѯ
	
			super.openConn();
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				if(super.rs.next()) {
					
					item = new TextItem();//ʵ�������������
					
					item.setTextID(rs.getInt("TextID"));//������ִ�����
					
					item.setTextOwnerID(rs.getInt("TextOwnerID"));//�����������
					
					item.setTextCaption(rs.getString("TextCaption"));//������ִ������
					
					item.setTextContent(rs.getString("TextContent"));//������ִ�������
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
		   return item;//����������
	}
	
	/**
	 * �������ֵ������������ʾ���
	 * @param TextOwnerID
	 * @return
	 */
	public List getTextListByOwner(int TextOwnerID)
	{
		List retList = new ArrayList();//��ѯ���ֵ������б�
		   
		String sql = "select * from TextTable where TextOwnerID = " + TextOwnerID;//���ֵ�������Ų�ѯ
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				TextItem item = new TextItem();//ʵ�������������
				
				item.setTextID(rs.getInt("TextID"));//������ִ�����
				
				item.setTextOwnerID(rs.getInt("TextOwnerID"));//�����������
				
				item.setTextCaption(rs.getString("TextCaption"));//������ִ������
				
				item.setTextContent(rs.getString("TextContent"));//������ִ�������
				
				retList.add(item);//�����ִ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
	   return retList;//�����������б�
	}
    
	/**
	 * �������ִ����Ÿ������ִ���
	 * @param item
	 * @return
	 */
	public boolean updateTextItem(TextItem item)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String updateSql = "update TextTable set TextCaption = ?,TextContent = ?" +
		  		" where TextID = ?";//���µ�sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
			 
			  super.pstmt.setString(1, item.getTextCaption());//�������ִ������
			  
			  super.pstmt.setString(2, item.getTextContent());//�������ִ�������
			  
			  super.pstmt.setInt(3, item.getTextID());//�������ִ�����
			  
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
     * ������������ɾ��������
     * @param TextID
     * @return
     */
	public boolean deleteTextItem(int TextID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from TextTable where TextID = ?";//ɾ���������sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, TextID);//Ϊsql���������id��ֵ
			  
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
	/***
	 * �������������������ʾ���ɾ��������
	 * @param TextOwnerID
	 * @return
	 */
	public boolean deleteTextItemByOwner(int TextOwnerID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from TextTable where TextOwnerID = ?";//ɾ���������sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, TextOwnerID);//Ϊsql������������������ʾ��Ÿ�ֵ
			  
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
	 * ���������
	 * @param item
	 * @return
	 */
    public boolean InsertTextItem(TextItem item)
    {
      boolean flag = false;//��ӽ�����
  	  
  	  String insertSql = "insert into TextTable(TextOwnerID,TextCaption,TextContent) values(?,?,?)";//��ӵ�sql���
  	  
  	  super.openConn();//�����ݿ�����
  		
  		try
  		{
  		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
  		  
  		  super.pstmt.setInt(1, item.getTextOwnerID());//�������������������ʾ���
  		 
  		  super.pstmt.setString(2,item.getTextCaption());//������������
  		  
  		  super.pstmt.setString(3, item.getTextContent());//��������������
  		  
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
