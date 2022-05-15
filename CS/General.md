####

운영체제 : 스레드, 프로세스, 스케줄링 알고리즘, 캐시 알고리즘, 데드락, 락

자료구조 : 리스트, 스택, 큐, 맵, 해시 ( + 내가 쓰는 언어의 자료구조 구현체들 )

DB : DB 설계 ( 정규화, 비정규화 ) , 트랜잭션 개념, 트랜잭션 ACID, 트랜잭션 propagation, 트랜잭션 locking level, 인덱스 구조

네트워크 : ip, 서브넷, http, https, REST, http/2, tcp udp

보안 : CSRF, CORS, 대칭키, 비대칭키 알고리즘

* SE? proxy
* 엘라스틱 서치
* tcp/ udp
* jpa n+1
####



# 일반
---
### 좋은 코드란?
* 읽기 쉬운 코드
* 중복이 없는 코드
* 테스트가 용이한 코드

## 객체 지향 프로그래밍이란?
* 현실 세계를 프로그래밍으로 옮겨온 것으로, 현실 세계의 사물을 객체로 보고,  
  그 객체로부터 개발하고자 하는 애플리케이션을 필요한 특징을 뽑아 프로그래밍 하는 것이 추상화(공통의 속성이나 기능을 묶어 이름을 붙이는 것)
