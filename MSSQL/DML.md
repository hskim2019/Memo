## 데이터 삽입
```sql
INSERT INTO [테이블이름] ([컬럼], [컬럼])
VALUES([값],[값])
```
```sql
INSERT INTO tblCompany_HSK (SABUN, NAME, TEAMNAME, POSITION)
 VALUES('10001', '강산애', 'SS사업부', '사원')
 INSERT INTO tblCompany_HSK (SABUN, NAME, TEAMNAME, POSITION)
 VALUES('10002', '임꺽정', 'SS사업부', '대리')
```

## 데이터 수정
```sql
UPDATE [테이블이름]
    SET 
        [데이터수정할컬럼이름] = '[수정할내용]'
        WHERE [조건컬럼] = [조건]
```
```sql
-- 임꺽정의 직급을 '과장'으로
UPDATE tblCompany_HSK
        SET
		  POSITION='과장'
        WHERE NAME = '임꺽정'
```

## 데이터 삭제
```sql
DELETE [테이블이름] WHERE [조건]
```
```sql
DELETE tblCompany_HSK WHERE NAME = '임꺽정'
```

## 조인 JOIN
```sql
-- 강산애가 참여중인 프로젝트 모두 출력
-- 출력 칼럼 NAME/TEAMNAME/POSITION/PROJECTNO/PROJECTNAME
SELECT 
    A.NAME
    ,A.TEAMNAME
    ,A.POSITION
    ,B.PROJECTNAME
    ,B.PROJECTNAME
    FROM tblCompany_HSK AS A
    LEFT OUTER JOIN tblProject_HSK AS B
        ON A.SABUN = B.SABUN
        WHERE NAME = '강산애'
```

## GROUP BY
```sql
SELECT [출력할컬럼], COUNT([count할컬럼]) AS [count출력할임의의컬럼이름]
    FROM [테이블이름] AS A
    LEFT OUTER JOIN [테이블이름] AS B
        ON [조인할컬럼] = [조인할컬럼]
        GROUP BY [조건컬럼]
```
```sql
-- GROUP BY
-- 각각의 인원이 참여중인 프로젝트 개수 출력
-- 이름, 프로젝트 개수
SELECT NAME, COUNT(PROJECTNAME) AS ProjectCTN
    FROM tblCompany_HSK AS A
    LEFT OUTER JOIN tblProject_HSK AS B
        ON A.SABUN = B.SABUN
        GROUP BY NAME
```

## SELECT TOP
- 상위값 출력

```sql
-- 상위 또는 첫번째 값 출력
SELECT TOP 1 * FROM tblCompany_HSK ORDER BY SEQ DESC

-- 상위 또는 첫번째부터 둘째 값까지 출력
SELECT TOP 2 * FROM tblCompany_HSK ORDER BY SEQ DESC
```

## IN
## EXISTS
## LIKE


-- BEGIN TRAN
-- Begin tran과 rollback tran 구문으로 감싸서 실행하면 다른 사람이 실행하는 것을 들어오지 못하게 
BEGIN TRAN
UPDATE tblProJect_HSK SET PROJECTNAME = '프로젝트1' WHERE SEQ = 2
select * from tblProJect_HSK
ROLLBACK TRAN

BEGIN TRAN
COMMIT TRAN


-- IN 조건 (=  OR)
select * from tblProJect_HSK where SEQ in(1,2)

-- EXIST
IF EXISTS (SELECT * FROM tblProJect_HSK WHERE SEQ = 3)
BEGIN SELECT 1 END
ELSE
BEGIN SELECT 2 END

-- 함수
SELECT GETDATE()




