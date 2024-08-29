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
