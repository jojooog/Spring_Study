package hello.spring_core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void StatefulServiceTest(){
        //스프링 컨테이너 생성
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //사용자A 10000원 주문
        statefulService1.order("userA", 10000);

        //사용자B 20000원 주문
        statefulService2.order("userB", 20000);

        //사용자A가 주문금액을 조회한다
        int orderPrice = statefulService1.getPrice();
        System.out.println("orderPrice = " + orderPrice);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        Assertions.assertThat(statefulService1).isEqualTo(statefulService2);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
