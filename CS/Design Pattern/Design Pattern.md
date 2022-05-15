# Design Pattern

## Singleton
* https://github.com/hskim2019/bitcamp-java-2018-12/blob/master/java-basic/src/main/java/design_pattern/singleton/Test02.java
* 객체의 인스턴스가 1개만 생성되는 패턴
* 싱글톤 패턴 사용 이유?
    - 인스턴스가 한 개만 있는 것을 보증하고 싶을 때, Global State 전역상태로 이용하고자 할 때
    - new 연산자를 통해 고정된 메모리 영역을 사용하기 때문에 메모리 낭비를 방지하고 이미 생성 된 인스턴스를 활용하므로 속도 측면에서의 이점이 있다
* 단점lll
    - 자신만이 객체를 생성할 수 있도록 생성자를 private으로 제한하여 상속 불가능
    - [?] 테스트가 힘들다
    - [?] 서버환경에서 싱글톤이 1개만 된다는 점을 보장할 수 없다