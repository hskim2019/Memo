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
	<url-pattern>*.do</url-pattern>
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