* <u>문제를 여러 개의 객체 단위로 나누어 작업하는 방식</u>이라고 할 수 있다.
* 클래스를 이용해서 연관 있는 처리부분(함수)과 데이터(변수) 를 하나로 묶어 객체(인스턴스) 를 생성해 사용함
* 이렇게 독립적인 객체로 분리해서 작업하면 다른 사람이 만든 클래스를 가져와서 이용할 수도 있으므로 <u>"코드 재사용성이 용이"</u>하고, 수정이 필요할 때 클래서 내부 멤버 변수 혹은 메서드를 수정하면 되기 때문에 <u>"유지보수가 쉽고"</u>, 모듈화 시켜서 개발할 수 있으므로 <u>"대형프로젝트에 적합"</u>, 그만큼 설계가 잘 되어야 하고, 객체가 많으면 용량이 커질 수 있음
* Object Oriented Programming = OOP로 코드를 작성하면 <u>(상속, 캡슐화, 다형성으로 인해) 코드 재사용성이 용이하다. 프로그램 수정 시 캡슐화를 통해 주변 영향이 적기 때문에 유지보수에도 용이하다</u>.
  * 캡슐화? 
    * 코드를 재수정 없이 재활용 하는 것
    * 접근 제어자를 통한 정보 은닉
      - 객체가 외부에 노출하지 않아야 할 정보 또는 기능을 접근제어자를 통해 제어 권한이 있는 객체에서만 접근할 수 있도록 함
  * 추상화?
    - 공통의 속성이나 기능을 묶어 이름 붙이는 것
    - [abstract](/memo/asp.net/language.md/#abstract-virtual-interface)
  * 상속성?
    - 부모 클래스의 속성과 기능을 그대로 이어받아 사용할 수 있게 하고 기능의 일부를 상속받은 자식 클래스에서 알맞게 수정하여 사용 할 수도 있게 하는 것
  * 다형성?
    - 같은 이름이지만 구현이 다른 여러 메소드 사용 가능 
    - 오버라이딩, 오버로딩이 가능
  * getter/setter 사용하는 이유?
  - 메서드를 이용해서 변수에 접근하기 때문에 메서드 안에서 올바르지 않은 입력에 대해 사전에 처리할 수 있게 제한 하거나 조절 가능

### 오버라이딩 오버로딩 overriding overload
[참조](https://link2me.tistory.com/872)
* overriding: 
    - 부모 클래스에서 정의 된 멤버 메서드를 자식에서 재정의
    - 메서드를 오버라이딩 할 때 부모 클래스에서는 virtual 로, 자식 클래스에서는 override 키워드로 메서드를 정의
    - 부모 클래스에 virtual 를 미정의 시, 자식 클래스에서 메소드 앞에 new를 붙이면 된다.
* overloading
    - 한 클래스 내에서 같은 이름의 함수를 여러개 정의하고, 매개변수의 타입과 개수를 다르게 하는 것(signature)

### 의존성 주입
    * 필요한 클래스를 직접 생성하는 것이 아닌 주입 해 줌으로써 객체 간 결합도를 줄이고 유연한 코드 작성 가능
    * 한 클래스 수정했을 때 다른 클래스 수정해야 하는 상황을 막을 수 있음
    * 의존성이 있는 코드 예)
[[link]](https://devscb.tistory.com/77)
```java
   // Computer 가 Monitor 클래스에 의존하는 형태
    class Computer {
        Computer() {}
        void on() {
            Monitor m = new Monitor()
            m.turnOn(); // Monitor method가 바뀐다면?
        }
    }

    class Monitor() {
        Monitor(){}
        void turnOn() {
            print("turn on")
        }
    }
```
[[link]](https://bamdule.tistory.com/174)
```java
public interface OS {
    public void booting();
}

public class Windows implements OS {

    @Override
    public void booting() {
        System.out.println("Windows booting !");
    }
}

public class Linux implements OS {

    @Override
    public void booting() {
        System.out.println("Linux booting !");
    }

}

public class Computer {

    private OS os;
    //외부에서 객체를 주입받는다.
    public Computer(OS os) {
        this.os = os;
    }

    public void booting() {
        os.booting();
    }
}

public class Main {
    public static void main(String[] args) {

        OS windows = new Windows();
        Computer windowsComputer = new Computer(windows);

        OS linux = new Linux();
        Computer linuxComputer = new Computer(linux);
        
        windowsComputer.booting();
        linuxComputer.booting();
    }
}
```
    * 객체를 직접 생성하는 것이 아니라 외부로부터 필요한 객체를 받아서 사용
    * => 객체간 결합도를 줄이고 코드 재활용성을 높임

### git svn
    - SVN 은 내 로컬PC 에서 Commit을 하면 바로 중앙저장소에 반영이 되는 반면  
    GIT 은 내 로컬PC에서 Commit을 하면 로컬 저장소에 반영이 되고  
    로컬저장소에서 Push를 하면원격저장소에 반영

### jwt
[참조_link](https://blog.outsider.ne.kr/1160)
* 장점  
    - 인증에 필요한 정보를 토큰 자체에 포함하기 떄문에 별도의 인증 저장소 필요 없음
    - 토큰만 가지고 검증 가능하므로 서버를 stateless 상태로 유지 할 수 있음

### https
- 내 사이트에 보내는 정보를 제 3자가 못 보게
- 신뢰할 수 있는 사이트인지 판별(검증 된 사이트만 https 사용 허가)
- 위 두 개를 가능하게 하는 원리? 
    - 대칭키(암호화-복호화에 대칭인, 같은 키 사용) 비대칭키(=공개키, ex.A키로 암호화 하면 B키로 복호화 가능)
    - 서버에서 사이트로 보낸 데이터(개인키로 암호화)도 암호화 된 상태이므로 사이트에서 공개키로 풀어서 데이터 확인하는데 풀리지 않으면 신뢰할 수 없는 사이트
        - 브라우저에 CA(공개키를 인증 해주는 공인 기관들) 목록이 내장되어 있음<br>
        -> browser(client) 에서 server 로 랜덤 데이터 전송<br>
        -> server 에서 client로 응답하며 랜덤 데이터와 해당 서버의 인증서 실어 보냄<br>
        -> browser에 내장 된 CA 정보를 통해 인증서가 진짜인지 확인
        -> 확인 되면 이후에는 대칭키와 비대칭키 방식 혼합되어 사용 됨
            모든 데이터를 일일히 비대칭키로 암호화, 복호화 하는 것은 효율적이지 못하므로<br>
            데이터를 대칭키로 암호화 하여 주고받고 대신 대칭키 공유를 비대칭 키로 함<br>
            (대칭키도 처음 handshake 할 때 주고받은 랜덤 데이터를 활용하여 만들어짐)