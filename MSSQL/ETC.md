```sql
Substring(문자열, 시작자리번호, 자를문자수)

select len('라파엘') = 3

select charindex ('다', '가나다라마')   결과3

charindex ('찾을문자열', '지정문자열', 숫자)

지정문자열에서 숫자에서부터 문자열 검색하기

SELECT REPLICATE('채울문자', 전체길이 - LEN('데이터')) + '데이터'
```




```sql
ALTER TABLE [dbo].[usercard_data] ADD  CONSTRAINT [DF_usercard_data_IsDel]  DEFAULT ('False') FOR [IsDel]

ALTER TABLE dbo.usercard_data ADD UserCardID uniqueidentifier NOT NULL DEFAULT (newsequentialid())
```

### DB 에러 발생시키기
```sql
RAISERROR ('ERROR!!!', 16, 1)
```

```sql
EXEC SymmetricKey_Open
update dbo.GWT_Customer set ConnectionString =
EncryptByKey(Key_GUID('GW_SYMMETRIC_KEY_001'), @ConnectionString, 1, HashBytes('SHA1', CONVERT(varbinary , @COMPANYCODE))) where CompanyCode = '001'
exec SymmetricKey_Close

EXEC SymmetricKey_Open
DECLARE @COMPANYCODE NVARCHAR(200)
SELECT @COMPANYCODE = '009'
DECLARE @ConnectionString NVARCHAR(MAX) 
SELECT @ConnectionString = 'Server=tcp:sqldb-gowit-mpn-dbservice.database.windows.net,1433;Initial Catalog=sqldb-gowit-dev-goworks;Persist Security Info=False;User ID=dbsaadmin;Password=Gowit365!@;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;'
INSERT INTO dbo.GWT_Customer 
(CompanyCode, CompanyName, Tenant, AdminName, Email, SPSiteInfo, ConnectionString)
VALUES (
@COMPANYCODE, '고객사', 'gowit.co.kr', '고객사관리자', 'whasun.kim@gowit.co.kr', 'gowitco.sharepoint.com', 
EncryptByKey(Key_GUID('GW_SYMMETRIC_KEY_001'), @ConnectionString, 1, HashBytes('SHA1', CONVERT(varbinary , @COMPANYCODE)))
)

exec SymmetricKey_Close

EXEC SymmetricKey_Open
SELECT CompanyCode, CompanyName, Tenant, AdminName, Email, SPSiteInfo, 
CONVERT(NVARCHAR(500), DecryptByKey(ConnectionString, 1 , HashBytes('SHA1', CONVERT(varbinary, CompanyCode)))) AS ConnectionString
FROM  GWT_Customer
EXEC SymmetricKey_Close
```


### SET QUOTED_IDENTIFIER ON/OFF
* 따옴표 처리를 표준에 따를 것인지 여부
    - SELECT "1" 은 비표준 (OFF)
    - SELECT '1'은 표준
### SET ANSI_NULLS ON/OFF
* NULL 값에 대한 비교 처리를 표준에 따를 것인지 여부
    - 컬럼 = NULL 은 비표준 
    - 컬럼 IS NULL 표준


