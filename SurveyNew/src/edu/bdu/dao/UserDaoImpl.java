package edu.bdu.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.bdu.entity.User;

/**
 * �������ڣ�2011��8��19��
 * �汾 1.0
 * @author lenov
 *
 */
public class UserDaoImpl extends BaseJdbcDao {
	
	/**
	 * �û���¼��֤����
	 * @param user1
	 * @return
	 */
	public User loginCheck(User user1)
	{
        User user = new User();//�����û�ʵ��
        
        if(user1 == null)
        {
           return null;//���������û��ǿ�ֵ
        }
		
		String sql = "select * from UserTable where UserName = ?";//��ѯ���
		
		super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//����sqlԤ�������ִ�в�ѯ
		  
		  pstmt.setString(1,user1.getUserName());//Ϊ�û���ѯid��ֵ
		  
		  super.rs = super.pstmt.executeQuery();//����ִ�в�ѯ�û�
		  
		  if(super.rs.next())
		  {
				user.setUserID(rs.getInt("UserID"));//����û�id
				
				user.setUserName(rs.getString("UserName"));//����û�����
				
				user.setUserPassword(rs.getString("UserPassword"));//��ȡ�û�����
				
				user.setUserType(rs.getInt("UserType"));//��ȡ�û�����
		  }
		  else
		  {
			 user = null;//�����ѯ���Ϊ�㣬���û���ֵΪ�� 
		  }
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//������Ϣ��ӡ
		   
		}
		
		super.closeAll();//�ر����ݿ��������ݿ�����
		
		if(user == null)
		{
			return null;//���ؿ�ֵ
		}
		
		if(!user.getUserPassword().equals(user1.getUserPassword()))//�ж�����һ����
		{
		    user = null;//�û�����ֵ 
		}
		
		return user;//���û�����
	}
	
	/***
	 * �����������ģ���б���Ϣ
	 * @param condition
	 * @return
	 */
	
	public List getUserList(User condition)
	{
		List retList = new ArrayList();
		
		String sql = "select * from UserTable where 1 = 1";
		
		if(null != condition)
		{
			if(condition.getUserID() != 0) //��ģ��id����0ʱ����ģ����в�ѯ
			{
				sql += " and UserID = " + condition.getUserID();
			}
			
			if(!isNullOrEmpty(condition.getUserName()))
			{
				sql += " and UserName = '" + condition.getUserName() + "'";
			}
			
		}
		
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				User item = new User();
				
				item.setUserID(rs.getInt("UserID"));//����û�id
				
				item.setUserName(rs.getString("UserName"));//����û�����
				
				item.setUserPassword(rs.getString("UserPassword"));//��ȡ�û�����
				
				item.setUserType(rs.getInt("UserType"));//��ȡ�û�����
				
				
				retList.add(item);//����û��� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//�����û��б�
	}
	
	/**
	 * �����û�id��ȡ�û���Ϣ
	 * @param userID
	 * @return User
	 */
	public User getUser(int userID)
	{
		User user = new User();//�����û�ʵ��
		
		String sql = "select * from UserTable where UserID = ?";//��ѯ���
		
		super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(sql);//����sqlԤ�������ִ�в�ѯ
		  
		  pstmt.setInt(1,userID);//Ϊ�û���ѯid��ֵ
		  
		  super.rs = super.pstmt.executeQuery();//����ִ�в�ѯ�û�
		  
		  if(super.rs.next())
		  {
				user.setUserID(rs.getInt("UserID"));//����û�id
				
				user.setUserName(rs.getString("UserName"));//����û�����
				
				user.setUserPassword(rs.getString("UserPassword"));//��ȡ�û�����
				
				user.setUserType(rs.getInt("UserType"));//��ȡ�û�����
		  }
		  else
		  {
			 user = null;//�����ѯ���Ϊ�㣬���û���ֵΪ�� 
		  }
		}
		catch(SQLException e)
		{
			
		   e.printStackTrace();//������Ϣ��ӡ
		   
		}
		
		super.closeAll();//�ر����ݿ��������ݿ�����
		
		return user;//���û�����
	}
	
	/**
	 * �����û�id�޸��û���Ϣ
	 * @param User
	 * @return boolean
	 */
	
	public boolean updateUser(User user)
	{
	  boolean flag = false;//�޸Ľ�����
	  
	  String updateSql = "update UserTable set UserName = ?,UserPassword = ?," +
	  		"UserType = ? where UserID = ?";//���µ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, user.getUserName());//Ϊsql����û�����ֵ
		  
		  super.pstmt.setString(2, user.getUserPassword());//Ϊsql������븳ֵ
		  
		  super.pstmt.setInt(3,user.getUserType());//Ϊ�û����͸�ֵ
		  
		  super.pstmt.setInt(4, user.getUserID());//Ϊ�û�id��ֵ
		 
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
	 * �����û�idɾ���û���Ϣ
	 * @param userID
	 * @return boolean
	 */
	
	public boolean deleteUser(int userID)
	{
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from UserTable where UserID = ?";//���µ�sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			  super.pstmt.setInt(1, userID);//Ϊsql����û�id��ֵ
			  
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
	 * ��������û���Ϣ
	 * @param User
	 * @return boolean
	 */
	
	public boolean InsertUser(User user)
	{
	  boolean flag = false;//��ӽ�����
	  
	  String insertSql = "insert into UserTable(UserPassword,UserName,UserType) values(?,?,?)";//��ӵ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, user.getUserPassword());//Ϊsql����û�����ֵ
		  
		  super.pstmt.setString(2, user.getUserName());//Ϊsql������븳ֵ
		  
		  super.pstmt.setInt(3,user.getUserType());//Ϊ�û����͸�ֵ
		 
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
