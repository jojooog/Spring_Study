package hello.spring_core.singleton;

import hello.spring_core.AppConfig;
import hello.spring_core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureDiContainer(){
        AppConfig appConfig = new AppConfig();

        //조회
        MemberService memberService1 = appConfig.memberService();

        MemberService memberService2 = appConfig.memberService();

        //참조값 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴 적용한 객체 사용")
    void SingletonServiceTest(){
        SingletonService st1 = SingletonService.getInstance();
        SingletonService st2 = SingletonService.getInstance();

        System.out.println("st1 = " + st1);
        System.out.println("st2 = " + st2);

        //same 은 ==과 동일
        Assertions.assertThat(st1).isSameAs(st2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer(){
//        AppConfig appConfig = new AppConfig();

        AnnotationConfigApplicationContext ac =  new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        Assertions.assertThat(memberService1).isSameAs(memberService2);

    }


}
