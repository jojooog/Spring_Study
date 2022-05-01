package hello.spring_core.member;

import hello.spring_core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join(){

        //member 객체 생성
        Member member = new Member(1L, "member test", Grade.VIP);

        memberService.join(member); //회원 가입

        Member findMember = memberService.findMember(member.getId());
        //아이디로 찾아오기

        Assertions.assertThat(member).isEqualTo(findMember);
        //가입한 객체와 아이디로 찾아온 객체가
        //일치하는지 확인
    }
}
