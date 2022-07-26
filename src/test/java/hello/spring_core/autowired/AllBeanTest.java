package hello.spring_core.autowired;

import hello.spring_core.AutoAppConfig;
import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean(){

     ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
        int discountPrice2 = discountService.discount(member, 20000, "rateDiscountPolicy");

        Assertions.assertThat(discountService).isInstanceOf(DiscountService.class);
        Assertions.assertThat(discountPrice).isEqualTo(1000);
        Assertions.assertThat(discountPrice2).isEqualTo(2000);

    }

    static class DiscountService {

       private final Map<String, DiscountPolicy> map;
       private final List<DiscountPolicy> list;

     @Autowired
       public DiscountService(Map<String, DiscountPolicy> map, List<DiscountPolicy> list){
           this.map = map;
           this.list = list;
           System.out.println("map = " + map);

       }


        public int discount(Member member, int price, String discountCode) {
         DiscountPolicy discountPolicy = map.get(discountCode);
         return discountPolicy.discount(member, price);
        }
    }
}
