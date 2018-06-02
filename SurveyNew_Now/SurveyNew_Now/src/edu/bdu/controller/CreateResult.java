package edu.bdu.controller;

import java.util.List;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.bdu.dao.CheckboxItemDaoImpl;
import edu.bdu.dao.ItemDaoImpl;
import edu.bdu.dao.RadioItemDaoImpl;
import edu.bdu.dao.SurveyDaoImpl;
import edu.bdu.dao.TextItemDaoImpl;
import edu.bdu.dao.VisitorDaoImpl;

import edu.bdu.entity.CheckboxItem;
import edu.bdu.entity.Item;
import edu.bdu.entity.RadioItem;
import edu.bdu.entity.Survey;
import edu.bdu.entity.TextItem;
import edu.bdu.entity.Visitor;

import org.jfree.chart.entity.StandardEntityCollection;
import org.jfree.chart.ChartRenderingInfo;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

//import com.lowagie.text.Font;
import java.awt.Font;
import org.jfree.chart.title.TextTitle;

/**
 * �������ڣ�2011��8��24��
 * @author xin lei hu
 *
 */

public class CreateResult extends HttpServlet {

	private SurveyDaoImpl surveyOp;//�����ʾ������
	
	private ItemDaoImpl itemOp;//�����������
	
	private RadioItemDaoImpl radioOp;//��ѡ�������
	
	private CheckboxItemDaoImpl checkOp;//��ѡ�������
	
	private TextItemDaoImpl textOp;//�����������
	
	private VisitorDaoImpl visitorOp;//�����߲�����
   
	private int width = 550;//ͼƬ�Ŀ��
	
	private int height = 400;//ͼƬ�ĸ߶�
	
	public CreateResult() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 doPost(request, response);//����doPost������ִ�д�����
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");//���ñ����ʽ
		
		response.setContentType("text/html");//���ؽ������
		response.setCharacterEncoding("UTF-8");//���ñ����ʽ
		
		PrintWriter out = response.getWriter();

		
		//////////////////////////////��������ʾ����/////////////////////////////////////
		
		if(request.getParameter("surveyid") != null)
		{
			int surveyid = Integer.parseInt(request.getParameter("surveyid"));//��õ����ʾ���
			
			Survey survey = surveyOp.getSurvey(surveyid);//���ݱ�Ż�õ����ʾ�ʵ��
			
			if(survey != null)
			{
				out.println("<div id = 'surveyTitle'>" + survey.getSurveyTitle() + "</div>");//�����ʾ����
				
				
				List items = itemOp.getItemList(survey.getSurveyID());//���ݵ����ʾ��õ�����
				
				if(items.size() == 0)
				{
					out.println("�˵����ʾ�û�д��������");//��ӡû�е�������Ϣ
				}
				
				for(int i = 0; i < items.size(); i++)
				{
					Item item = (Item)items.get(i);//��õ�����ʵ��
					
					out.println("<div style='margin-bottom:20px;color:#0099CC;width:759px;padding-bottom:10px;line-height:29px;font-size:16px;font-weight:bold;' align='left'>");
					
					/////////////////////////��ӡ������������////////////////////////////
					out.println((i + 1) + ". " + item.getItemCaption());
					
					int itemType = item.getItemType();//��õ����������
					
					///////////////////////����������/////////////////////////////////////////////////
					String webName = getServletContext().getRealPath("/plotImgs");//���·�� 
				    String prefix = "";
				    String picpath="";
				    String mappath="";
				    
					switch(item.getItemType())
					{
					case 0:
						
						JFreeChart chart = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//�����ͼӴ���Ψһ���
						
						Font font = new Font("����",Font.BOLD,16);//����������ʽ
						
						TextTitle title = new TextTitle(item.getItemCaption(),font);
						
						chart.setTitle(title);
						
						
					    //chart.getLegend().setItemFont(new Font("����",Font.PLAIN,12));
						
						
						picpath = webName + "/" + prefix + ".jpg";
						
						mappath = webName + "/" + prefix + ".map";
						
						FileOutputStream plot_fos = new FileOutputStream(picpath);
						
						
						
						PrintWriter map_pw = new PrintWriter(mappath);
						
					
						
						ChartRenderingInfo info = new ChartRenderingInfo(new StandardEntityCollection());
						
						ChartUtilities.writeChartAsJPEG(plot_fos, 0.8f, chart, width, height, info);
						
						ChartUtilities.writeImageMap(map_pw, "mymap" + item.getItemID(), info, false);
						
						plot_fos.close();//�ر�������
						
						map_pw.close();//�ر�������
						
						out.println("<jsp:include page='plotImgs/" + prefix + ".map' />");//����map�ȵ�
						
						out.println("<div>"+
		               "<img id='pic" + item.getItemID() + "' src='plotImgs/"+ prefix + ".jpg' title='ͳ�Ʒ���ͼ'" +
		               " alt='���ڼ���ͼƬ�����Ե�...' usemap='#mymap" + item.getItemID() + "' style='border:0'>"
	                   + "</div>");
			
						break;
					case 1:
						
		                JFreeChart chart1 = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//�����ͼӴ���Ψһ���
						
						//JFreeChart chart = CreateChart(CreateDataset(item.getItemID(), item.getItemType()));
						
						prefix = "radio"+item.getItemID();//�����ͼӴ���Ψһ���
						
						Font font1 = new Font("����",Font.BOLD,16);//����������ʽ
						
						TextTitle title1 = new TextTitle(item.getItemCaption(),font1);
						
						chart1.setTitle(title1);
						
						picpath = webName + "/" + prefix + ".jpg";
						
						mappath = webName + "/" + prefix + ".map";
						
						FileOutputStream plot_fos1 = new FileOutputStream(picpath);
						
						PrintWriter map_pw1 = new PrintWriter(mappath);
						
						
						
						ChartRenderingInfo info1 = new ChartRenderingInfo(new StandardEntityCollection());
						
						ChartUtilities.writeChartAsJPEG(plot_fos1, 0.8f, chart1, width, height, info1);
						
						ChartUtilities.writeImageMap(map_pw1, "mymap" + item.getItemID(), info1, true);
						
						plot_fos1.close();//�ر�������
						
						map_pw1.close();//�ر�������
						
                        out.println("<jsp:include page='plotImgs/" + prefix + ".map' />");//����map�ȵ�
						
						out.println("<div>"+
		               "<img id='pic" + item.getItemID() + "' src='plotImgs/"+ prefix + ".jpg' title='ͳ�Ʒ���ͼ'" +
		               " alt='���ڼ���ͼƬ�����Ե�...' usemap='#mymap" + item.getItemID() + "' style='border:0'>"
	                   + "</div>");
						
						break;
					case 2:
						out.println("<div id='textItem" + item.getItemID() + "'><a href='javascript:getTextList(" + item.getItemID() + ",1)'>�鿴�����߻ش��б�</a></div>");//��ӡ�������б�
						
						break;
					}
					
					///////////////////////����������/////////////////////////////////////////////////
					
					////////////////////////��ӡ���������ͳ��////////////////////////////////
					
					out.println("</div>");
				}
				
			}
			else
			{
				out.println("û���ҵ��˵����ʾ�");//û�д˵����ʾ�
			}
			
			
			
		}

