package hello.spring_core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonWithPrototypeTest {

    @Test
    void prototypeFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(Prototype.class);

        Prototype prototype1 = ac.getBean(Prototype.class);
        prototype1.addCount();
        Assertions.assertThat(prototype1.getCount()).isEqualTo(1);


        Prototype prototype2 = ac.getBean(Prototype.class);
        prototype2.addCount();
        Assertions.assertThat(prototype2.getCount()).isEqualTo(1);



    }

    @Scope("prototype")
    static class Prototype {

        private int count = 0;

        public void addCount(){
            count++;
        }


        //count 조회
        public int getCount(){
            return count;
        }

        @PostConstruct
        //this로 참조값 찍어서 확인하기
        public void init(){
            System.out.println("Prototype.init:" + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Prototype.destroy = " + this);
        }
    }
}
