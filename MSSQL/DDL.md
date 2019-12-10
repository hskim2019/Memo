### 데이터베이스 생성
```sql
IF DB_ID (N'BoardAppDb') IS NOT NULL  
DROP DATABASE BoardAppDb;  
GO  
CREATE DATABASE BoardAppDb  
COLLATE Korean_Wansung_CI_AS ;  
GO
```


### 테이블 조회
```sql
SELECT * FROM INFORMATION_SCHEMA.TABLES

SELECT NAME FROM SYS.TABLES
```

## 특정 컬럼이 포함 된 테이블 찾기
```sql
SELECT
T.name AS table_name, C.name AS column_name
FROM sys.tables AS T
INNER JOIN sys.columns AS C
ON T.object_id = C.object_id
WHERE C.name = '[컬럼명]'
```

### 테이블 속성 확인
```sql
SP_HELP [테이블이름]
```

```sql
SP_HELP tblCompany_HSK
```

### 테이블 생성
```sql
CREATE TABLE [테이블이름] (
	[컬럼이름] [데이터타입] [NULL여부]
)
```
```sql
CREATE TABLE tblCompany_HSK (
	SEQ INT IDENTITY(1,1) NOT NULL -- 자동증가 (시작할 숫자값Seed, 증가할 숫자값)
	,SABUN VARCHAR(20) NOT NULL PRIMARY KEY
	, NAME VARCHAR(8) NOT NULL
	, TEAMNAME VARCHAR(20) NULL
	, POSITION VARCHAR(8) NULL
)
```

### 테이블 수정

- 테이블 이름 변경
```sql
SP_RENAME '[수정전테이블이름]', '[수정후테이블이름]'
```
```sql
SP_RENAME 'tblCompany_HSK', 'tblCompany'
```

### 테이블 삭제
```sql
DROP TABLE [테이블 이름]
```
```sql
DROP TABLE TESTTABLE
```


### 컬럼 수정

- 컬럼 추가
```sql
ALTER TABLE [테이블이름] ADD [컬럼명][데이터타입][NULL여부])
```
```sql
ALTER TABLE tblCompany_HSK ADD TEST VARCHAR(20) NOT NULL
```

- 컬럼 이름 수정
```sql
SP_RENAME '[테이블이름].[변경하고자하는컬럼이름]', '[수정하고싶은컬럼이름]'
```
```sql
SP_RENAME 'tblCompany_HSK.TEST', 'RETEST'
```

- 컬럼 데이터 "타입" 수정
```sql
ALTER TABLE [테이블이름] ALTER COLUMN [컬럼이름] [데이터타입] [NULL여부]
```
```sql
ALTER TABLE tblCompany_HSK ALTER COLUMN TEST VARCHAR(20) NULL
```

- 컬럼삭제
```sql
ALTER TABLE [테이블이름] DROP COLUMN [삭제할컬럼이름]
```
```sql
ALTER TABLE tblCompany_HSK DROP COLUMN RETEST
```

---
### 테이블 제약 조건 확인
```sql
SP_HELPCONSTRAINT [테이블이름]
```
```sql
SP_HELPCONSTRAINT tblCompany_HSK
```

### Primary Key 추가

 > 모든 제약조건은 이름을 가지게 되는데 별도로 지정하지 않으면 임의의 이름이 붙게 됨
```sql
ALTER TABLE [테이블이름] ADD CONSTRAINT [제약조건이름] PRIMARY KEY CLUSTERED (PK지정할컬럼이름)
```
```sql
ALTER TABLE tblCompany_HSK ADD CONSTRAINT PK_tblCompany PRIMARY KEY CLUSTERED (SABUN)
```

###  Foreign Key 추가
```sql
ALTER TABLE [테이블이름]] 
	ADD CONSTRAINT [제약조건이름] FOREIGN KEY(외래키지정할컬럼) 
	REFERENCES [참조할테이블=PK가있는테이블](참조할컬럼) 
```
```sql
ALTER TABLE tblProject_HSK 
	ADD CONSTRAINT FK_tblProject FOREIGN KEY(SABUN) 
	REFERENCES tblCompany_HSK(SABUN) 
```


### Primary / Foreign Key 삭제
```sql
ALTER TABLE [테이블이름] DROP CONSTRAINT [제약조건이름]
```
```sql
ALTER TABLE tblCompany_HSK DROP CONSTRAINT PK_tblCompany
```


### Default 값 추가
```sql
ALTER TABLE [테이블이름] ADD CONSTRAINT [제약조건이름] DEFAULT [값] FOR [제약조건넣을컬럼이름]
```
```sql
ALTER TABLE Comments_TB ADD CONSTRAINT DF_CommentOrder DEFAULT 0 FOR OriginCommentNo
```

