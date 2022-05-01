package hello.spring_core.order;

import hello.spring_core.AppConfig;
import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        OrderService orderService = appConfig.orderService();
//        MemberService memberService = appConfig.memberService();

//        MemberService memberService = new MemberServiceImpl();
//        OrderService orderService = new OrderServiceImpl();

        //스프링 컨테이너
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);


        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId,"notebook",10000);
        System.out.println("order = " + order);
    }
}
