package hello.spring_core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeFindTest(){


        //스프링 컨테이너 등록
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class);

        System.out.println("find prototypeBean1");
        Prototype prototype1 = ac.getBean(Prototype.class);

        System.out.println("find prototypeBean2");
        Prototype prototype2 = ac.getBean(Prototype.class);

        System.out.println("prototype1 = " + prototype1);
        System.out.println("prototype2 = " + prototype2);

        Assertions.assertThat(prototype1).isNotSameAs(prototype2);

        //스프링 컨테이너가 관리하지 않기 때문에
        //종료 메서드를 직접 실행해야한다.
        prototype1.destory();
        prototype2.destory();

        ac.close();


    }


    @Scope("prototype")
    static class Prototype{

        @PostConstruct
        public void init(){
            System.out.println("prototype.init");
        }

        @PreDestroy
        public void destory(){
            System.out.println("prototype.destroy");
        }
    }
}
