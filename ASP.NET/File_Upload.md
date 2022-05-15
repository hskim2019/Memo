# File Upload
* 첨부파일 저장 방식 변경 할 때 메모한 내용
    +  binary data로 만들어서 DB에 저장
    +  DB 에는 파일 정보만 저장하고 실제 파일은 서버에 저장
    +  DB 에 파일 정보 저장 & CloudBlobClient 사용하여 Azure blob storage 에 파일 저장 
* [BoardApp/Controllers/BoardController.cs](https://github.com/hskim2019/board-application/blob/whasun.kim/BoardApp/Controllers/BoardController.cs)

### HttpPostedFileBase 클래스 활용하여 veiw에서 파일 읽어오기
#### 속성
    - ContentLength: 파일 크기(byte)
    - ContentType: 파일의 MIME 콘텐츠 형식 가져옴
    - FileName: 
    - InputStream: 파일 콘텐츠를 읽을 수 있는 Stream 개체 가져옴
#### Methods
    - Equals(Object) : 지정된 개체와 현재 개체가 같은지 여부 확인
    - SaveAs(String) : 파일 저장

#### 파일 데이터와 <form> 태그 데이터를 구분해서 전송하는 방식
- 게시글 제목, 내용, 작성자 정보 입력
- 파일 첨부하면 Ajax요청으로 서버에 파일 업로드
- 최종 게시글 등록을 클릭하면 게시글 제목, 내용, 작성자 정보, 첨부파일정보 DB테이블에 저장

## CloudBlobClient vs BlobContainerClient
* BoardApp 에서는 CloudBlobClient 사용하여 blobstorage 테스트
    + Sample1 public access
    + Sample2 Upload Blob with Service SAS
    + Sample3 with Credentials & upload with folder - acccount SAS
* BlobContainerClient 가 v12 Azure.Storage.Blobs new version

- [HttpPostedFile 사용 참조] (http://2nusa.blogspot.com/2016/07/cnet.html)
- [HttpPostedFile 사용 참조] (https://m.blog.naver.com/PostView.nhn?blogId=cambo95&logNo=100110487413&proxyReferer=https%3A%2F%2Fwww.google.com%2F)
- [ajax 요청 참조] (https://cofs.tistory.com/181)

- http://taeyo.net/Columns/View.aspx?SEQ=491&PSEQ=36&IDX=1
- http://taeyo.net/Columns/View.aspx?SEQ=576&PSEQ=40

- https://walbatrossw.github.io/spring-mvc/2018/07/21/15-spring_mvc-board-fileupload.html#542-%EC%B2%A8%EB%B6%80%ED%8C%8C%EC%9D%BC-%EC%B6%9C%EB%A0%A5-%EB%A7%A4%ED%95%91-%EB%A9%94%EC%84%9C%EB%93%9C
- https://doublesprogramming.tistory.com/130