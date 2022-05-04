package hello.spring_core.order;

import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.discount.RateDiscountPolicy;
import hello.spring_core.member.*;

public class OrderServiceImpl implements OrderService{


    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(findMember,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
