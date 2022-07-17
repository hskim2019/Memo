# Web 취약점

## Null byte injection (널 바이트 인젝션)
  * url로 인코딩 된 널 바이트 문자열(%00, 0x00, \x00, \x0) 은 문자열의 끝을 의미하기 때문에<br>
    특정 확장자를 숨기기 위한 목적으로 사용될 수 있다. <br>
  * 문자열을 저장할 때 null 문자를 삽입하여 null 문자열이 있는 곳까지만 출력하고 끝난 것으로 간주함을 이용한 공격
  * 주로 perl, php, java에서 취약
  * http://xxx.xxx/bbs.php?file=log.txt%00.gif <br>
    null byte 삽입 시 문자열이 끝난 것으로 간주하여 .gif 르르 무시하기 때문에 우회 가능
  * [[reference]](https://jeongminhee99.tistory.com/25) : https://jeongminhee99.tistory.com/25
