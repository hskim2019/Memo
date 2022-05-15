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


