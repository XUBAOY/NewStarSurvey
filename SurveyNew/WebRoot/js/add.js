//�ر�����������
function close_window(){
    showCheckCodeClear();//������֤����ʾ����ʾ
    
    initialzation();
}

//������������
function loadScripAdd_window(){
/*****************��ʾ�������ݲ�������ͼ��(����������Ըǽ�е�����)***********************/
     document.getElementById("notClickDiv").style.display="block";
     var w = document.body.clientWidth;
     var h = document.body.clientHeight;
     document.getElementById("notClickDiv").style.width=w;
     document.getElementById("notClickDiv").style.height=h;
     rsCount=document.getElementById("hRsCount").value;
     document.getElementById("notClickDiv").style.zIndex=rsCount+101;
     
     /********��ʾ���������ͼ��**************/
     var width=screen.width;
     var height=screen.height;
     document.getElementById("scrip_add").style.display="block";
     document.getElementById("scrip_add").style.top=(height-310-240)/2;
     document.getElementById("scrip_add").style.left=(width-500)/2-120;
     document.getElementById("scrip_add").style.zIndex=rsCount+102;
     
     /*****************��ʾԤ��������ͼ��*******************/
     document.getElementById("preview").style.display = "block";
     document.getElementById("preview").setAttribute("class", "Style0");
     document.getElementById("preview").style.top=(height-310-240)/2;
     document.getElementById("preview").style.left=(width-240)/2+290;
     document.getElementById("preview").style.zIndex=rsCount+103;
}

function InputInfo(OriInput, GoalArea){
     document.getElementByIf(GoalArea).innerHTML = OriInput.value;
}

/********************���������ֽ���**********************/

var LastCount=0;

function CountStrByte(info, Total, Used, Remain){//�ֽ�ͳ��
   var ByteCount=0;
   var StrValue=info.value;
   var StrLength=info.value.length;
   var MaxValue=Total.value;
   if(LastCount != StrLength){//�ڴ��жϣ�����ѭ������
      for(i = 0; i < StrLength; i++){//����ף�����ָ�����Ӣ������ռ1���ַ�������ռ2���ַ�
          ByteCount = (StrValue.charCodeAt(i) <= 256)?ByteCount + 1: ByteCount + 2;
          
          if(ByteCount>MaxValue){
           
            info.value=StrValue.substring(0,i);
              
              document.getElementById("pContext").innerHTML = info.value;//��������Ԥ�������ϵ����� 
              
              alert("����������಻�ܳ���" + MaxValue + "���ֽڣ�\nע�⣺һ������Ϊ���ֽڡ�");
              
              ByteCount=MaxValue;
              
              break;
          }
      }
      
      Used.value = ByteCount;//�����ֽ�
      Remain.value=MaxValue-ByteCount;//ʣ���ֽ�
      LastCount=StrLength;
      document.getElementById("pContext").innerHTML=StrValue;//����������ʱ��ʾ��Ԥ��������
   }
}

/******************************************/

function ColorChoose(n){
    //�޸���������
    var ClassName = "Style" + n;
    
    document.getElementById("preview").setAttribute("class", ClassName);
    document.getElementById("preview").setAttribute("className", ClassName);
    scripForm.color.value=n;
}

function faceChoose(n){
     //�޸�����ͼ��
     var Url = "images/face/face_"+n+".gif";
     
     document.getElementById("pFace").src=Url;
     
     scripForm.face.value=n;
}

//��ʼ����������ı�
function initialization(){
        scripForm.wisMan.value="";
        scripForm.wellWisher.value="����";
        scripForm.color.value=0;
        scripForm.face.value=0;
        scripForm.content.value="";
        scripForm.used.value=0;
        scripForm.remain.value=200;
        
        /***********��ʼ����֤��******************************/
        var loader1 = new net.AjaxRequest("getPictureCheckCode.jsp?nocache="+ new Date().getTime(), deal_getCheckCode, onerror, "GET");//ˢ����֤��
        
        scripForm.checkCode.value="";
        document.getElementById("messageImg").src="images/tishi2.gif";
        document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
        document.getElementById("resultMessage").appendChild(document.createTextNode("��ܰ��ʾ��������֤������򣬻�ȡ��֤�룡"));
        
        /**************************************************/
        document.getElementById("btn_Submit").disabled=true;//����"����"��ť������
        document.getElementById("preview").style.display="none"; //��������Ԥ������
        document.getElementById("scrip_add").style.display="none";//��������������
        document.getElementById("notClickDiv").style.display="none";//������ʾ������Ըǽ���ݵ�ͼ��
        /***********************��ʼ������Ԥ������*********************/
        document.getElementById("preview").setAttribute("class", "Style0");
        document.getElementById("preview").setAttribute("className", "Style0");
        document.getElementById("pFace").src="images/face/face_0.gif";
        document.getElementById("pWishMan").innerHTML="";
        document.getElementById("pWellWisher").innerHTML="����";
        document.getElementById("pContext").innerHTML="";
        
}

