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