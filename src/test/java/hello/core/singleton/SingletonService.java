package hello.core.singleton;

public class SingletonService {

    //1. static 영역에 객체를 딱 1개만 생성한다.
    private static final SingletonService instance = new SingletonService();

    //2. public  으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회되도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    //3. 생성자를 private 으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    // 생성하려고 하면 컴파일 오류. 컴파일 오류 나도록 설계하는 것이 베스트!
    private SingletonService() {}

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

    /* 싱클톤 패턴 단점. 안티패턴으로 불리는 이유
    * 싱글톤 패턴을 구현하는 코드 자체가 많아짐
    * 클라이언트가 구체 클래스에 의존 => DIP 위반. OCP 위반 가능성
    * 테스트 어려움. 초기화 어려움.
    * 즉, 유연성이 떨어진다.
    * */
}
