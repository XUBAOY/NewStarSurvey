package edu.bdu.dao;

import java.sql.*;
public abstract class BaseJdbcDao {
	
    protected Connection conn = null;
    protected Statement stmt = null;
    protected PreparedStatement pstmt = null;
    protected ResultSet rs = null;
    
    /**
     * �õ����ݿ�����
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return ���ݿ�����
     */
    public Connection openConn(){
    	
		try{
			this.conn = ConnectionManager.getConnection();			
		}catch(Exception sqlexception){
			sqlexception.printStackTrace();
		}
		return conn;
    }
    
    
    /**
     * �ͷ���Դ
     * @param conn ���ݿ�����
     * @param pstmt PreparedStatement����
     * @param rs �����
     */
    public void closeAll() {
        /*  ���rs���գ��ر�rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���pstmt���գ��ر�pstmt  */
        if(pstmt != null){
            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���stmt���գ��ر�stmt  */
        if(stmt != null){
            try { stmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���conn���գ��ر�conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
    
    
    /**
	 * �ж��ַ����Ƿ�Ϊnull���߿��ַ���
	 * @param title
	 * @return
	 */
	public boolean isNullOrEmpty(String str) {
		boolean ret = false;
		if (null==str) {
			ret = true;
		}else {
			if (str.trim().equals("")) {
				ret = true;
			}
		}	
		return ret;
	}
	


}
