package hello.spring_core.singleton;

//싱글톤 객체 만들기
public class SingletonService {

    //static영역에 객체 인스턴스 생성
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    //생성자를 private으로 만들어서 외부에서는 객체가 생성되지 않도록 만든다
    private SingletonService(){}

    public void singletonCall(){
        System.out.println("싱글톤 객체 호출");
    }
}
