# Full Text Search

## Summary
1. Install Full-Text search
2. Define Full-Text Catalog
3. Define Full-Text Index

### Installation
* sql server version : Express with Advanced Services (SQLEXPRADV)  
This package contains Polybase and <b>Full Text Search features</b> in addition to SQL Database Engine.
* [sql server download link] (https://www.microsoft.com/en-us/Download/details.aspx?id=101064)
* 기존에 lightweight version 으로 설치해서 Advanced version 으로 재설치 함

* Advanced version 으로 설치 되었고 Full-Text features 만 추가 설치 하면 되는 경우
    1. Start Sql Server Installation Center
    2. [Installation] - [New SQL Server stand-alone installation or add features to an existing installation]
        * exisitng folder e.g) C:\SQL2019\Express_EU
    3. [Installation Type] - [Add features to an exsiting instance of SQL Server 2019]

### SQL Full-Text Filter Deamon Launcher
 * 설치 완료되면 구성관리자에서 확인 할 수 있음

### Check whether Full text Search is Installed or not on 
```sql
SELECT fulltextserviceproperty('IsFulltextInstalled') as [Full-Text Search]
-- 1 feature is installed
-- 0 not installed
```


### Reference
[Using full-text search with Azrue SQL Database] (https://www.sqlcoffee.com/Azure_0023.htm)
[전체 텍스트 검색 세팅하기] (https://calssess.tistory.com/93)
[Step by Step Process to Add Full Text Search on SQL Server 2019] (https://www.mssqltips.com/sqlservertip/6841/add-full-text-search-sql-server/)


