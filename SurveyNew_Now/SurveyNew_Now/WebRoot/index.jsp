<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
    <meta http-equiv="description" content="this is my page" />
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="JS/css/start/jquery-ui.css" rel="stylesheet" />
	<script type="text/javascript" src="JS/js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="JS/js/jquery-ui-1.10.2.custom.js"></script>
    <script type="text/javascript">
        $(function () {

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
            
            //��ú�̨����
            $("#surveyContent").html("<div><a href='index.jsp'>�����ʾ����ڼ���...</a></div>");
            
             var request = $.ajax({ url: "anyUser/AnyUserVisitAction",
                    type: "POST",
                    data:{comType:1},
                    dataType: "html"
                });



                request.done(function (msg) {
                    $("#surveyContent").html(msg);
                });

                request.fail(function (jqXHR, textStatus) {
                    alert("Request failed: " + textStatus);
                });
        });
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
       margin-left:4px;
       margin-top:5px;
       padding-top:5px;
       line-height:30px;
       padding-left:1px;
       width:300px;
       height:200px;
       text-align:left;
       background:url(image/surveyN1.png) no-repeat;
      }
  .foot
  {
      height:75px;
      width:920px;
      background:url(image/footbg.gif);
      margin-top:2px;
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
        padding-left:10px;
    }
    #newTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
        padding-left:10px;
    }
    #sectionTitle
    {
        color:Blue;
        font-size:18px;
        font-style:italic;
        padding-left:10px;
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
       
       
       <div>
         <div><img src="image/advert3.png"/></div>
       </div>
       
       
       <div style="height:210px;width:920px;">

         <div class="surN1">
              <div id="surveyTitle">�����ʾ�</div>
              <div id="surveyContent">
                <div><a href="flash/game1.swf"><span style="margin-left:30px;">��������������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game2.swf"><span style="margin-left:30px;">�й�����������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game3.swf"><span style="margin-left:30px;">С��������С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
              </div>
         </div>

         <div class="surN1">
              <div id="newTitle">ϵͳ��ѯ</div>
              <div id="newContent">
                <div><a href="flash/game1.swf"><span style="margin-left:30px;">��������������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game2.swf"><span style="margin-left:30px;">�й�����������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game3.swf"><span style="margin-left:30px;">С��������С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
              </div>
         </div>
         <div class="surN1">
             <div id="sectionTitle">��������</div>
             <div id="sectionContent">
                <div><a href="flash/game1.swf"><span style="margin-left:30px;">��������������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game2.swf"><span style="margin-left:30px;">�й�����������Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game3.swf"><span style="margin-left:30px;">С��������С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
                <div><a href="flash/game4.swf"><span style="margin-left:30px;">�����ǹ��С��Ϸ</span><span style="margin-left:30px;">2013-04-05</span></a></div>
             </div>
         </div>
       </div>
       
       
       <div class="foot">
           
           <div style="height:52px;margin:0px;" align="center">
           
             <div style="width:25%;float:left;margin-top:5px;">
                 <div style="width:100px;">
                     <div style="float:left;"><img src="image/contact_1.gif"/></div>
                     <div style="float:left;margin-left:8px;height:36px;line-height:36px;vertical-align:middle;">
                        <a href="help.html">����ϵͳ</a>
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
