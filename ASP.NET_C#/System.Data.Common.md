
## 메서드
- ExcuteNonQuery : 명령어 실행, 해당 쿼리문 적용 된 레코드 수 반환
- ExecuteReader : 명렁어를 Connection을 통해 보내고 SqlDataReader 바인딩 (?)
- ExecuteScalar : 명령어 실행, Sum(), Avg()와 같이 값이 하나만 반환 될 경우 사용

---
## ADO.NET
- 모든 데이터에 대해 쉽게 접근하고 조작할 수 있도록 .NET Framework에서 제공하는 클래스들의 집합
- 기본 포트번호 1433


## .NET Framework 데이터 공급자
- 데이터베이스에 연결하고 SQL 명령 실행, 결과 검색
- DBMS가 MySql, Oracle이면 전용 데이터 제공자를 다운받아 사용해야 함 (.NET Framework Data Providoer for SQL Server)

#### Data Provider 데이터 공급자
- SqlConnection : 데이터베이스 서버와 연결, 이 객체를 사용 후에는 반드시 Connection 닫아줘야 함
- SqlCommand : 데이터베이스 서버에 실행 될 SQL문 전달
- SqlParameter : 매개변수화된 쿼리를 사용할 수 있도록 함
- SqlDataReader : 실행 된 SQL문으로부터 반환받은 데이터 조회를 담당
- SqlDataAdapter : 비연결성 데이터베이스로 연결 및 Dataset에 데이터 저장

## Select query 문 사용 시 객체 생성 방법
```C#
string strConn = ConfigurationManager.ConnectionStrings["커넥션스트링이름"].ConnectionString;
string sql = "쿼리문";

using(SqlConnection conn = new SqlConnection()) {
  conn.ConnecdtionString = strConn;
  conn.Open();

  SqlCommand cmd = new SqlCommand(sql, conn);

  object ObjValue = cmd.ExecuteScalar();        // select문이 단일 값 반환할 때 
  
  SqlDataReader reader = cmd.ExecuteReader();   // select문이 단일 값이 아닌 그 밖의 모든 쿼리 실행
                                                // 결과값을 반환하는 것이 아니라 SqlDataReader 타입의 인스턴스 반환
												// SqlDataReader는 모든 select문 결과를 담고 있는 것이 아니라 Read메서드 호출 할 때 마다 DB에서 현재 행에 대한 값을 읽어온다(SqlConnection 객체가 연결 된 상태에서만)
}
```

## SqlDataReader 메서드
- Read() : 레코드가 존재하면 다음 레코드로 이동
- Close() : SqlDataReader 객체 닫기
- Get.....() : 각 행에 속한 컬럼별 데이터 가져오기


### SqlDataReader를 이용하여 테이블 내용 조회
```C#
SqlDataReader reader = cmd.ExecuteReader();

while(reader.Read())
{
	string name = reader.GetString(0);
	DateTime birth = reader.GetDateTime(1);
	byte family = reader.GetByte(3);
}
reader.Close();
```

### DataAdapter.Fill method
- SQL Data source와 DataSet 연결에 사용
- Fill method를 통해 Data source를 얻어오고, DataSet을 채움
- 연결 된 SelectCommand 속성으로 지정 된 Select문을 사용하여 데이터 원본에서 행을 검색

