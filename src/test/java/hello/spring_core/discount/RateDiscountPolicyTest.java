package hello.spring_core.discount;

import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("vip는 10퍼센트 할인")
    void vip_discount(){
        Member member = new Member(1L, "testMember", Grade.VIP);

        int discountAmount = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discountAmount).isEqualTo(1000);

    }

    @Test
    @DisplayName("vip가 아니면 할인이 적용되지 않는다")
    void basic_discount(){
        Member member = new Member(1L, "testMember", Grade.BASIC);

        int discountAmount2 = discountPolicy.discount(member, 10000);

        Assertions.assertThat(discountAmount2).isEqualTo(0);
    }


}