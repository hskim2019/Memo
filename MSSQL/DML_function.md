## 집계함수
AVG
SUM

COUNT/COUNT BIG
```sql
SELECT PROJECTNAME, COUNT(PROJECTNAME) FROM tblProject_HSK GROUP BY PROJECTNAME 
```

MIN

### > MAX
```sql
SELECT MAX([컬럼명]) FROM [테이블명] WHERE [조건절]
```

GROUPING_ID
CUBE/ROLL UP

## 날짜함수
GETDATE

### > DATEPART
- 날짜데이터를 인수로 받아 원하는 파트만 리턴하는 함수
```sql
DATEPART([원하는날짜파트], '[변환하고자하는날짜데이터]')
```
```sql
-- 연도만 출력 됨
SELECT DATEPART(YYYY, PROJECTDATE) AS YEAR FROM tblProject_HSK
```

YEAR
MONTH
DAY
DATEDIFF
DATEADD
EOMONTH

## 문자함수
LTRIM/RTRIM
CHARINDEX
PATINDEX

### > SUBSTRING
```sql
-- 출력결과 ex) 010-****-1111
SELECT 
SUBSTRING(TEL,1 ,4) + '****' + SUBSTRING(TEL ,9 ,13) AS TEL 
FROM tblCompany_HSK
```


CONCAT
STUFF

### > REPLACE
```sql
SELECT REPLACE([컬럼이름], '변경할문자열패턴', '새로운문자열') FROM [테이블이름]
```
```sql
SELECT SABUN, REPLACE(PROJECTNAME, '프로젝트1', '중단') AS PROJECTNAME FROM tblProject_HSK
```
```sql
UPDATE tblProject_HSK
    SET PROJECTNAME = REPLACE(PROJECTNAME, '프로젝트1', '중단')
```

REPLICATE
LEFT/RIGHT
LEN
UPPER/LOWER

## 순위함수
RANK
DENSE_RANK
ROW_NUMBER

## 변환함수

### > CONVERT
```sql
CONVERT([CONVERT후출력할데이터형식], [변환하고자하는데이터/컬럼], [스타일])
```
```sql
-- DATE 데이터를 YYYY-MM-DD 형태로 바꾸기
SELECT CONVERT (char(10), PROJECTDATE, 23) AS 날짜 
FROM tblProject_HSK
```

### TRY_CONVERT


## RowNo
- key(id) 대신 RowNo 사용하기
```sql
SELECT BoardNo, BoardTitle, ROW_NUMBER() OVER(ORDER BY BoardNo) AS ROWNUM From Boards
```

# 인덱스
# 커서
# 트리거

## 암호화 PWDENCRYPT / PWDCOMPARE
```sql
INSERT INTO Boards(password) VALUES(PWDENCRYPT('1234'));

SELECT PWDCOMPARE('1234', password) FROM Boards WHERE boardNo = 1;
```
