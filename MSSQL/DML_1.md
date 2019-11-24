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
- Where 조건절을 붙이지 않으면 데이터 전체 삭제
```sql
DELETE [테이블이름] WHERE [조건]
```
```sql
DELETE tblCompany_HSK WHERE NAME = '임꺽정'
```
```sql
DROP TABLE IF EXISTS dbo.tblProject_HSK
```

---
# SELECT 구문
---

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
- OR보다 간결해 보이기 때문에 IN구문을 사용하는 것이 더 좋음
```sql
SELECT * FROM [테이블이름] WHERE [컬럼이름] IN ([조건], [조건])
```
```sql
SELECT NAME FROM tblCompany_HSK
    WHERE SABUN IN ('10001', '10002')
```

## EXISTS
- 주로 예외처리를 하고 싶을 때 활용
```sql
EXISTS (조건문)
```
```sql
SELECT NAME, SABUN from tblCompany_HSK A
WHERE NOT EXISTS(SELECT B.SABUN FROM tblProject_HSK B WHERE A.SABUN = B.SABUN)
-- 출력 결과는 B table 에 SABUN 이 포함되지 않은 NAME과 SABUN
```

## EXISTS ~ BEGIN END
```sql
IF EXISTS (SELECT * FROM tblProJect_HSK WHERE SEQ = 3)
BEGIN SELECT 1 END
ELSE
BEGIN SELECT 2 END
```
```sql
IF EXISTS(SELECT * FROM tblCompany_HSK)
BEGIN SELECT * FROM tblProject_HSK END 
ELSE 
BEGIN PRINT 'B' END
```

## LIKE
```sql
-- NAME이 '강'으로 시작하지 않는 문자 찾기
SELECT * FROM tblCompany_HSK WHERE NAME NOT LIKE '강%'
-- NAME이 '강'으로 시작하는 '두글자' 문자 찾기
SELECT * FROM tblCompany_HSK WHERE NAME LIKE '강_'
-- NAME의 첫번째 문자가 '강'또는 '임'또는 '유' 가 아닌 모든 문자 찾기
SELECT * FROM tblCompany_HSK WHERE NAME LIKE '[^강임유]%'
```

## CONTAINS
```sql
SELECT SABUN FROM tblCompany_HSK WHERE CONTAINS (NAME, '강')
```

## BEGIN TRAN / ROLLBACK TRAN / COMMIT TRAN

- Begin tran과 rollback tran 구문으로 감싸서 실행하면 다른 사람이 실행하는 것을 들어오지 못하게 
```sql
BEGIN TRAN
[실행구문]
COMMIT TRAN -- COMMIT 하지 않으면 실행구문 완료되지 않음

BEGIN TRAN
[실행구문] -- COMMIT 전에 ROLLBACK 하면 실행구문 최소 됨
ROLLBACK TRAN
```
```sql
BEGIN TRAN
UPDATE tblProJect_HSK SET PROJECTNAME = '프로젝트1' WHERE SEQ = 2
select * from tblProJect_HSK
ROLLBACK TRAN
```

## INSERT - SELECT
- tblCompany_HSK 와 tblCompany_HSK_B 테이블이 동일한 구조일 경우
```sql
INSERT INTO [데이터를Insert할테이블이름]
SELECT * FROM [복사할데이터가있는테이블이름]
```
```sql
-- 컬럼을 지정 해 줘야 할 경우
INSERT INTO [데이터를Insert할테이블이름] ([컬럼명], [컬럼명])
SELECT [컬럼명], [컬럼명] FROM [복사할데이터가있는테이블이름] WHERE [조건]
```
```sql
-- B 테이블에 A테이블의 데이터가 insert 됨
INSERT INTO tblCompany_HSK_B (SABUN, NAME, TEAM, POSITION)
SELECT SABUN,NAME,TEAM,POSITION FROM tblCompany_HSK WHERE NAME = '유순관'
```

## SELECT - INSERT
- 새로운 table을 만들면서 기존 테이블의 데이터를 그대로 복사 + insert
```sql
SELECT * INTO [새로생성할테이블이름] FROM [복사할데이터가있는테이블이름]
```
```sql
SELECT * INTO testTable FROM tblCompany_HSK_B
```

## UNION / UNION ALL
- UNION : 중복된 결과는 제외
- UNION ALL : 중복된 결과를 제외하지 않고 조회 하기 때문에 속도는 더 빠름
```sql
-- 출력결과는 두 테이블을 합쳐서 보여줌
SELECT * FROM tblCompany_HSK
UNION ALL
SELECT * FROM tblCompany_HSK_B 
```





