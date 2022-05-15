package hello.spring_core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

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

    @Test
    void singletonWithPrototypeTest(){
        AnnotationConfigApplicationContext ac =
                new AnnotationConfigApplicationContext(Singleton.class, Prototype.class);
        Singleton singleton1 = ac.getBean(Singleton.class);
        Singleton singleton2 = ac.getBean(Singleton.class);

        int count1 = singleton1.logic();
        Assertions.assertThat(count1).isEqualTo(1);

        int count2 = singleton2.logic();
        Assertions.assertThat(count2).isEqualTo(1);
    }


    //생성 시점에 주입된 프로토타입빈을 쓴다
    @Scope("singleton")
    static class Singleton{


//        @Autowired
//        private ObjectProvider<Prototype> prototypeBean;

        @Autowired
        private Provider<Prototype> prototypeBean;


        public int logic(){
            Prototype prototype = prototypeBean.get();
            prototype.addCount();
            int count = prototype.getCount();
            return count;
        }

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
