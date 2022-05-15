## Struts
* web browser --요청--> servlet : structs.xml설정에 따라 액션 연결  
--> 로직 수행 후 request 객체에 결과 담아 jsp로 연결

### 프로젝트 구조
[Reference: https://skillazit.tistory.com/6](https://skillazit.tistory.com/6)
* ActionForm : 웹에서 넘어 온 데이터 저장
* Action : 데이터 처리 클래스
* Jsp web page : View 생성


http://gnujava.com/board/article_view.jsp?article_no=3410&board_no=35&table_cd=EPAR01&table_no=01


<img src="https://github.com/hskim2019/Memo/blob/master/images/struts.jpg"/>

### Example
#### 1) WebContent > WEB-INF > web.xml
```xml
<servlet>
   <servlet-name>StrutsAction</servlet>
   		<servlet-class>
			[클래스 지정]
		</servlet-class>
        <init-param> <!--struts 설정 -->
          <param-name>config</param-name> <!--이름 아무거나 -->
          <param-value>
           /WEB-INF/configs/struts/client/struts_config_client_example.xml <!--이런식으로 파일 지정-->
           /WEB-INF/configs/struts/client/struts_config_client_example2.xml 
          </param-value>
		</init-param>
</servlet>

<servlet-mapping> <!--url패턴 설정-->
	<servlet-name>StrutsAction</servlet-name> <!-- *.adics 로 들어오면 servlet-name이 action 실행 -->
	<url-pattern>*.do</url-pattern> <!-- param-value 에 작성한 xml 중 해당하는 것을 어떻게 찾아서 action을 실행하는지는 모르겠다. -->
</servlet-mapping>

<welcome-file-list>
	<welcome-file>index.html</welcome-file>
</welcome-file-list>
```
#### 2) wecome-file-list 에 지정된 html 또는 jsp 로드
#### 3) index.html 에서 index.do 로 redirect
- welcome-file-list 에서는 jsp나 html 파일은 찾을 수 있지만 *.do 는 사용할 수 없어서 이런식으로 돌아감
#### 4) web.xml 의  servlet-mapping을 통해 매칭되는 servlet(Front Controller) 호출


*.do로 들어오는 모든것들을 ActionServlet을 로드하고 param-value에 작성한 xml을 로딩
xxx_example.xml 은 로딩을 통해서 Action 태그에 있는 내용 로드
https://wiper2019.tistory.com/103

##### struts-config.xml 설정법
```xml
// FormBean
<form-beans>
	<form-bean type="com.hanil.client.main.MainForm" name="MainForm"/> <!-- action 에서 사용할 이름 -->
</form-beans>
<global-forwards>
	<forward name="index" path="/index.jsp" redirect="false"/>
</global-forwards>

<action-mappings>
    <action path="/main" 
 	    name="MainForm"
	    type="app.MainAction"
	    scope="request"
	    parameter="cmd">
        <forward name="main" path="client.main"/>          
    </action>
</action-mappings>
  <!-- 사용자가 http://hostname/main.do 를 호출하면 
  액션객체(app.MainAction") 호출하기 전에 "MainForm" ActionForm 객체 생성하여 폼 값을 채워서 "app.MainAction"액션 객체 호출하여 넘겨줌-->
 <!--name : ActionForm 지정, <form-bean>에서 설정 된 이름을 지정
     type : 작업 처리할 Action class 지정
     forward : action 의 return message 가 main이면 path 로 forward >
```


### Example2
(관리자) 전자결재 
http://localhost:8080/admin/appr/MasterCodeAction.adics?cmd=main 

#### 1) web.xml 
*.adics 로 들어왔으므로 web.xml 에서 servlet-name으로 action 실행
param-value에 작성된 여러 xml 중에서 해당하는 것을 어떻게 찾는지는 모르겠다. path로 찾는 것으로 추측
```xml
	<servlet>
		<servlet-name>StrutsAction</servlet-name>
		<servlet-class>
			org.apache.struts.action.ActionServlet
		</servlet-class>
		<init-param>
			<param-name>config</param-name>
			<param-value>
			/WEB-INF/configs/struts/admin/struts_config_admin_appr.xml
			</param-value>
		</init-param>
		<load-on-startup>3</load-on-startup>
	</servlet>

	<servlet-mapping>
		<servlet-name>StrutsAction</servlet-name>
		<url-pattern>*.adics</url-pattern>
	</servlet-mapping>
```
#### 2) WEB-INF/configs/struts/admin/struts_config_admin_appr.xml
http://localhost:8080/admin/appr/MasterCodeAction.adics?cmd=main 
path - /admin/appr/MasterCodeAction 인 것으로 찾는 듯
name - 지정 된 MasterCodeForm 객체를 생성하여(model)
type - /com/hanil/admin/appr/mastercode/MasterCodeAction 호출하어 처리
forward - cmd 값이 main 이므로 path에 지정 된 appr.mastercode.main 에 mapping  
```xml
<struts-config>
	<form-beans>
		<form-bean type="com.hanil.admin.common.mastercode.MasterCodeForm" name="MasterCodeForm"/>
	</form-beams>

	<action-mappings>        
	<action path="/admin/appr/MasterCode*Action"
				name="MasterCode{1}Form"
				type="com.hanil.admin.appr.mastercode.MasterCodeAction"
	    		scope="request"
	    		parameter="cmd">
	    		
	    	<forward name="main" path="appr.mastercode.main"/>
            <forward name="delete" path="/jsp/framework/ajax/AjaxXML.jsp"/>
            <forward name="insert" path="/jsp/framework/ajax/AjaxXML.jsp"/>
            <forward name="update" path="/jsp/framework/ajax/AjaxXML.jsp"/>
        </action>
	</action-mappings>
	<controller processorClass="com.hanil.framework.servlet.RequestProcessor"/>
</struts-config>
```

#### 3) WEB-INF/configs/tiles/admin/tiles_admin_appr_layout.xml
name = appr.mastercode.main 과 mapping 되는 듯
extends = tiles.xml에 지정 된 admin_layout 을 over write 하는 것 처럼 채우는 것 같음, tiles.xml 이 mater layout인 셈
```xml
<tiles-definitions>
	<definition name="appr.mastercode.main" extends="admin_layout">
		<put name="title" value="Groupware Mail Administrator" />
		<put name="topMenu" value="/jsp/admin/include/topMenu.jsp?menuID=002"/>
		<put name="left_menu" value="/jsp/admin/menu/appr/admin_appr_menu.xml"/>
		<put name="menu" value="appr" />
		<put name="leftHeaderImage" value="/admin/common/admin-title_ea.png"/>
		<put name="body" value="/admin/appr/MasterCodeList.adics?cmd=listView" />
	</definition>
</tiles-definitions>
```

#### 4) WEB-INF/configs/tiles/tiles_layout.xml
admin_common_layout.jsp 에 html 있음
```xml
<tiles-definitions>
	<definition name="admin_layout" path="/layouts/admin/admin_common_layout.jsp">
	  	<put name="title" value=""/>
	  	<put name="topMenu" value=""/>
	  	<put name="leftHeaderImage" value=""/>
		<put name="body" value="" />
	</definition>
</tiles-definitions>	
```

#### 5) WebContents/layouts/admin/admin_common.layout.jsp
name value 로 값이 채워짐
```jsp
	<iframe width="100%" height="100%" id="main" name="main" 
	frameborder="0" src="<tiles:getAsString name="body"/>"  
	scrolling="no" marginwidth="0" marginheight="0" frameborder="0">
	</iframe> 
```


## 업무지원 공지사항/일반게시판
* ex) board_list.jsp 
- 객체 생성
```jsp
  <jsp:userBean id="자바빈이름" class="자바빈클래스이름" scope="영역">
  // useBean 은 객체를 생성하는 action tag
  // id: jsp 페이지에서 자바빈 객체에 접근 할 때 사용하는 이름
  // class : 패키지 이름을 포함한 자바빈 클래스의 완전한 이름을 입력
  // scope : 자바빈 객체가 저장 될 영역 지정
  //         영역은 page, request, session, application 중 하나이고 기본값은 page
```
- Handler class 호출
- query direct 처리 하는 듯
