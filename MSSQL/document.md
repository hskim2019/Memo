# 데이터 타입  
<br>

## [숫자형 데이터 타입]
---

### DECIMAL / NUMERIC
- 고정 소수점
- numeric과 decimal 타입은 소수점 이하 값을 반올림한다.
- int / float 보다는 numeric/decimal 사용이 권장된다.
  - float과 real은 근사 데이터 형식으로 정확한 값이 아닌 근사값을 저장하기 때문에  나누기 등의 계산 시 정확하지 않은 값이 발생할 수 있다.

<br>

## [문자형 데이터 타입]
---

### CHAR
- 고정 길이를 갖는 문자열 저장

### VARCHAR
- 가변 길이를 갖는 문자열을 저장

```
 CHAR/VARCHAR 와 NCHAR/NVARCHAR 비교  
    >       NVARCHAR는 동일한 데이터 저장 시 VARCHAR 보다 공간을 2배 차지
    >       영문은 1byte, 한글(유니코드) 2byte이므로
    >       한글 2글자를 입력 받으면 4byte 데이터가 넘어갈 수 있다
    >       VARCHAR 는 byte수로 사이즈 지정하고, NVARCHAR는 글자 수로 사이즈 지정
    >       한글을 저장하지 않을 것이 확실하면 VARCHAR를 사용하여 공간을 더 적게 차지하게 할 것
```

### TEXT
### MEDIUMTEXT
### LONGTEXT

<br>

## [날짜형 데이터 타입]
---
### DATE
- YYYY-MM-DD  

### SMALLDATETIME
- YYYY-MM-DD hh:mm:ss
### DATETIME
- YYYY-MM-DD hh:mm:ss[.nnn] 

```sql
SELECT SYSDATETIME()  
    ,SYSDATETIMEOFFSET()  
    ,SYSUTCDATETIME()  
    ,CURRENT_TIMESTAMP  
    ,GETDATE()  
    ,GETUTCDATE();  
/* Returned:  
SYSDATETIME()      2007-04-30 13:10:02.0474381  
SYSDATETIMEOFFSET()2007-04-30 13:10:02.0474381 -07:00  
SYSUTCDATETIME()   2007-04-30 20:10:02.0474381  
CURRENT_TIMESTAMP  2007-04-30 13:10:02.047  
GETDATE()          2007-04-30 13:10:02.047  
GETUTCDATE()       2007-04-30 20:10:02.047  
*/
```

---

