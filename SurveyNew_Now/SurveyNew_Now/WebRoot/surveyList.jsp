<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>�����ʾ��б�ҳ</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="JS/css/start/jquery-ui.css" rel="stylesheet" />
	<script type="text/javascript" src="JS/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="JS/js/jquery-ui-1.10.2.custom.js"></script>
    <script type="text/javascript">
      $(function(){
      
             $("#indexDiv").mouseover(function () {
                if ($(this).hasClass("nav1")) {
                    $(this).removeClass("nav1").addClass("nav1_1"); //��굽��ť��ʱ�ĳ���ʽnav1_1
                }
            });
            $("#indexDiv").mouseout(function () {
                if ($(this).hasClass("nav1_1")) {
                    $(this).removeClass("nav1_1").addClass("nav1");//������뿪��ťʱ��ʽ�ĳ�nav1
                }
            });

            $("#surveyDiv").mouseover(function () {
                if ($(this).hasClass("nav2")) {
                    $(this).removeClass("nav2").addClass("nav2_1"); //��굽��ť��ʱ�ĳ���ʽnav1_1
                }
            });

            $("#surveyDiv").mouseout(function () {
                if ($(this).hasClass("nav2_1")) {
                    $(this).removeClass("nav2_1").addClass("nav2"); //������뿪��ťʱ��ʽ�ĳ�nav1
                }
            });

            $("#helpDiv").mouseover(function () {
                if ($(this).hasClass("nav3")) {
                    $(this).removeClass("nav3").addClass("nav3_1"); //��굽��ť��ʱ�ĳ���ʽnav1_1
                }
            });

            $("#helpDiv").mouseout(function () {
                if ($(this).hasClass("nav3_1")) {
                    $(this).removeClass("nav3_1").addClass("nav3"); //������뿪��ťʱ��ʽ�ĳ�nav1
                }
            });
            
            //��ť�����任
            $("#searchBtn").mouseover(function () {
                if ($(this).hasClass("searchBtn1")) {
                    $(this).removeClass("searchBtn1").addClass("searchBtn1_1"); //��굽��ť��ʱ�ĳ���ʽnav1_1
                }
            });

            $("#searchBtn").mouseout(function () {
                if ($(this).hasClass("searchBtn1_1")) {
                    $(this).removeClass("searchBtn1_1").addClass("searchBtn1"); //������뿪��ťʱ��ʽ�ĳ�nav1
                }
            });
      
            //������ʾ�����ʾ��б�
             $("#surveyList").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
            
             var request = $.ajax({ url: "anyUser/AnyUserVisitAction",
                    type: "POST",
                    data:{comType:2,pageNum:1,pageListNum:1},
                    dataType: "html"
                });

                request.done(function (msg) {
                    $("#surveyList").html(msg);
                });

                request.fail(function (jqXHR, textStatus) {
                    alert("Request failed: " + textStatus);
                });
                
                //����ǰ���������ʾ���Ϣ
              $("#surveyTopFive").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
              
              var request1 = $.ajax({ url: "anyUser/AnyUserVisitAction",
                    type: "POST",
                    data:{comType:3},
                    dataType: "html"
                });
                
                 request1.done(function (msg) {
                    //�����ʾ���ʾ
                    $("#surveyTopFive").html("<div style='color:#006633;font-size:16px;margin-top:5px;height:40px;line-height:40px;'><b>���µ����ʾ�</b></div>");
                    
                    $("#surveyTopFive").append(msg);
                });  
                
                 request1.fail(function (jqXHR, textStatus) {
                    alert("Request failed: " + textStatus);
                });     
                
                //���������ʾ��ܰ�ť����¼�
                $("#searchBtn").click(function(){
                  
                      //������ʾ�����ʾ��б�
            		 $("#surveyList").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
            		 
            		 if($("#searchTitle").val() == "")
            		 {
            		  $("#surveyList").html("<div><a href='surveyList.jsp'>��������������Ϊ��!</a></div>");
            		  
            		  return ;
            		 }
            		 
            		 //alert($("#searchTitle").val());//��������ı����е�����
                     //���ݱ������������ʾ�
            		 var request2 = $.ajax({ url: "anyUser/AnyUserVisitAction",
                   				 type: "POST",
                 			   	 data:{comType:4,searchCondition:$("#searchTitle").val(),pageNum:1},
                  			  	 dataType: "html"
              		  });

 		              request2.done(function (msg) {
 				                 $("#surveyList").html(msg);
 		              });

 		              request2.fail(function (jqXHR, textStatus) {
 				                  alert("Request failed: " + textStatus);
	                  });
                  
                });                       
                
                            
      });
     //�����ʾ��ҳ������
     function showBtn(currentPageNum,currentPageListNum)
     {
             //������ʾ�����ʾ��б�
             $("#surveyList").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
            
             var request = $.ajax({ url: "anyUser/AnyUserVisitAction",
                    type: "POST",
                    data:{comType:2,pageNum:currentPageNum,pageListNum:currentPageListNum },
                    dataType: "html"
                });

                request.done(function (msg) {
                    $("#surveyList").html(msg);
                });

                request.fail(function (jqXHR, textStatus) {
                    alert("Request failed: " + textStatus);
                });
     }
     
       //��õ����ʾ��ҳ������
     function getSearchList(condition,pageNum1)
     {
                              //������ʾ�����ʾ��б�
            $("#surveyList").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
            		 
           /* if($("#searchTitle").val() == "")
            {
            	$("#surveyList").html("<div><a href='surveyList.jsp'>��������������Ϊ��!</a></div>");
            		  
            	return ;
            }*/
            		 
            //alert($("#searchTitle").val());//��������ı����е�����
            //���ݱ������������ʾ�
            var request2 = $.ajax({ url: "anyUser/AnyUserVisitAction",
                   				 type: "POST",
                 			   	 data:{comType:4,searchCondition:condition,pageNum:pageNum1},
                  			  	 dataType: "html"
              		  });

 		    request2.done(function (msg) {
 				                 $("#surveyList").html(msg);
 		              });

 		    request2.fail(function (jqXHR, textStatus) {
 				                  alert("Request failed: " + textStatus);
	                  });
	                  
     }
                
  </script>
  
      <style type="text/css">
    body
    {
      margin:0px;
      padding:0px;
      background-color:#f5f5f5;
    }
     .nav1
     {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;
      width:159px;
      background:url(image/index1.png);
      cursor:pointer;

      }
      .nav1 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      .nav1_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index1_1.png);
      cursor:pointer;

      }
      
      .nav1_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      .nav2
      {
      float:right;
      margin-bottom:0px;
      font-size:18px;
      height:70px;
      line-height:70px;
      width:159px;
      background:url(image/index2.png);
      cursor:pointer;  
      }
      
      .nav2 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      .nav2_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index2_1.png);
      cursor:pointer; 
      }
      
      .nav2_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      
      .nav3
      {
      float:right;
      margin-bottom:0px;
      font-size:18px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index3.png);
      cursor:pointer;            
      }
      
      .nav3 a
      {
        font-size:18px;
        color:#000000;
        text-decoration:none;
      }
      
      .nav3_1
      {
      float:right;
      margin-bottom:0px;
      height:70px;
      line-height:70px;      
      width:159px;
      background:url(image/index3_1.png);
      cursor:pointer;  
      }
      
      .nav3_1 a
      {
        color:#000000;
        text-decoration:none;
      }
      .surN1
      {
       float:left;
       margin-left:6px;
       margin-top:5px;
       padding-top:5px;
       line-height:30px;
       padding-left:17px;
       width:300px;
       height:200px;
       text-align:left;
       background:url(image/surveyN1.png);
      }
  .foot
  {
      height:75px;
      width:920px;
      background:url(image/footbg.gif);
      margin-top:5px;
  }
    .foot a
    {
     font-size:12px;
     color:#000;
     text-decoration:none;
    }
    #sectionContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #sectionContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
    #newContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #newContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
    #surveyContent
    {
      display:inline-block;
    }    
    #surveyContent a
    {
     font-size:13px;
     color:#000;
     text-decoration:none;
    }
    #surveyContent a:hover,#sectionContent a:active
    {
     font-size:13px;
     color:red;
     text-decoration:none;
    }
    
   .foot a:hover,.foot a:active
    {
     font-size:12px;
     color:#003333;
     text-decoration:none;
    }
    
    .login
    {
     padding-top:2px;
     float:left;
     width:46px;
     height:23px;
     background:url(image/reg_btn.gif) no-repeat;
    }
    .login a,.login a:active
    {
     color:white;
     font-size:16px;
     text-decoration:none;
    }
    .login a:hover
    {
     color:white;
     font-size:16px;
     text-decoration:none;
     cursor:pointer;
    }
    
    .help
    {
      float:left;
    }
   .help a,.help a:active
    {
    color:black;
    font-size:16px;
    text-decoration:none;
    }
    .help a:hover
    {
    color:#cc3300;
    font-size:16px;
    text-decoration:none;
    cursor:pointer;
    }
    #surveyTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
    }
    #newTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
    }
    #sectionTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
    }
    #surveyLine1
    {
      margin-left:0px;
      margin-right:0px;
      margin-top:5px;
      margin-bottom:5px;
    }
     #surveyLine2
    {
      margin-left:0px;
      margin-right:0px;
      margin-top:5px;
      margin-bottom:5px;
    }
    #searchTitle{
	 border:2px solid #0099CC;
	 width:520px;
	 height:34px;
    }
    
    .searchBtn1
    {
     height:34px;
     line-height:34px;
     color:#FFFFFF;
     font-size:16px;
     font-weight:bold;
     width:130px;
     border:0px;
     background:url(image/btn1_1.png);
     cursor:pointer;
    }
    
    .searchBtn1_1
    {
      height:34px;
      line-height:34px;
      color:#FFFFFF;
      border:0px;
      font-size:16px;
      font-weight:bold;
      width:130px;
      background:url(image/btn1.png);  
      cursor:pointer; 
    }
    
    #surveyContentReal
    {
      color:#003333;
      font-size:13px;
      width:920px;
      display:inline-block;
    }
    
    #surveyList
    {
     float:left;
     width:600px;
     border:1px dotted #0099CC;
     display:inline-block;
    }
    
    #surveyList a
    {
      font-weight:bold;
      color:#006633;
      font-size:16px;
    }
    
    #surveyList a:hover,#surveyList a:active
    {
      font-weight:bold;
      color:red;
      font-size:16px;
    }
    
    #surveyNew
    {
     float:left;
     width:305px;
     border:0px dotted #0099CC;
     height:500px;
     margin-left:10px;
     display:inline-block;
    }
    
    #surveyTopFive
    {
     width:300px;
     height:170px;
     margin-top:0px;
     margin-left:8px;
     border:1px solid #009966;
    }
    
    #cooperationDiv
    {
      width:300px;
      border:1px solid #009966;
      margin-top:10px;
      margin-left:8px;
      margin-bottom:5px;
    }
    </style>

  </head>
  
  <body>
     <div align="center" style="margin:0px;padding:0px;width:100%;height:100%;">
       <div style="height:130px;width:920px;padding-top:0px;background:url(image/bg.gif);">
          <div style="float:left;padding-top:10px;padding-left:20px;">
             <img src="image/logo1.png"/>
          </div>
          <div style="float:right;">
            <div style="margin:0px;padding:0px;height:60px;">
              <div style="float:right;margin-top:10px;margin-right:20px;">
                  <div style="padding:0px;margin:0px;float:left;"><img src="image/login.gif" /></div>
                  <div class="login"><a href="login.jsp">��¼</a></div>
                  <div style="float:left;margin-left:20px;"><img src="image/kefu.gif" /></div>
                  <div class="help"><a href="help.html"><b>��������</b></a></div>
              </div>
            </div>
            <div>
              <div class="nav3" id="helpDiv"><b><a href="help.html">��������</a></b></div>
              <div class="nav2" id="surveyDiv"><b><a href='surveyList.jsp'>�����ʾ��б�</a></b></div>
              <div class="nav1" id="indexDiv"><b><a href='index.jsp'>��ҳ</a></b></div>
            </div>
         </div>
       </div> 
       
       <div id="surveyContent">
         <div id="surveyLine1"><img src="image/surveyline.png"/></div>
         <div>
           <span style="margin-right:10px;"><input type="text" name="searchTitle" id="searchTitle" /></span>
           <span style="margin-left:10px;"><input id="searchBtn" class="searchBtn1" type="button" value="�����ʾ�"/></span>
          </div>
         <div id="surveyLine2"><img src="image/surveyline.png"/></div>
         <div id="surveyContentReal">
            <div id="surveyList">
            </div>
            <div id="surveyNew">
               <div id="surveyTopFive"></div>
               <div id="cooperationDiv">
                 <div style="color:#006633;font-size:16px;margin-top:5px;height:40px;line-height:40px;"><b>�����ʾ�����û�</b></div>
                 <div style="margin-top:5px;"><img src="image/cooperation1.png" /></div>
                 <div style="margin-top:5px;"><img src="image/cooperation2.png" /></div>
                 <div style="margin-top:5px;"><img src="image/cooperation1.png" /></div>
                 <div style="margin-top:5px;margin-bottom:5px;"><img src="image/cooperation2.png" /></div>
               </div>
            </div>
         </div>
       </div>
       
       <div class="foot">
           
           <div style="height:52px;margin:0px;" align="center">
           
             <div style="width:25%;float:left;margin-top:5px;">
                 <div style="width:100px;">
                     <div style="float:left;"><img src="image/contact_1.gif"/></div>
                     <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                        <a href="#">����ϵͳ</a>
                     </div>
                  </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;">
               <div style="width:100px;">
                   <div style="float:left;"><img src="image/contact_2.gif"/></div>
                   <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                     <a href="help.html">�ͻ�����</a>
                  </div>
                </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;" align="center">
                 <div style="width:100px;">
                     <div style="float:left;"><img src="image/contact_3.gif"/></div>
                     <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                       <a href="help.html">����֧��</a>
                     </div>
                 </div>
              </div>
              
              <div style="width:25%;float:left;margin-top:5px;" align="center">
                <div style="width:100px;">
                    <div style="float:left;"><img src="image/contact_4.gif"/></div>
                    <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                      <a href="help.html">��������</a>
                    </div>
                </div>
              </div>
              
           </div>
           
           <div style="height:14px;margin:0px;">
             <a href="help.html">��Ȩ����&copy;ȻȻ������</a>
           </div>
       </div>
     </div>
  </body>
</html>
