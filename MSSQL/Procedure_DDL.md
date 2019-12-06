# PROCEDURE

## Procedure 생성
```sql
CREATE PROCEDURE [프로시저명]
AS
[쿼리문]
```

```sql
ALTER PROCEDURE USP_SelectCompanyList
	@P_SABUN VARCHAR(20) -- 매개변수와 컬럼의 데이터 타입을 일치시켜 줄 것
	, @P_NAME VARCHAR(8) 
	, @P_TEAMNAME VARCHAR(20)
	, @P_POSITION VARCHAR(8)
AS

SET NOCOUNT ON -- *

DECLARE @sql NVARCHAR(1000) -- 지역변수
DECLARE @where NVARCHAR(1000)
DECLARE @err INT

INSERT INTO tblCompany_HSK(SABUN, NAME, TEAMNAME, POSITION) VALUES(@P_SABUN, @P_NAME, @P_TEAMNAME, @P_POSITION)

SET @err = @@ERROR

IF @err <> 0
	RETURN
SET @sql = 'SELECT * FROM tblCompany_HSK'

IF(@P_SABUN IS NOT NULL)
BEGIN
	IF (@where IS NULL)
		SET @where = ' WHERE '
	ELSE
		SET @where = @where + ' AND '
	SET @where = @where + 'NAME=' + '''' +  @P_NAME + '''' 
END
IF (@where IS NOT NULL)
	SET @sql = @sql + @where

EXEC sp_executesql @sql
GO
```

- > SET NOCOUNT란?<br>
        >  &nbsp;프로시저 실행 후 (0개의 행이 영향을 받음)과 같은 메세지가 출력이 되는데<br> 
        >  &nbsp; SET NOCOUNT ON이라는 문구를 삽입해줌으로써 제거 가능<br>

- > @@ ERROR 시스템 함수?<br>
        >  &nbsp; 마지막 Transact-SQL문이 실행되면 0 반환, 오류 발생 시 오류번호 반환<br> 
        >  &nbsp; @@ERROR값은 Transact-SQL문이 완료될 때 마다 값이 변경<br>
        >  &nbsp; 오류에 대해서만 발생하고 경고에 대해서는 발생하지 않음


## Procedure 삭제
```sql
DROP PROCEDURE dbo.USP_SelectCompanyList
```

## Procedure 호출
```sql
EXEC dbo.USP_SelectCompanyList '99', '세글', '테스트팀', '외부'
```