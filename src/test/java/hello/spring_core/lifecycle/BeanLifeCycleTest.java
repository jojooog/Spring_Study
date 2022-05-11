package hello.spring_core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void LifeCycleTest(){
        //ConfigurableApplicationContext는 ApplicationContext를 상속한다
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

//1. 객체 생성 -> 의존관계 주입
    @Configuration  //설정정보 등록
    static class LifeCycleConfig{

        @Bean(initMethod = "init", destroyMethod = "close")
        public NetworkClient networkClient(){
            NetworkClient networkClient = new NetworkClient();
            //객체 생성 당시에는 url이 없는 상태
            //객체 생성과 초기화 하는 부분을 명확하게 나누는 것이 좋다
            networkClient.setUrl("http://hello-spring");
            return networkClient;
        }


    }
}
