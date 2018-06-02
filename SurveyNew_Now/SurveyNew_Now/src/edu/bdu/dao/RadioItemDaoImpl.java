package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.RadioItem;

/**
 * �������ڣ�2011��8��21��
 * �汾 1.0
 * @author lenov
 *
 */
public class RadioItemDaoImpl extends BaseJdbcDao{
	/**
	 * ���������ص�ѡ������б�
	 * @return
	 */
	public List getRadioList()
	{
		   List retList = new ArrayList();//��ѯ��ѡ���б�
		   
			String sql = "select * from RadioTable";//��ѡ��������ѯ
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					RadioItem item = new RadioItem();//��ѡ����ʵ����
					
					item.setRadioID(rs.getInt("RadioID"));//��õ�ѡ������
					
					item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//��õ�ѡ����������������
					
					item.setRadioIndex(rs.getInt("RadioIndex"));//��õ�ѡ���������������еı��
					
					item.setRadioCaption(rs.getString("RadioCaption"));//��õ�ѡ�����
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//��õ�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
					
					item.setSelectCount(rs.getInt("SelectedCount"));//��õ�ѡ�ѡ�����
					
					retList.add(item);//��ѡ���� 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
		   return retList;//���ص�ѡ���б�
	}

	/**
	 * ���ݵ�ѡ���Ų�ѯ��ѡ��
	 * @param RadioID
	 * @return
	 */
	public RadioItem getRadio(int RadioID)
	{   
		String sql = "select * from RadioTable where RadioID = " + RadioID;//��ѡ��������ѯ
		
		RadioItem item = null;//ʵ������ѡ��
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			if(super.rs.next()) {
				
				item = new RadioItem();//��ѡ����ʵ����
				
				item.setRadioID(rs.getInt("RadioID"));//��õ�ѡ������
				
				item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//��õ�ѡ����������������
				
				item.setRadioIndex(rs.getInt("RadioIndex"));//��õ�ѡ���������������еı��
				
				item.setRadioCaption(rs.getString("RadioCaption"));//��õ�ѡ�����
				
				item.setDefaultSelected(rs.getInt("DefaultSelected"));//��õ�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
				
				item.setSelectCount(rs.getInt("SelectedCount"));//��õ�ѡ�ѡ�����
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
	   return item;//���ص�ѡ��
	}

	/**
	 * ���ݵ�ѡ�������������Ų�ѯ��ѡ�б�
	 * @param RadioOwnerID
	 * @return
	 */
	public List getRadioList(int RadioOwnerID)
	{
		    List retList = new ArrayList();//��ѯ��ѡ���б�
		   
			String sql = "select * from RadioTable where RadioOwnerID =" + RadioOwnerID + 
			" order by RadioIndex";//��ѡ��������ѯ
			
			super.openConn();
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					RadioItem item = new RadioItem();//��ѡ����ʵ����
					
					item.setRadioID(rs.getInt("RadioID"));//��õ�ѡ������
					
					item.setRadioOwnerID(rs.getInt("RadioOwnerID"));//��õ�ѡ����������������
					
					item.setRadioIndex(rs.getInt("RadioIndex"));//��õ�ѡ���������������еı��
					
					item.setRadioCaption(rs.getString("RadioCaption"));//��õ�ѡ�����
					
					item.setDefaultSelected(rs.getInt("DefaultSelected"));//��õ�ѡ���Ƿ�Ĭ�ϣ��˽׶�δ��
					
					item.setSelectCount(rs.getInt("SelectedCount"));//��õ�ѡ�ѡ�����
					
					retList.add(item);//��ѡ���� 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
		   return retList;//���ص�ѡ���б�
	}

	/**
	 * ���ݵ�ѡ���Ÿ��µ�ѡ��
	 * @param item
	 * @return
	 */
    public boolean updateRadioItem(RadioItem item)
    {
		 boolean flag = false;//�޸Ľ�����
		  
		  String updateSql = "update RadioTable set RadioIndex = ?,RadioCaption = ?,SelectedCount = ?" +
		  		" where RadioID = ?";//���µ�sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
			 
			  super.pstmt.setInt(1, item.getRadioIndex());//���õ�ѡ����
			  
			  super.pstmt.setString(2, item.getRadioCaption());//���õ�ѡ�����
			  
			  super.pstmt.setInt(3, item.getSelectCount());//��ѡ��ѡ�����
			  
			  super.pstmt.setInt(4, item.getRadioID());//��ѡ����
			  
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
     * ���ݵ�ѡ����ɾ����ѡ��
     * @param RadioID
     * @return
     */
    public boolean deleteRadioItem(int RadioID)
    {
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from RadioTable where RadioID = ?";//ɾ����ѡ���sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, RadioID);//Ϊsql��䵥ѡ��id��ֵ
			  
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
     * ���ݵ�������ɾ����ѡ��
     * @param RadioOwnerID
     * @return
     */
    public boolean deleteRadioItemByOwner(int RadioOwnerID)
    {
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from RadioTable where RadioOwnerID = ?";//ɾ����ѡ���sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, RadioOwnerID);//Ϊsql��䵥ѡ�������������Ÿ�ֵ
			  
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
     * ��ӵ�ѡ��
     * @param item
     * @return
     */
    public boolean InsertRadioItem(RadioItem item)
    {
        boolean flag = false;//��ӽ�����
    	  
    	  String insertSql = "insert into RadioTable(RadioOwnerID,RadioIndex,RadioCaption,DefaultSelected," +
    	  		"SelectedCount) values(?,?,?,0,0)";//��ӵ�sql���
    	  
    	  super.openConn();//�����ݿ�����
    		
    		try
    		{
    		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
    		  
    		  super.pstmt.setInt(1, item.getRadioOwnerID());//���õ�ѡ�����������ʾ���
    		 
    		  super.pstmt.setInt(2,item.getRadioIndex());//���õ�ѡ��˳���
    		  
    		  super.pstmt.setString(3, item.getRadioCaption());//���õ�ѡ�����
    		  
    		  //super.pstmt.setInt(4, item.getSelectCount());//���õ�ѡ��������
    		  
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
