package edu.bdu.tools;

public class OperatorTools {
	
	//���ָ����С�����з����ַ���
    public String getNewString(String oldString,int size)
    {
    	String newString = "";
    	
    	for(int i = 0; i < oldString.length(); i = i + size)
    	{
    	  int endI = i + size - 1;
    	  
    	  if(endI >= oldString.length())
    	  {
    		  endI = oldString.length();
    	  }
    	  
    	  newString += oldString.substring(i,endI);	
    	  
    	  newString += "<br>";//��ӻ���
    	}
    	
    	return newString;
    }
    
    //ȥ�������ַ�
    public String getStringReplace(String oldString)
    {
    	return oldString.replaceAll("[\\t\\n\\r]","");
    }

}
