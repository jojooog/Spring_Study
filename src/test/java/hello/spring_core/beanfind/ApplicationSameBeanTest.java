package hello.spring_core.beanfind;

import hello.spring_core.AppConfig;
import hello.spring_core.discount.DiscountPolicy;
import hello.spring_core.member.MemberRepository;
import hello.spring_core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationSameBeanTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SameBeanConfig.class);

    @Test
    @DisplayName("같은 타입이 둘 이상 있으면 중복 오류 발생")
    void findBeanDuplicate(){
        //코드 수정
        assertThrows(NoUniqueBeanDefinitionException.class,
                () -> ac.getBean(MemberRepository.class));
    }

    @Test
    @DisplayName("같은 타입이 둘 이상있으면 빈 이름 지정하면 된다")
    void findBean(){
        MemberRepository memberRepository = ac.getBean("memberRepository1", MemberRepository.class );
        Assertions.assertThat(memberRepository).isInstanceOf(MemberRepository.class);
    }

    @Test
    @DisplayName("특정 타입 전부 조회하기")
    void findAllTypeBean(){
        Map<String, MemberRepository> BeansOfType = ac.getBeansOfType(MemberRepository.class);
        for(String key : BeansOfType.keySet()){
            System.out.println("key = " + key + " value = " + BeansOfType.get(key));

        }
        System.out.println("BeansOfType = " + BeansOfType);
        Assertions.assertThat(BeansOfType.size()).isEqualTo(2);
    }

    @Configuration
    static class SameBeanConfig {

        @Bean
        public MemberRepository memberRepository1(){
            return new MemoryMemberRepository();
        }

        @Bean
        public MemberRepository memberRepository2(){
            return new MemoryMemberRepository();
        }
    }
}
