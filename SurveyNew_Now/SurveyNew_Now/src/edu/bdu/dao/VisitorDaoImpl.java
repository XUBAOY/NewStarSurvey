package edu.bdu.dao;

import java.sql.SQLException;
import java.util.*;
import edu.bdu.entity.Visitor;

/**
 * ����ʱ�䣺2011��8��23��
 * @author lenov
 *
 */

public class VisitorDaoImpl extends BaseJdbcDao{
	
	/**
	 * ��������ѯ���з�������Ϣ
	 * @return
	 */
	public List getVisitorList()
	{
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable";//��ѯȫ��������
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//������ʵ����
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//��÷����߱��
				
				item.setVisitorIP(rs.getString("VisitorIP"));//������ip��ַ
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//�����߷��ʵĵ����ʾ���
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//�����߷���ʱ��
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//�������ύ�ĵ�ѡ�Ͷ�ѡ���ֵ
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//�������б�
	}
	
	/**
	 * ���ݵ����ʾ��Ų�ѯ��������Ϣ
	 * @param visiteSurveyID
	 * @return
	 */
	public List getVisitorList(int visiteSurveyID)
	{
	
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable where VisiteSurveyID = " + visiteSurveyID;//���ݵ����ʾ��Ų�ѯȫ��������
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//������ʵ����
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//��÷����߱��
				
				item.setVisitorIP(rs.getString("VisitorIP"));//������ip��ַ
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//�����߷��ʵĵ����ʾ���
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//�����߷���ʱ��
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//�������ύ�ĵ�ѡ�Ͷ�ѡ���ֵ
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//�������б�
	}
	
	/**
	 * ���ݷ����߱�Ų�ѯ������
	 * @param visitorNumber
	 * @return
	 */
	public Visitor getVisitor(int visitorNumber)
	{
		Visitor item = null;//�����߳�ʼ�� 
		
		String sql = "select * from VisitorTable where VisitorNumber =" + visitorNumber;//���ݷ����߱�Ų�ѯ������
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				item = new Visitor();//������ʵ����
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//��÷����߱��
				
				item.setVisitorIP(rs.getString("VisitorIP"));//������ip��ַ
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//�����߷��ʵĵ����ʾ���
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//�����߷���ʱ��
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//�������ύ�ĵ�ѡ�Ͷ�ѡ���ֵ
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return item;//�����߶���
	}
	
	/**
	 * ���ݷ�����ip��ַ��ѯ�������б�
	 * @param visitorIP
	 * @return
	 */
	public List getVisitorList(String visitorIP)
	{
	
		List retList = new ArrayList();
		
		String sql = "select * from VisitorTable where VisitorIP = '" + visitorIP + "'";//����ip��ַ��ѯȫ��������
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Visitor item = new Visitor();//������ʵ����
				
				item.setVisitorNumber(rs.getInt("VisitorNumber"));//��÷����߱��
				
				item.setVisitorIP(rs.getString("VisitorIP"));//������ip��ַ
				
				item.setVisiteSurveyID(rs.getInt("VisiteSurveyID"));//�����߷��ʵĵ����ʾ���
				
				item.setVisiteDateTime(rs.getDate("visiteDateTime"));//�����߷���ʱ��
				
				item.setVisiteSurveyData(rs.getString("VisiteSurveyData"));//�������ύ�ĵ�ѡ�Ͷ�ѡ���ֵ
				
				retList.add(item);//
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//�������б�
	}
	
	
	/**
	 * ���ݷ����߱���޸ķ��������ݣ��˷�����ǰ�׶β�ʹ�ã��Է���������������Ҫ
	 * @param visitor
	 * @return
	 */
	public boolean updateVisitor(Visitor visitor)
	{
	  boolean flag = false;//�޸Ľ�����
	  
	  String updateSql = "update VisitorTable set VisitorIP = ?,VisiteSurveyID = ?," +
	  		"VisiteSurveyData = ? where VisitorNumber = ?";//���µ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, visitor.getVisitorIP());//Ϊsql��������ip��ַ
		  
		  super.pstmt.setInt(2, visitor.getVisiteSurveyID());//Ϊsql�������߷��ʵĵ����ʾ���
		  
	      super.pstmt.setString(3, visitor.getVisiteSurveyData());//����������
		  
		  super.pstmt.setInt(4, visitor.getVisitorNumber());//Ϊ�����߱��
		 
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
	 * ���ݷ����߱��ɾ����������Ϣ
	 * @param visitorNumber
	 * @return
	 */
	public boolean deleteVisitor(int visitorNumber)
	{
		  boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from VisitorTable where VisitorNumber = ?";//ɾ����sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, visitorNumber);//Ϊsql�������߱�Ÿ�ֵ
			  
			  int deleteRows = super.pstmt.executeUpdate();//ִ��ɾ�����
			  
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
		  
		  return flag;//�����޸Ľ��
	}
	
	/**
	 * ���ݷ����߷��ʵĵ����ʾ�ı��ɾ����������Ϣ
	 * @param visiteSurveyID
	 * @return
	 */
	public boolean deleteVisitorsBySurveyId(int visiteSurveyID)
	{
		  boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from VisitorTable where VisiteSurveyID = ?";//ɾ����sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, visiteSurveyID);//Ϊsql�������߱�Ÿ�ֵ
			  
			  int deleteRows = super.pstmt.executeUpdate();//ִ��ɾ�����
			  
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
		  
		  return flag;//�����޸Ľ��
	}
	
	/**
	 * ��ӷ����� 
	 * @param visitor
	 * @return
	 */
	public boolean InsertVisitor(Visitor visitor)
	{
		  boolean flag = false;//��ӽ�����
		  
		  String insertSql = "insert into VisitorTable(VisitorIP,VisiteSurveyID,VisiteSurveyData) values(?,?,?)";//��ӵ�sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
			
			  super.pstmt.setString(1, visitor.getVisitorIP());//���÷�����ip��ַ
			  
			  super.pstmt.setInt(2, visitor.getVisiteSurveyID());;//���õ����ʾ���
			  
			  super.pstmt.setString(3, visitor.getVisiteSurveyData());//�����ʾ�ش�𰸼�¼
			
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
