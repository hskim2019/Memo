# File Upload

## HttpPostedFileWrapper
#### HttpPostedFileBase를 상속 받은 클래스
#### 생성자
    - HttpPostedFileWrapper(HttpPostedFile)
#### 속성
    - ContentLength: 파일 크기(byte)
    - ContentType: 파일의 MIME 콘텐츠 형식 가져옴
    - FileName: 
    - InputStream: 파일 콘텐츠를 읽을 수 있는 Stream 개체 가져옴
#### Methods
    - Equals(Object) : 지정된 개체와 현재 개체가 같은지 여부 확인
    - SaveAs(String) : 파일 저장


## 첨부파일 업로드 처리 방식
- 파일 데이터와 <form>태그 데이터 함께 전송하는 방식 VS 파일 데이터와 <form> 태그 데이터를 구분해서 전송하는 방식

### 파일 데이터와 <form> 태그 데이터를 구분해서 전송하는 방식
- 게시글 제목, 내용, 작성자 정보 입력
- 파일 첨부하면 Ajax요청으로 서버에 파일 업로드
- 최종 게시글 등록을 클릭하면 게시글 제목, 내용, 작성자 정보, 첨부파일정보 DB테이블에 저장

### 
- https://walbatrossw.github.io/spring-mvc/2018/07/21/15-spring_mvc-board-fileupload.html#542-%EC%B2%A8%EB%B6%80%ED%8C%8C%EC%9D%BC-%EC%B6%9C%EB%A0%A5-%EB%A7%A4%ED%95%91-%EB%A9%94%EC%84%9C%EB%93%9C

- https://cofs.tistory.com/181
- https://doublesprogramming.tistory.com/130
- http://taeyo.net/Columns/View.aspx?SEQ=576&PSEQ=40
- http://taeyo.net/Columns/View.aspx?SEQ=491&PSEQ=36&IDX=1
- https://ispie.tistory.com/43
- https://m.blog.naver.com/PostView.nhn?blogId=cambo95&logNo=100110487413&proxyReferer=https%3A%2F%2Fwww.google.com%2F
- https://202psj.tistory.com/176
- https://src-bin.com/ko/q/4f4072
- https://www.it-swarm.net/ko/c%23/%ED%8C%8C%EC%9D%BC-%EC%97%85%EB%A1%9C%EB%93%9C-aspnet-mvc-30/971879674/
- http://2nusa.blogspot.com/2016/07/cnet.html