package hello.spring_core.beanfind;

import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

//스프링 빈 상속관계 조회
public class ApplicationExtendsTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);


    @Test
    @DisplayName("부모 타입으로 전부 조회")
    void findAllTypes(){
        Map<String, DiscountPolicy> listOfBeans= ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertThat(listOfBeans.size()).isEqualTo(2);
        for(String key : listOfBeans.keySet()) {
            System.out.println("key = " + key + "value = " + listOfBeans.get(key));
        }
    }


    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBySubType(){
        RateDiscountPolicy bean = ac.getBean(RateDiscountPolicy.class);
        Assertions.assertThat(bean).isInstanceOf(RateDiscountPolicy.class);
    }



    @Test
    @DisplayName("부모타입으로 조회할 때 자식이 둘 이상 있으면 빈 이름 지정")
    void findByDuplicatedBeanName(){
        DiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DiscountPolicy.class);
        //부모타입으로 빈 조회
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }


    @Test
    @DisplayName("부모타입으로 조회할 때 자식이 둘 이상 있으면 중복오류 발생")
    void findByDuplicatedBean(){
        DiscountPolicy discountPolicy = ac.getBean(DiscountPolicy.class);
        //부모타입으로 빈 조회
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(DiscountPolicy.class));
    }


    @Configuration
    static class TestConfig {

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