		//////////////////////////////��������ʾ����/////////////////////////////////////

		out.flush();
		out.close();
	}
   /**
    * ���ݴ��������������ɷ���ͼ��
    * @param dataset
    * @return
    */
	private static JFreeChart CreateChart(CategoryDataset dataset)
	{
		
		
		JFreeChart chart = ChartFactory.createBarChart(
				"",
				"������",
				"�������Ʊ��", 
				dataset, 
				PlotOrientation.VERTICAL,
				true, 
				true,
				false
				);
		
		CategoryPlot plot = (CategoryPlot)chart.getPlot();
		
		NumberAxis rangeAxis = (NumberAxis)plot.getRangeAxis();
		
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
		
		BarRenderer renderer = (BarRenderer)plot.getRenderer();
		
		/*Font font = new Font("����",Font.BOLD,16);//����������ʽ
		
		TextTitle title = new TextTitle("����wew",font);
		
		chart.setTitle(title);
		//���jsp��jfreechart��������
		*/
	    //renderer.setDrawBarOutline(true);
	    
	    //renderer.setBarPainter(new StandardBarPainter());
		
		CategoryAxis domainAxis = plot.getDomainAxis();
		
		//����X�������ϵ����֣������������
		domainAxis.setTickLabelFont(new Font("scan-serif",Font.PLAIN,15));
		
		//����X��ı������֣������������
		domainAxis.setLabelFont(new Font("����",Font.PLAIN,15));
		
		NumberAxis numberAxis = (NumberAxis)plot.getRangeAxis();
		
		//����Y�������ϵ�����
		numberAxis.setTickLabelFont(new Font("sans-serif",Font.PLAIN,12));
		
		//����Y��ı�������
		numberAxis.setLabelFont(new Font("����",Font.PLAIN,12));
		
		//����ײ�������������
		chart.getLegend().setItemFont(new Font("����",Font.PLAIN,12));
		
		//chart.getLegend().setItemFont(font)
		
		
		domainAxis.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));
		
		return chart;
	}
	/**
	 * ���ͼ�������
	 * @param itemid
	 * @param itemType
	 * @return
	 */
	private CategoryDataset CreateDataset(int itemid, int itemType)
	{
	   DefaultCategoryDataset dataset = new DefaultCategoryDataset();//�����ʾ�
	  
	   switch(itemType)
	   {
	   case 0:
		   List radios = radioOp.getRadioList(itemid);//��õ�ѡ������Ĵ���
		   for(int i = 0; i < radios.size(); i++)
		   {
			   RadioItem radio = (RadioItem)radios.get(i);
			   
			   dataset.setValue(radio.getSelectCount(), radio.getRadioCaption(), "");
		   }
		   break;
	   case 1:
		   
		   List checkboxs = checkOp.getCheckboxList(itemid);//��ö�ѡ������Ĵ���
		   
		   for(int i = 0; i < checkboxs.size(); i++)
		   {
			   CheckboxItem checkbox = (CheckboxItem)checkboxs.get(i);
			   
			   dataset.setValue(checkbox.getSelectCount(), checkbox.getCheckboxCaption(), "");
		   }
		   
		   break;
	   }
	   
	   return dataset;//���ص����ʾ�ʵ��
	}
	
	public void init() throws ServletException {
	
		
		this.surveyOp = new SurveyDaoImpl();//ʵ���������ʾ�
		
		this.itemOp = new ItemDaoImpl();//ʵ���������������
		
		this.radioOp = new RadioItemDaoImpl();//ʵ������ѡ�������
		
		this.checkOp = new CheckboxItemDaoImpl();//ʵ������ѡ�������
		
		this.textOp = new TextItemDaoImpl();//ʵ���������������
		
		this.visitorOp = new VisitorDaoImpl();//ʵ���������߲�����
	}

}