### 데이터베이스 호환성 수준
* [참조_link](https://blog.outsider.ne.kr/1160)
```sql
  -- 데이터베이스 호환성 수준(compatibility_level)이 100인 DB 에서 실행 시 
  -- "개체 이름 'string_split'이(가) 잘못되었습니다." 오류
  SELECT  DisplayName
         ,VALUE
    FROM  USER
   CROSS APPLY STRING_SPLIT(DisplayName, '/')

 -- 데이터베이스 호환성 수준(compatibility_level)이 130인 DB 에서 실행 시 오류 없음
  SELECT  DisplayName
         ,VALUE
    FROM  User.dbo.USER
   CROSS APPLY STRING_SPLIT(DisplayName, '/')

 -- 호환성 수준 확인
   SELECT name, compatibility_level, * FROM sys.databases WHERE NAME = 'DB명'
   
 -- STRING_SPLIT 에서는 호환성 수준이 130 이상이어야 한다. 수준이 130보다 작으면 데이터베이스 엔진 함수를 찾을 수 없다
```

### cursor 사용 지양 이유
* 재컴파일 이슈
  - cursor 내부에서 실행되는 select 문이 cursor를 열 때마다 컴파일되고 최적화 됨. CPU 리소스를 소비하고 성능에 영향 미칠 수 있다
* CPU 부하 이슈
  - cursor 사용 시 서버가 행 단위로 처리하며
  - 임시테이블을 사용하므로 결과 집합을 메모리에 유지, 대용량 처리 시 메모리 사용량이 급증하며 메모리 부족으로 인한 성능 문제 발생할 가능성이 있다
* 결론
  - while 문 사용


### 임시테이블
```sql
  CREATE TABLE #test (
    A INT IDENTITY(1, 1),
    B INT
  )

  INSERT INTO #test VALUES(1)

  SELECT * FROM #test
```
* 특징 
  - 임시테이블이 삭제되지 않은 경우 SQL SERVER 는 사용자 연결이 끊겼을 때 자동으로 임시테이블 삭제
  - 현재 설정된 데이터베이스에 저장되지 않고 시스템 데이터베이스 Tempdb에 저장된다 
  - 테이블을 만든 연결에서만 볼 수 있다
  - 테이블을 만든 연결이 끊어지기 전에 이 테이블이 명시적으로 삭제되지 않으며
  - 대용량 쿼리 비용에 유리
  - INSERT 되는 데이터가 100건 이상인 경우 사용하는 것이 좋음

* 장점
  - 인덱스를 작성할 수 있다
  - FK를 제외한 나머지 제약을 지정할 수 있다
  - ALTER TABLE 이 가능
  - 통계정보 및 Non Cluster Index의 사용으로 대용량 및 조인 쿼리 성능 높이기 가능

* 단점
  - 저장프로시저 안에서 사용시 저장프로시저 실행시마다 임시 테이블 사용 구문에 대해 재컴파일 발생(CPU 부하)
  - Transaction Overhead, Lock Overhead, Rollback Overhead 존재


### 테이블 변수
```sql
  CREATE @test TABLE (
    A INT IDENTYT(1, 1) PRIMARY KEY NONCLUSTERED,
    B INT
  )

  INSERT INTO @test SELECT 1

  SELECT * FROM @test
```
* 특징
  - 변수가 정의된 함수, 저장 프로시저 및 일괄 처리가 끝나면 자동으로 정리된다
  - 테이블 변수와 관련된 트랜잭션은 테이블 변수가 업데이트 되는 동안만 지속되므로 리소스 잠금과 로깅에 대한 요구가 줄어든다
  - 소용량 쿼리 비용 유리
  

* 장점
  - PK (기본키), UNIQUE(유일키), CHECK 제약을 사용할 수 있다
  - IDETITY(식별자) 속성 지정
  - 임시테이블에서 발생할 수 있는 Overhead문제 없음
  - 저장프로시저 안에서 사용시 저장프로시저 재컴파일 없음

* 단점
  - 100 건 이상 대량 데이터에서 Sort 작업 등으로 인한 성능 저하 발생
  - PK, UNIQUE를 통한 Cluster index만 생성 가능
  - 테이블의 각 컬럼에 대한 통계정보 생성 X

* 제약사항
  - FK (외래키) 를 사용할 수 없다
  - ALTER TABLE 할 수 없음
  - 추가 인덱스 선언을 할 수 없으나 PK, UNIQUE가 있다


### LOCK
* [참조_link](https://woowaa.net/31)
* 잠금 종류
  - 공유 잠금 (SHARED LOCK): SELECT 문에 의해 설정
  - 배타적 잠금 (EXCLUSIVE LOCK): INSERT, UPDATE, DELETE 문에 의해 설정