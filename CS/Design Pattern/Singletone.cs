using System;
class Car2 {
  // 인스턴스 주소를 받을 클래스 필드를 선언한다
  private static Car2 instance;

  // 1) 생성자를 정의하고 private로 선언하여 비공개로 만들어라
  //     => 비공개 생성자를 외부에서 호출할 수 없다
  //     => 오직 내부에서만 호출할 수 있다
  private Car2() {}

  // 2) 인스턴스를 생성해주는 메서드를 정의한다
  public static Car2 getInstance() {
    if (Car2.instance == null) {
      // 아직 인스턴스를 생성한 적이 없다면 즉시 인스턴스를 생성한다
      Car2.instance = new Car2();
    }
    // 기존에 변수에 저장된 인스턴스 주소를 리턴한다
    return Car2.instance;
  }
}

class Program {
  public static void Main (string[] args) {
    Car2 c2 = Car2.getInstance();
    Car2 c3 = Car2.getInstance();
    
    if(c2 != c3)
      Console.WriteLine("다르다!");
    else
      Console.WriteLine("같다!");
  }
}