# 인덱스
  * 원하는 데이터를 더 빨리 찾을 수 있도록 데이터 위치 정보를 모아 놓은 데이터베이스 내의 객체
  * 검색은 빠르지만 인덱스를 만들기 위한 디스크 공간이 필요하고
  * 인덱스가 있는 테이블은 DML 작업 시 시간이 더 걸릴 수 있음
  * [클러스터인덱스 vs 넌클러스터인덱스] (https://mozi.tistory.com/320)
  * 클러스터인덱스 : 
    + 범위검색에 용이 ex 영어사전
    + 데이터 페이지 자체가 인덱스 키 값에 의해 물리적으로 정렬
    + 테이블 당 1개만 설정 (데이터가 정렬 되어야 하므로 두 개 이상이면 정렬이 꼬일 수 있어서)
  * 비클러스터인덱스 : 
    + 특정값 검색에 용이 ex 일반책
    + 인덱스 페이지가 구성되고 인덱스 걸어 준 컬럼의 키 값과 데이터페이지 번호 슬롯 번호가 저장 됨
    + 루트레벨에서 리프 레벨(leaf level, 최하위 레벨) 인덱스 페이지까지 탐색해야 테이블 데이터 위치를 알 수 있음
    + 선택도 3% 이내면 사용
      - 선택도? 찾을 데이터 / 전체 데이터 비율
      - 성별을 찾는 경우 50:50 확률이라 선택도 높음
    + 클러스터인덱스보다 용량 큼
    + 테이블 당 249개
  * 인덱스를 설정하면 좋은 경우
    + where절에 참조되는 컬럼
    + 참조키가 설정 되어 있는 컬럼
    + 참조키는 아니어도 join 에 사용되는 컬럼
    + 범위 검색이 일어나는 컬럼
    + order by로 정렬되는 컬럼

  * Included column 
    + 인덱스 키 칼럼에 조회에 필요한 모든 칼럼을 다 포함시키게 되면 인덱스 노드가 복잡해지고 인덱스의 용량이 커지기 때문에 조회에 필요한 칼럼들을 인덱스 키 칼럼에는 포함시키지 않으면서 인덱스 페이지에서 사용할 수 있도록 하는 방법

### 인덱스 적용 성능 테스트
```sql
-- NoIndex : table with no indexes
-- Cluster : clustered index on id
-- NonCluster : non clustered index on id include name clumn
-- BothIndex : table with both indexes (primary key clustered, unique nonclustered index)

-- [1] 테이블 생성 & 인덱스 생성
USE [STUDY]
GO
/****** Object:  Table [dbo].[BothIndex]    Script Date: 2022-03-11 오후 6:04:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BothIndex](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](10) NOT NULL,
	[date] [datetime] NOT NULL,
 CONSTRAINT [PK_BothIndex] PRIMARY KEY CLUSTERED 
(
	[id] ASC,
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY],
 CONSTRAINT [IX_BothIndex] UNIQUE NONCLUSTERED 
(
	[id] ASC,
	[name] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[Cluster]    Script Date: 2022-03-11 오후 6:04:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Cluster](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](10) NOT NULL,
	[date] [datetime] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Index [IDX_Cluster]    Script Date: 2022-03-11 오후 6:04:52 ******/
CREATE CLUSTERED INDEX [IDX_Cluster] ON [dbo].[Cluster]
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[NoIndex]    Script Date: 2022-03-11 오후 6:04:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NoIndex](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](10) NOT NULL,
	[date] [datetime] NOT NULL
) ON [PRIMARY]
GO


/****** Object:  Table [dbo].[NonCluster]    Script Date: 2022-03-11 오후 6:04:52 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[NonCluster](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[name] [nvarchar](10) NOT NULL,
	[date] [datetime] NOT NULL
) ON [PRIMARY]
GO
/****** Object:  Index [IDX_NonCluster]    Script Date: 2022-03-11 오후 6:04:52 ******/
CREATE NONCLUSTERED INDEX [IDX_NonCluster] ON [dbo].[NonCluster]
(
	[id] ASC
)
INCLUDE([name]) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
GO



-- [2] 테스트 데이터 생성
SET NOCOUNT ON

DECLARE @I INT
SET @I = 0
WHILE @I < 200000

   BEGIN
      INSERT INTO dbo.NoIndex (name, date)
	       VALUES (@I, GETDATE())
      SET @I = @I + 1
   END

insert into [dbo].[Cluster]([name], [date]) select [name], [date] from [dbo].[NoIndex]
insert into [dbo].[NonCluster]([name], [date]) select [name], [date] from [dbo].[NoIndex]
insert into [dbo].[BothIndex]([name], [date]) select [name], [date] from [dbo].[NoIndex]
SET NOCOUNT OFF


-- [3] 성능비교
-- 메뉴> 쿼리 > 실제 실행계획 포함


-- IO 통계보기
SET STATISTICS IO ON
-- 쿼리 ex
SELECT COUNT(*) FROM NoIndex       WHERE id < 10000 and [name] = 2222
SELECT COUNT(*) FROM Cluster       WHERE id < 10000	and [name] = 2222
SELECT COUNT(*) FROM NonCluster    WHERE id < 10000	and [name] = 2222
SELECT COUNT(*) FROM BothIndex     WHERE id < 10000	and [name] = 2222

SET STATISTICS IO OFF
```

<img src="https://github.com/hskim2019/Memo/blob/master/images/indextest_20220311.png?raw=true"/>