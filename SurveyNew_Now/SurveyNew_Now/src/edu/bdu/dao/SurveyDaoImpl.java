package edu.bdu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.bdu.entity.Survey;




/**
 * �������ڣ�2011��8��19��
 * �汾 1.0
 * @author lenov
 *
 */
public class SurveyDaoImpl extends BaseJdbcDao{
	
	/**
	 * ������е����ʾ��б�
	 * @return
	 */
	public List getServeyList()
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable order by SurveyCreateDateTime desc";
		
	    //sql += " and SurveyOwnerID = " + surveyOwnerID;//��õ����ʾ����û����
		
		super.openConn();
		
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//��õ����ʾ�id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ����
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ����û����
				
				item.setSurveyLink(rs.getString("SurveyLink"));//��ȡ�����ʾ�����
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��ȡ�����ʾ���ʱ��
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��ȡ�����ʾ����ʱ��
				
				retList.add(item);//��ӵ����ʾ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص����ʾ��б�
	}
	
	
	/**
	 * ���ݵ����ʾ����߱�Ų��ҵ����ʾ�
	 * @param surveyOwnerID
	 * @return surveylist
	 */
	public List getServeyList(int surveyOwnerID)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
	    sql += " and SurveyOwnerID = " + surveyOwnerID;//��õ����ʾ����û����
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//��õ����ʾ�id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ����
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ����û����
				
				item.setSurveyLink(rs.getString("SurveyLink"));//��ȡ�����ʾ�����
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��ȡ�����ʾ���ʱ��
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��ȡ�����ʾ����ʱ��
				
				retList.add(item);//��ӵ����ʾ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص����ʾ��б�
	}
	
	/**
	 * ���ݵ����ʾ�Ĵ�����id��������ѯ�����ʾ��б�
	 * @param surveyOwnerID
	 * @param condition
	 * @return
	 */
	public List getServeyList(int surveyOwnerID,Survey condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
		if(surveyOwnerID != 0)
		{
		   sql += " and SurveyOwnerID = " + surveyOwnerID;//��õ����ʾ����û����
		}
		
		
		if(null != condition)
		{
			if(condition.getSurveyID() != 0) 
			{
				sql += " and SurveyID = " + condition.getSurveyID();//��õ����ʾ���
			}
			
			if(!isNullOrEmpty(condition.getSurveyTitle()))
			{
				sql += " and SurveyTitle like '%" + condition.getSurveyTitle() + "%'";//��ȡ�����ʾ����
			}
			
	
			if(!isNullOrEmpty(condition.getSurveyLink()))
			{
				sql += " and SurveyLink = '" + condition.getSurveyLink() + "'";//��õ����ʾ���������
			}
			
			if(condition.getSurveyCreateDate() != null)
			{
				Date date = condition.getSurveyCreateDate();
				
				String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();//��ʽ�����ڣ���ȷ����
				
			    sql += " and SurveyCreateDateTime = '" + strDate + "'";//��õ����ʾ�����ʱ��
			}
			
			if(!isNullOrEmpty(condition.getSurveyExpirationDate()))
			{
				
				String strDate = condition.getSurveyExpirationDate();//��ù�������
				
				
				
				sql += " and SurveyExpirationDateTime ='" + strDate + "'";//��õ����ʾ����õĹ���ʱ��
			}
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//��õ����ʾ�id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ����
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ����û����
				
				item.setSurveyLink(rs.getString("SurveyLink"));//��ȡ�����ʾ�����
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��ȡ�����ʾ���ʱ��
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��ȡ�����ʾ����ʱ��
				
				retList.add(item);//��ӵ����ʾ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص����ʾ��б�
	}
	/***
	 * ����������õ����ʾ��б���Ϣ
	 * @param condition
	 * @return
	 */
	
	public List getServeyList(Survey condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from SurveyTable where 1 = 1";
		
		if(null != condition)
		{
			if(condition.getSurveyID() != 0) 
			{
				sql += " and SurveyID = " + condition.getSurveyID();//��õ����ʾ���
			}
			
			if(!isNullOrEmpty(condition.getSurveyTitle()))
			{
				sql += " and SurveyTitle like '%" + condition.getSurveyTitle() + "%'";//��ȡ�����ʾ����
			}
			
			if(condition.getSurveyOwnerID() != 0)
			{
			   sql += " and SurveyOwnerID = " + condition.getSurveyOwnerID();//��õ����ʾ����û����
			}
			
			if(!isNullOrEmpty(condition.getSurveyLink()))
			{
				sql += " and SurveyLink = '" + condition.getSurveyLink() + "'";//��õ����ʾ���������
			}
			
			if(condition.getSurveyCreateDate() != null)
			{
				Date date = condition.getSurveyCreateDate();
				
				String strDate = date.getYear() + "-" + date.getMonth() + "-" + date.getDate();//��ʽ�����ڣ���ȷ����
				
			    sql += " and SurveyCreateDateTime = '" + strDate + "'";//��õ����ʾ�����ʱ��
			}
			
			if(!isNullOrEmpty(condition.getSurveyExpirationDate()))
			{
				String strDate = condition.getSurveyExpirationDate();//��ù�������
				
				sql += " and SurveyExpirationDateTime ='" + strDate + "'";//��õ����ʾ����õĹ���ʱ��
			}
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Survey item = new Survey();
				
				item.setSurveyID(rs.getInt("SurveyID"));//��õ����ʾ�id
				
				item.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ����
				
				item.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ����û����
				
				item.setSurveyLink(rs.getString("SurveyLink"));//��ȡ�����ʾ�����
				
				item.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��ȡ�����ʾ���ʱ��
				
				item.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��ȡ�����ʾ����ʱ��
				
				retList.add(item);//��ӵ����ʾ��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص����ʾ��б�
	}
	
	/**
	 * ���ݵ����ʾ�id��ȡ�����ʾ���Ϣ
	 * @param SurveyID
	 * @return Survey
	 */
	public Survey getSurvey(int surveyID)
	{
		Survey survey = null;//���������ʾ�ʵ��
		
		String sql = "select * from SurveyTable where SurveyID = ?";//��ѯ���
		
		super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//����sqlԤ�������ִ�в�ѯ
		  
		  pstmt.setInt(1,surveyID);//Ϊ�����ʾ�d��ֵ
		  
		  super.rs = super.pstmt.executeQuery();//ִ�е����ʾ�Ĳ�ѯ
		  
		  if(super.rs.next())
		  {
			  
			survey = new Survey();//��ʼ�������ʾ�
			
			survey.setSurveyID(rs.getInt("SurveyID"));//��ȡ�����ʾ���
			
			survey.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ�ı���
			
			survey.setSurveyLink(rs.getString("SurveyLink"));//��õ����ʾ��˵������
			
			survey.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ������û��ı��
			
			survey.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��õ����ʾ�������
			
			survey.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��õ����ʾ������������
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//������Ϣ��ӡ
		   
		}
		
		super.closeAll();//�ر����ݿ��������ݿ�����
		
		return survey;//�������ʾ���ʵ������
	}
	
	
	/**
	 * ���ݵ����ʾ�id��ȡ�����ʾ���Ϣ
	 * @param SurveyID
	 * @return Survey
	 */
	public Survey getServeyByTitle(String surveyTitle)
	{
		Survey survey = null;//���������ʾ�ʵ��
		
		String sql = "select * from SurveyTable where SurveyTitle = ?";//��ѯ���
		
		super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//����sqlԤ�������ִ�в�ѯ
		  
		  pstmt.setString(1,surveyTitle);//Ϊ�����ʾ�d��ֵ
		  
		  super.rs = super.pstmt.executeQuery();//ִ�е����ʾ�Ĳ�ѯ
		  
		  if(super.rs.next())
		  {
			  
			survey = new Survey();//��ʼ�������ʾ�
			
			survey.setSurveyID(rs.getInt("SurveyID"));//��ȡ�����ʾ���
			
			survey.setSurveyTitle(rs.getString("SurveyTitle"));//��õ����ʾ�ı���
			
			survey.setSurveyLink(rs.getString("SurveyLink"));//��õ����ʾ��˵������
			
			survey.setSurveyOwnerID(rs.getInt("SurveyOwnerID"));//��õ����ʾ������û��ı��
			
			survey.setSurveyCreateDate(rs.getDate("SurveyCreateDateTime"));//��õ����ʾ�������
			
			survey.setSurveyExpirationDate(rs.getString("SurveyExpirationDateTime"));//��õ����ʾ������������
		  }
		  
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//������Ϣ��ӡ
		   
		}
		
		super.closeAll();//�ر����ݿ��������ݿ�����
		
		return survey;//�������ʾ���ʵ������
	}
	
	/**
	 * ���ݵ����ʾ�id�޸ĵ����ʾ���Ϣ
	 * @param Survey
	 * @return boolean
	 */
	
	public boolean updateSurvey(Survey survey)
	{
	  boolean flag = false;//�޸Ľ�����
	  
	  String updateSql = "update SurveyTable set SurveyTitle = ?,SurveyLink = ?," +
	  		"SurveyExpirationDateTime = ? where SurveyID = ?";//���µ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, survey.getSurveyTitle());//Ϊsql�������ʾ���⸳ֵ
		  
		  super.pstmt.setString(2, survey.getSurveyLink());//Ϊsql�������ʾ�˵�����Ӹ�ֵ
		  
		  String expiration = survey.getSurveyExpirationDate();//��õ����ʾ������������
		  if(expiration != null && !expiration.equals(""))//����������ڲ�Ϊ��
		  {
			  String strDate = survey.getSurveyExpirationDate();//������ڵ�����
			  
			  
			  super.pstmt.setString(3, strDate);//Ϊ�����ʾ����ʱ������ 
		  }
		  else
		  {
			  super.pstmt.setDate(3,null);//������������ǿ�ֵ�Ļ�����ֵ
		  }
		  
		  super.pstmt.setInt(4, survey.getSurveyID());//Ϊ�����ʾ�id��ֵ
		 
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
	 * ���ݵ����ʾ����߱��ɾ�������ʾ�
	 * @param surveyOwnerID
	 * @return ���ز������
	 */
	public boolean deleteSurveyByOwner(int surveyOwnerID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from SurveyTable where��SurveyOwnerID = ?";//ɾ�������ʾ��sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			
			  super.pstmt.setInt(1, surveyOwnerID);//Ϊsql�������ʾ����߱��
			  
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
	 * ���ݵ����ʾ�idɾ�������ʾ���Ϣ
	 * @param userID
	 * @return boolean
	 */
	
	public boolean deleteSurvey(int surveyID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from SurveyTable where SurveyID = ?";//ɾ�������ʾ��sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, surveyID);//Ϊsql�������ʾ�id��ֵ
			  
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
		  
		   return flag;//���ؽ�����
	}
	
	/**
	 * ��ӵ����ʾ���Ϣ
	 * @param Survey
	 * @return boolean
	 */
	
	public boolean InsertSurvey(Survey survey)
	{
	  boolean flag = false;//��ӽ�����
	  
	  String insertSql = "insert into SurveyTable(SurveyTitle,SurveyLink,SurveyOwnerID,SurveyExpirationDateTime) values(?,?,?,?)";//��ӵ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, survey.getSurveyTitle());//Ϊsql�������ʾ����
		  
		  super.pstmt.setString(2, survey.getSurveyLink());//Ϊsql�������ʾ�˵�����Ӹ�ֵ
		  
          super.pstmt.setInt(3, survey.getSurveyOwnerID());//���������ʾ�����id
		  
          ///////////////���ù���ʱ��/////////////////
		  if(survey.getSurveyExpirationDate() != null)//����������ڲ�Ϊ��
		  {
			  super.pstmt.setString(4, survey.getSurveyExpirationDate());//Ϊ�����ʾ����ʱ������ 
		  }
		  else
		  {
			  super.pstmt.setString(4,null);//������������ǿ�ֵ�Ļ�����ֵ
		  }
		 
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
