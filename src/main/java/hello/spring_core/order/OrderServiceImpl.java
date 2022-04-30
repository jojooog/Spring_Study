package hello.spring_core.order;

import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.discount.FixDiscountPolicy;
import hello.spring_core.member.*;

public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();


    @Override
    public Order order(Long memberId, String itemName, int itemPrice) {
        Member findMember = memberRepository.findById(memberId);

        int discountPrice = discountPolicy.discount(findMember,itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
