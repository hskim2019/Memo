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

WebContent > WEB-INF > web.xml
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
```
*.do로 들어오는 모든것들을  ActionServlet을 로드하고 param-value에 작성한 xml을 로딩
xxx_example.xml 은 로딩을 통해서 Action 태그에 있는 내용 로드


https://wiper2019.tistory.com/103