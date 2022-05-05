package hello.spring_core.singleton;

import hello.spring_core.AppConfig;
import hello.spring_core.member.MemberRepository;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;
import hello.spring_core.member.MemoryMemberRepository;
import hello.spring_core.order.OrderService;
import hello.spring_core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl  orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository repository1 = memberService.getMemberRepository();
        MemberRepository repository2 = orderService.getMemberRepository();


        System.out.println("memberService -> repository1 = " + repository1);
        System.out.println("orderService -> repository2 = " + repository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(repository1).isSameAs(repository2);
    }
}
