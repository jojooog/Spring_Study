package hello.spring_core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient(){
        System.out.println("생성자 호출");
        connect(); //connect 함수 호출
        call("연결 메시지");
    }

    public void setUrl(String url){
        this.url = url;
    }


    //서비스가 시작하면 호출
    public void connect(){
        System.out.println("connect: "+ url);
    }

    //호출하면 메시지 출력
    public void call(String message) {
        System.out.println("call: " + url + "message: " + message);
    }

    //서비스 종료시 호출
    public void disconnected(){
        System.out.println("closed: " + url);
    }
}
