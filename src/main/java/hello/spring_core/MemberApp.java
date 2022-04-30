package hello.spring_core;

import hello.spring_core.member.Grade;
import hello.spring_core.member.Member;
import hello.spring_core.member.MemberService;
import hello.spring_core.member.MemberServiceImpl;

public class MemberApp {

    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();

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
