package edu.bdu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
	
    private static final String DRIVER_CLASS = ProReader.getProReader().getProperty("DRIVER_CLASS");//���ݿ�����
	
	private static final String DRIVER_URL = ProReader.getProReader().getProperty("DRIVER_URL");//���ӵ����ݿ�
	
	private static final String DATABASE_USER = ProReader.getProReader().getProperty("DATABASE_USER");//��½���ݵ��û�
	
	private static final String DATABASE_PASSWORD = ProReader.getProReader().getProperty("DATABASE_PASSWORD");//sql��ݵ�¼ʱ������
	
	/**
	 * ������ݿ����ӵ�ʵ����
	 * @return dbConnection
	 */
	public static Connection getConnection() throws Exception
	{
		Connection dbConnection = null;//�������ݶ���
		try
		{
			Class.forName(DRIVER_CLASS);//�������ݿ�����
			
			dbConnection = DriverManager.getConnection(DRIVER_URL,DATABASE_USER,DATABASE_PASSWORD);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return dbConnection;//�������ݿ�����
	}
}
