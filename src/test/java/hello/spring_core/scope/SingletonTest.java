package hello.spring_core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTest {

    @Test
    void singletoneTest(){
        //스프링 컨테이너 생성
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Singleton.class);

        Singleton singleton1 = ac.getBean(Singleton.class);
        Singleton singleton2 = ac.getBean(Singleton.class);

        System.out.println("singleton1 = " + singleton1);
        System.out.println("singleton2 = " + singleton2);


        //싱글톤 객체 확인
        Assertions.assertThat(singleton1).isSameAs(singleton2);

        ac.close();
    }




    //기본이 싱글톤이라 지정 안해도됨
    @Scope("singleton")
    static class Singleton{

        @PostConstruct
        public void init(){
            System.out.println("singleton.init");
        }


        @PreDestroy
        public void destroy(){
            System.out.println("singleton.destroy");
        }

    }


}
