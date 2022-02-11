### .net framework / .net core
    * 닷넷 프레임워크 : 윈도우 전용
    * 닷넷 코어 : 멀티 플랫폼
    
#### .NET Framework
    * Windows 전용
    * .NET Framework 대표 라이브러리 - WPF, Windows Forms, ASP.NET을 사용하여 Windows 응용 프로그램 빌드 가능
#### .Net Core
    * 윈도우, 리눅스, macOS 사용 가능
    * UWP 및 ASP.NET Core 만 지원
    * 클라우드 사용과 인터넷 연결 앱을 만들기 위한, 브라우저 기반 웹 응용 프로그램 작성에 용이
    * Razor Page 지원
    * 경량화로 인해 가벼움
    * Docker 사용 가능
#### Xamarin
    * 모바일 환경에 제한된 기능
    * Android, IOS, Window mobile 등 지원

### Entity Framework
    * Database First 방식: 데이터베이스 테이블을 이용해서 데이터 모델 객체 자동 생성 : 테이블 생성 -> (Visual Studio에) 테이블 정보 불러오기 -> C# 클래스 생성
    * Model First: 데이터모델 객체를 정의하고 모델을 이용해서 데이터베이스에 테이블 자동 생성: (Visual Studio에서) Model들의 관계 설정 -> C# 클래스와 테이블 생성
    * Code First: C# 클래스 작성 -> Table 생성

    * .NET 전용 ORM(객체 관계 맵핑 Object- relational mapping) 프레임워크
    * EDM(entity data model) 을 만들고 LINQ를 이용해서 데이터 접근 가능
    * 데이터 테이블 구조를 클래스화 시키고 각 객체에 데이터를 맵핑시키는 기술
    * 단점: DB 성능을 기대하기 어려움


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
    * 스택 영역에 저장 됨 [메모리구조 참조](/Memo/ETC/CS.md#메모리-구조)
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

---
### etc.
---
* API 컨트롤러는 Http 통신에 담당하는 ControllerBase 클래스를 파생하여 정의하고.  
반면, MVC 컨트롤러는 Controller 클래스를 파생하여 정의한다. (controller 가 page 반환)

### IActionResult - status code results
<https://exceptionnotfound.net/asp-net-core-demystified-action-results/>
