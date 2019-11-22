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

### TETXT
### MEDIUMTEXT
### LONGTEXT

<br>

## [날짜형 데이터 타입]
---
### DATE
- YYYY-MM-DD  
#

### DATETIME
- YYYY-MM-DD HH:MM:SS  
#
    
### TIMESTAMP
- ????  
    

