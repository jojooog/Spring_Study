package hello.spring_core.member;

import hello.spring_core.AppConfig;
import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        //스프링 컨테이너 생성하기
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService",MemberService.class);


        //member 객체 생성
        Member member = new Member(1L, "memberA", Grade.VIP);

        //회원가입
        memberService.join(member);

        //아이디로 member 찾기
        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());


    }
}