function getTime(){
   //��õ�ǰʱ��
   var ThisTime = new Date();
   return ThisTime.getFullYear() + "-" + (ThisTime.getMonth() + 1) + "-" + ThisTime.getDate();
   
}

/*****************��������**************************/
function scripSubmit(){
      
      if(scripForm.wishMan.value==""){//��֤ף�������Ƿ�Ϊ��
         alert("������ף������");
         scripForm.wishMan.focus();
         return false;
         
      }
      
      if(scripForm.wellWisher.value==""){//��֤ף�����Ƿ�Ϊ�գ����Ϊ�ս��丳ֵΪ"����"
         scripForm.wellWisher.value="����";         
      }
      
      if(scripForm.content.value==""){//��֤���������Ƿ�Ϊ�� 
         alert("�������������ݣ�");
         scripForm.content.focus();
         return false;
      }
      
      var param="wishMan" + scripForm.wishMan.value + "&wellWisher=" + scripForm.wellWhisher.value + "&color=" + scripForm.face.value + "&content=" + scripForm.content.value;//����Ը�������������ӳ��ַ�������Ϊ��������Ĳ���
      
      var loader=new net.AjaxRequest("scrip.do?action=scripAdd", deal_s, onerror, "POST", param);
}

function onerror(){
   alert("���Ĳ�������");
}

function deal_s(){

   /*******************��ȡ�������***************************/
   
    var h = this.req.responseText;
    
    h=h.replace(/\s/g,"");//ȥ���ַ����е�Unicode�հ׷�
    
    var id = h.substr(h.indexOf("[")+1,h.indexOf("]")-h.indexOf("[")-1);
    
    /***********************************************/
    
    if(h!="�ܱ�Ǹ��������������ʧ�ܣ�"){
    
      alert(h);//�����ʾ��Ϣ
      
      createNewScrip(id);//������ӵ�������ʾ����Ըǽ��
      
      Show(id, "shadeDiv");//������ӵ�����ͻ����ʾ
    }
    
    initialization();//��ʼ�������������
}

/******************������֤�벢�����֤���Ƿ���ȷ*****************************/
function getCheckCodeFun(showCheckCode, checkCode){
        
        showCheckCode.style.display='';
        
        checkCode.focus();
}

function getCheckCode1(showCheckCode, checkCode){

        var loader1 = new net.AjaxRequest("getPictureCheckCode.jsp?nocache=" + new Date().getTime(), deal_getCheckCode, onerror, "GET");
        
        showCheckCode.style.display='';
        
        checkCode.focus();
}


function deal_getCheckCode(){
       document.getElementById("showCheckCode").innerHTML=this.req.responseText;
}

function showCheckCodeClearr(){
       showCheckCode.style.display='none';
}

/*******************��֤��֤���Ƿ���ȷ**********************/
function checkCheckCode(inCheckCode){

   if(inCheckCode != "")
   {
       var loader = new net.AjaxRequest("checkCheckCode.jsp?inCheckCode=" + inCheckCode + "&nocache=" + new Date().getTime(), deal_checkCheckCode, onerror, "GET");
   }
}

function deal_checkCheckCode(){

  var resultValue = this.req.responseText;
  
  if(resultValue == 1){//����ֵΪ1ʱ����ʾ��֤����ȷ
  
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode(" "));
     document.getElementById("messageImg").src="images/dui2.gif";
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNotes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode("��֤����ȷ��"));
     ducument.getElementById("btn_Submit").disabled=false;
  }
  else
  {
     document.getElementById("messageImg").src="images/cuo2.gif";
     document.getElementById("resultMessage").removeChild(document.getElementById("resultMessage").childNodes[0]);
     document.getElementById("resultMessage").appendChild(document.createTextNode("���������֤�벻��ȷ��"));
     
  }
}

/*************************************************/