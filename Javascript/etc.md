### 컴파일 과정이 없는 자바스크립트
- complie 과정이 없기 때문에 실행 시점에 해석되고 실행 된다
- 그렇기 때문에 내장 함수를 최대한 활용하는 것이 자바스크립트를 고속화 시키는 방법

#### Array.prototype.slice.call(obj/arguments)
#### Array.prototype.slice.apply(obj/arguments)
- Array.prototype의 slice method를 빌려오기
- 일반 literal 객체와arguments에는 배열 method가 없고, 호출 할 수가 없기 때문에
- 여기서의 arguements 는 배열이 아닌 배열처럼 생긴 객체