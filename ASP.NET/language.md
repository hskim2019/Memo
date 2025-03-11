## Class(클래스), Object(객체), Instance(인스턴스), Constructor(생성자), this, static, 지역변수
[참조](https://blog.naver.com/PostView.nhn?blogId=myjet1490&logNo=221369557011)
* 클래스   
    - 객체를 정의 해 놓은 것, 객체를 생성하기 위한 설계도, 객체는 붕어빵 클래스는 붕어빵 틀
    - namespace, 클래스 안에 선언 된 것들은 member, 멤버변수, 메서드
* 객체/인스턴스
    - 클래스에 정의 된 내용이 메모리에 생성된 것 
    - 클래스로부터 객체를 만드는 것이 인스턴스화
    - 클래스는 정의 후 반드시 new 연산자(객체 생성, 생성자 호출) 로 객체(인스턴스) 생성을 해야만 사용 가능
* 생성자
    - 객체가 생성될 때 호출되는 인스턴스 초기화 메서드
* this
    - 객체가 자기 자신을 가리키고 싶을 때 사용하는 키워드
* static (정적) 변수, 메서드 (클래스 변수)  
    - [static이란](/memo/asp.net/language.md/#static이란-)
    - 클래스 자체에 소속되도록 static 한정자를 사용하여 만들어주며, 프로그램 전체에 걸쳐 공유해야 하는 경우 사용
    - 인스턴스 생성 없이 클래스 이름.클래스 변수명을 통해서 접근 할 수 있음
* 지역변수
    - 메서드 내에 선언 되어서 메서드 내에서만 사용
    - 메서드가 실행 될 떼 메모리에 할당 됨, 메서드 끝나면 소멸

## class 와 struct 차이
[Reference](https://velog.io/@livelyjuseok/C-Struct%EC%99%80-Class%EC%9D%98-%EC%B0%A8%EC%9D%B4.-%EA%B7%B8%EB%A6%AC%EA%B3%A0-%EC%99%9C-%EC%82%AC%EC%9A%A9%ED%95%A0%EA%B9%8C)
* 상속 여부
    - Class 는 상속이 가능하지만, 구조체는 상속이 불가능하다.
* 형식의 차이
    - Struct는 값 타입(Value Type)이지만 Class는 참조(Reference Type)이다.
    - 값 타입은 스택 메모리 영역에 할당, 참조 타입은 힙 영역에 할당되며 가비지 컬렉터가 힙에 할당된 메모리를 정리, 스택은 사용 후 종료된다.

## Static이란?
- 어떠한 값이 메모리에 한 번 할당되면 프로그램이 끝날 때 까지 그 값이 유지 됨
- 특정한 값을 공유해야 하는 경우 static 사용
- 인스턴스 변수는 클래스가 생성될 때 메모리 공간이 할당되고 생성자에 있는 값으로 초기화 됨 = 서로 다른 값 유지
- static은 선언 되는 숭간 하나의 메모리에 공간이 할당 되기 때문에 클래스 내에서 공유 됨 = 공통적인 값 유지
- static 메서드는 객체 생성 없이 호출 가능
- static 메서드 안에서 인스턴스 변수 접근 불가능

## 멤버변수
- 클래스 안에 선언 된 변수
- 멤버변수는 클래스변수와 인스턴스 변수로 나뉜다
- 클래스 변수 :  
   static으로 선언하여 인스턴스를 생성하지 않아도 호출할 수 있게 할 수 있고  
   new 연산자를 통해 인스턴스 생성 시 static은 제외하고 생성 됨
- 인스턴스 변수 :  
   new를 통하여 인스턴스 생성 시 메모리가 할당되어 인스턴스 생성 후 사용 가능

```c#
class Person {
   public int age = 10;
   public static string name = "eugene";
   public Person() {}
}

namespace ConsoleApp 
{
   class Program {
       static void Main(string[] args) {
           Console.WriteLine(Person.name); // 객체 생성 없이 name 호출 가능
                                           // 객체를 생성하지 않아 age 는 호출 불가능
           Person person = new Person();
           Console.WriteLine(person.age); // 객체 생성 후 멤버변수인 age 접근 가능
                                          // person.name으로는 클래스 변수인 name에 접근 x
       }
   }
}
```
## 상속 Inheritance
[Reference: 상속 https://www.geeksforgeeks.org/c-sharp-inheritance/](https://www.geeksforgeeks.org/c-sharp-inheritance/)</br>
[Reference: 인터페이스 Interface](https://daekyoulibrary.tistory.com/entry/C-%EA%B0%9D%EC%B2%B4%EC%A7%80%ED%96%A5%EC%9D%98-%EA%BD%83-%EC%9D%B8%ED%84%B0%ED%8E%98%EC%9D%B4%EC%8A%A4Interface-1%ED%8E%B8)</br>
[Reference : C# 다중상속](https://velog.io/@livelyjuseok/C-C%EC%9D%80-%EC%99%9C-%EB%8B%A4%EC%A4%91%EC%83%81%EC%86%8D%EC%9D%B4-%EC%95%88%EB%90%A0%EA%B9%8C)</br>

    * C# 에서는 다중 상속 Multiple Inheritance 를 지원하지 않는다. 인터페이스를 통해서만 다중 상속 구현이 가능하다.
    * 다중 상속 했을 때 같은 함수를 두 개씩 가지게 된다면 어떤 함수를 실행하고자 하는지 알 수 없는 모호성 문제가 발생한다
    * 인터페이스는 메소드, 이벤트, 인덱서, 프로퍼티만 가질 수 있고 구현부가 없다.
    * 모든 것이 public 으로 선언되어야 하며
    * 인터페이스를 상속 받은 클래스에서 오버라이딩 해야 한다

## ref out 키워드 차이
[Reference](https://www.geeksforgeeks.org/difference-between-ref-and-out-keywords-in-c-sharp/)</br>
[Reference](https://jettstream.tistory.com/57)</br>
    * ref
        - 변수를 참조로 전달하는데 사용되며 변수가 메서드 안에서 변경될 수 있다
        - 매서드 내에서 전달하는 변수 변경해야 할 때 사용하면 좋다.
        - 변수 초기화 필수

    * out
        - 변수를 참조 타입으로 전달하는데 사용되며 변수를 전달하기 전에 변수를 초기화 하지 않아도 된다. 하지만 메서드 안에서 반드시 할당되어야 한다.
        - 보통 여러 변수를 리턴해야 할 때 사용한다
        - 코드의 가독성을 높여준다

## Property
[Reference](https://constructionsite.tistory.com/38)
    * 프로퍼티는 선언한 변수의 값을 외부에서 접근할 수 있도록 하면서 동시에 캡슐화를 지원한다.
    * 정보은닉을 위해 private 변수를 선언하고, 이 변수를 읽고 쓰는 get, set 메서드를 만들기도 하는데
    * c# 에서는 자동 구현 프로퍼티를 사용해 get set 접근자에 return 을 굳이 쓰지 않아도 된다.
    * 예를 들어 외부에서 변경하면 안되는 값이라면 set 을 없애고 get 속성만 부여 가능

## Encalsulation
[Reference](https://www.geeksforgeeks.org/c-sharp-encapsulation/)</br>
[Reference](https://developer-talk.tistory.com/478)</br>
    * 필드를 private로 선언하여 클래스 외부에서 접근할 수 없도록 하고
    * public 으로 선언된 메서드(getter 및 setter) 를 사용하여 클래스 내부의 필드를 접근하는 것

## async - await
    * async 키워드는 매서드 내에 await 키워드를 사용할 수 있게 만들어주고, async method 는 void, Task, Task<T> 를 반환
    * await 는 비동기 작업의 흐름을 제어하는 키워드로, await를 통해 비동기 작업의 순서를 정해줄 수 있음. 작업을 차단하지 않는 방식으로 해당 작업이 완료되면 계속 실행 될 수 있도록 제어.

## Access Modifiers (접근제한자)
|**제한자**|사용|
|:---:|:---|
|public|모든 곳에서 접근 가능|
|internal|같은 어셈블리 내에서만 public으로 접근|
|protected|클래스 외부에서 접근할 수 없으나 파생 클래스에서 접근 가능|
|private|클래스 내부에서만 접근|

- 명시하지 않으면 internal 로 지정 됨
- virtual : 함수나 속성등에 virtual 을 붙이면 자식 클래스/구조체에서 override 해서 재 구현 시켜 줄 수 있다는 의미

## Boxing / UnBoxing
<https://docs.microsoft.com/en-us/dotnet/csharp/programming-guide/types/boxing-and-unboxing>
<https://afsdzvcx123.tistory.com/entry/C-%EB%AC%B8%EB%B2%95-%EB%B0%95%EC%8B%B1%EA%B3%BC-%EC%96%B8%EB%B0%95%EC%8B%B1%EC%9D%B4%EB%9E%80>
* Value Type 
    * int, char, double...
    * 스택 영역에 저장 됨 [메모리구조 참조](/Memo/CS/OS.md#메모리-구조)
    * Object 를 상속 받은 System.ValueType을 상속받은 구조체
* Reference Type
    * System.Object 를 상속 받으며
    * 힙 영역에 저장
    * 처음 변수 선언 시 값 타입과는 달리 메모리 생성되지 않음
    * 생성 후에 힙 메모리 할당 후 값에 대한 참조만 스택에 저장
* Boxing 
    - 값 타입의 객체를 참조타입으로 변환
    - 스택에 저장 된 값 타입을 힙 타입으로 복사하고 복사된 영역을 참조 타입이 가리키게 됨
* UnBoxing
    - 참조타입 값을 값 타입으로 변환
    - 힙에 있던 데이터를 다시 스택으로 복사
```c#
int i = 123 // a value type
Object o = i // boxing
int j = (int) o; // unboxing
```
* 박싱과 언박싱은 메모리에 영향을 미치므로 가능하면 제네릭 컬렉션을 사용하는 것이 좋음
```c#
// 비 제네릭 컬렉션
ArrayList arrList = new ArrayList();
arrList.Add(15);
// 제네릭 컬렉션  System.Collections 대신 System.Collections.Generic
List<int> intList = new List<int>();
intList.Add(15);
```

## generic class 제네릭 클래스
* 특정 데이터 형식이 없는 클래스나 객체를 만들 때 사용
* 클래스를 사용할 때 타입을 지정해주는 기술 - 구현 코드 재사용 가능
* Object 사용으로 인한 Boxing 변화 차단 - Boxing을 피해서 메모리 덜 소모
```C#
// Declare the generic class.
public class GenericList<T>
{
    public void Add(T input) { }
}
class TestGenericList
{
    private class ExampleClass { }
    static void Main()
    {
        // Declare a list of type int.
        GenericList<int> list1 = new GenericList<int>();
        list1.Add(1);

        // Declare a list of type string.
        GenericList<string> list2 = new GenericList<string>();
        list2.Add("");

        // Declare a list of type ExampleClass.
        GenericList<ExampleClass> list3 = new GenericList<ExampleClass>();
        list3.Add(new ExampleClass());
    }
}
```

---
### etc.
---
* API 컨트롤러는 Http 통신에 담당하는 ControllerBase 클래스를 파생하여 정의하고.  
반면, MVC 컨트롤러는 Controller 클래스를 파생하여 정의한다. (controller 가 page 반환)

### IActionResult - status code results
<https://exceptionnotfound.net/asp-net-core-demystified-action-results/>

### abstract virtual interface
#### virtual
- 파생 클래스에서 필요에 따라서 재정의(override) 할 수 있지만 필수로 재정의 할 필요 없음
#### interface
- 구현이 없는 클래스로
- 추상 클래스와 비슷하지만 멤버변수를 사용할 수 없고 프로퍼티는 사용 가능
- 다른 클래스 작성 시 도움을 주는 목적으로 사용 (표준화)
- 접근 제한자 사용 불가하고 모든 것이 public으로 선언
```C#
namespace ConsoleApp
{
  interface Store
  {
      void welcome();

      string Name {get; set;}
  }
  class Cafe : Store
  {
     public void welcome() {

     }
     private string name;
     public string Name 
     {
         get{return name;}
         set{name = value;}
     }
  }
  class Program
  {
      public void Main(string[] args) {
          Cafe store = new Cafe() {Name = "tester"};
          store.welcome();
          Console.WriteLine(store.Name);
      }
  }
}
```
#### abstract
- 추상클래스의 목적은 파생 클래스에서 공유해야 하는 공통 정의를 제공하는 것이기 때문에
- 파생 클래스에서 abstract 한정자가 달린 것을 반드시 재정의 구현 해야 함
```C#
// 추상 클래스 : 객체를 만들 수 없고 접근하려면 다른 클래스에서 상속 되어야 함
abstract class Store { 
    // 추상 메서드 : 추상 클래스에서만 사용 가능하고 본문이 없음
    // 본문은 하위 클래스에서 재정의 시 구현
    public abstract void welcome();
    // 추상 클래스는 일반 메서드도 가질 수 있음
    public void greeting() {
        Console.WriteLine("Hello");
    } 
}

class Cafe : Store
{
   public override void welcome() {
       Console.WriteLine("Welcome");
   }
}

namespace ConsoleApp 
{
   class Program {
       static void Main(string[] args) {
           // Store store = new Store();  객체를 만들 수 없음
           Cafe cafe = new Cafe();
           cafe.welcome();
           cafe.greeting();
       }
   }
}
```
# 예외처리
- try : 예외 검사 할 블록
- catch: 예외 핸들러를 사용하여 예외를 catch
- finally: 예외가 catch 되었는지 여부와 상관 없이 실행하는 블록
- throw: 문제 발생 시 예외 throw



