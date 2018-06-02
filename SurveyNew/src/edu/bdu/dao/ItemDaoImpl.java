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
import edu.bdu.entity.Item;
import edu.bdu.entity.Survey;
public class ItemDaoImpl extends BaseJdbcDao{
	
	/**
	 * ��������ѯ���е������б�
	 * @return
	 */
    public List getItemList()
	{
		  List retList = new ArrayList();
			
		  String sql = "select * from ItemTable";//��ѯ���еĵ������б�
		  
			super.openConn();
			try {
				super.stmt = conn.createStatement();//��ȡ���ݿ������
				
				super.rs = super.stmt.executeQuery(sql);
				
				while(super.rs.next()) {
					
					Item item = new Item();//ʵ����������
					
					item.setItemID(rs.getInt("ItemID"));//��õ�������
					
					item.setItemCaption(rs.getString("ItemCaption"));//��õ���������
					
					item.setItemType(rs.getInt("ItemType"));//��õ����������
					
					item.setItemAttribute(rs.getInt("ItemAttribute"));//��õ��������ͣ����ֶε�ǰû��ʹ��
					
					item.setItemOwerID(rs.getInt("ItemOwnerID"));//��õ�������߱��
					
					item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//��õ��������������ʾ��� 
					
					item.setRadioCount(rs.getInt("RadioCheckboxCount"));//��õ�����Ļش����
					
					retList.add(item);//��ӵ����� 
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			super.closeAll();//�ر����ݿ����ӺͲ�����
			
			return retList;//���ص������б�
	}
	/**
	 * ���ݵ����ʾ��Ų�ѯ�������б�
	 * @param itemOwnerSurveyID
	 * @return
	 */
  public List getItemList(int itemOwnerSurveyID)
  {
	  List retList = new ArrayList();
		
	  String sql = "select * from ItemTable where ItemOwnerSurveyID = " 
			+ itemOwnerSurveyID;//���ݵ��������������ʾ�������sql
		
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Item item = new Item();//ʵ����������
				
				item.setItemID(rs.getInt("ItemID"));//��õ�������
				
				item.setItemCaption(rs.getString("ItemCaption"));//��õ���������
				
				item.setItemType(rs.getInt("ItemType"));//��õ����������
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//��õ��������ͣ����ֶε�ǰû��ʹ��
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//��õ�������߱��
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//��õ��������������ʾ��� 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//��õ�����Ļش����
				
				retList.add(item);//��ӵ����� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص������б�
  }
  
  /***
   * ���ݵ������������ѯ������
   * @param itemID
   * @return
   */
  
  public Item getItemByItemId(int itemID)
  {
	  Item item = null;//Ĭ�ϲ�ѯ���Ľ���ǿ�ֵ
		
	  String sql = "select * from ItemTable where ItemID = " 
			+ itemID;//���ݵ�����ı������sql
		
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			if(super.rs.next()) {
				
				item = new Item();//ʵ����������
				
				item.setItemID(rs.getInt("ItemID"));//��õ�������
				
				item.setItemCaption(rs.getString("ItemCaption"));//��õ���������
				
				item.setItemType(rs.getInt("ItemType"));//��õ����������
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//��õ��������ͣ����ֶε�ǰû��ʹ��
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//��õ�������߱��
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//��õ��������������ʾ��� 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//��õ�����Ļش����
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return item;//���ز�ѯ���Ľ�� 
  }
  
  /**
   * ���ݲ�ѯ����
   * @param condition
   * @return
   */
  public List getItemList(Item condition)
  {
	  List retList = new ArrayList();
		
	  String sql = "select * from ItemTable where ItemOwnerSurveyID where 1 = 1";//���ݵ��������������ʾ�������sql
		
	  
	  //���õ������ѯ����
	  if(condition != null)
	  {
	     if(condition.getItemID() != 0)
	     {
	    	 sql += " and ItemID = " + condition.getItemID();//���õ�������
	     }
	     
	     if(!isNullOrEmpty(condition.getItemCaption()))
	     {
	    	 sql += " and ItemCaption like '%" + condition.getItemCaption() + "%'";//���õ��������
	     }
	     
	     if(condition.getItemOwnerSurveyID() != 0)
	     {
	    	 sql += " and ItemOwnerSurveyID = " + condition.getItemOwnerSurveyID();//���õ��������������ʾ���
	     }
	     
	     if(condition.getItemOwnerID() != 0)
	     {
	    	 sql += " and ItemOwnerID = " + condition.getItemOwnerID();//�������
	     }
	  }
	  
		super.openConn();
		try {
			super.stmt = conn.createStatement();//��ȡ���ݿ������
			
			super.rs = super.stmt.executeQuery(sql);
			
			while(super.rs.next()) {
				
				Item item = new Item();//ʵ����������
				
				item.setItemID(rs.getInt("ItemID"));//��õ�������
				
				item.setItemCaption(rs.getString("ItemCaption"));//��õ���������
				
				item.setItemType(rs.getInt("ItemType"));//��õ����������
				
				item.setItemAttribute(rs.getInt("ItemAttribute"));//��õ��������ͣ����ֶε�ǰû��ʹ��
				
				item.setItemOwerID(rs.getInt("ItemOwnerID"));//��õ�������߱��
				
				item.setItemOwnerSurveyID(rs.getInt("ItemOwnerSurveyID"));//��õ��������������ʾ��� 
				
				item.setRadioCount(rs.getInt("RadioCheckboxCount"));//��õ�����Ļش����
				
				retList.add(item);//��ӵ����� 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		super.closeAll();//�ر����ݿ����ӺͲ�����
		
		return retList;//���ص������б�
  }

  /**
   * ���ݵ��������޸ĵ�����
   * @param item
   * @return
   */
  public boolean updateItem(Item item)
  {
      boolean flag = false;//�޸Ľ�����
	  
	  String updateSql = "update ItemTable set ItemCaption = ?," +
	  		"RadioCheckboxCount = ? where ItemID = ?";//���µ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(updateSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1,item.getItemCaption());//���õ�������������
		  
		  super.pstmt.setInt(2, item.getRadioCount());//���õ�����ش����
		  
		  super.pstmt.setInt(3,item.getItemID());//���õ�������
          	     
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
   * ���ݵ�������ɾ��������
   * @param itemID
   * @return
   */
  public boolean deleteItem(int itemID)
  {
		 boolean flag = false;//�޸Ľ�����
		  
		  String deleteSql = "delete from ItemTable where ItemID = ?";//ɾ�������ʾ��sql���
		  
		  super.openConn();//�����ݿ�����
			
			try
			{
			  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
			  
			
			  super.pstmt.setInt(1,itemID);//Ϊsql�������ʾ����߱��
			  
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
   * ���ݵ��������������ʾ���ɾ��������
   * @param itemSurveyOwnerID
   * @return
   */
  public boolean deleteItemBySurveyId(int itemSurveyOwnerID)
  {
	  boolean flag = false;//�޸Ľ�����
	  
	  String deleteSql = "delete from ItemTable where ItemOwnerSurveyID = ?";//ɾ�������ʾ��sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(deleteSql);//����sqlԤ�������ִ�в�ѯ
		  
		
		  super.pstmt.setInt(1,itemSurveyOwnerID);//Ϊsql�������ʾ����߱��
		  
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
   * ��ӵ�����
   * @param item
   * @return
   */  
  public boolean InsertItem(Item item)
  {
      boolean flag = false;//��ӽ�����
	  
	  String insertSql = "insert into ItemTable(ItemCaption," +
	  		"ItemType,ItemAttribute,ItemOwnerID," +
	  		"ItemOwnerSurveyID,RadioCheckboxCount)" +
	  		" values(?,?,0,?,?,0)";//��ӵ�sql���
	  
	  super.openConn();//�����ݿ�����
		
		try
		{
		  super.pstmt = conn.prepareStatement(insertSql);//����sqlԤ�������ִ�в�ѯ
		  
		  super.pstmt.setString(1, item.getItemCaption());//����������Ŀ����
		  
		  super.pstmt.setInt(2,item.getItemType());//���õ����������
		  
		  super.pstmt.setInt(3,item.getItemOwnerID());//���õ�����Ĵ����߱��
		  
		  super.pstmt.setInt(4, item.getItemOwnerSurveyID());//���õ�����ĵ����ʾ���
		  
		 // super.pstmt.setInt(5,item.getRadioCount());//���õ�����Ļش������¼
		 
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
