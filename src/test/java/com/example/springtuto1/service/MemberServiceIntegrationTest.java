package com.example.springtuto1.service;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.reposiroty.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

// 스프링 컨테이너와 테스트를 같이함
// 컨테이너에 올린 애를 사용할수있다는 뜻
@SpringBootTest

// 테스트시작전에 트랜잭션을 시작하고 완료후 롤백.
// 테스트 DB에 데이터가 남지않아서 영향을 주지않음.
@Transactional
public class MemberServiceIntegrationTest {

    @Autowired MemberService service ;
    @Autowired MemberRepository repository ;

    @Test
    @Commit
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long memeberId = service.join(member);

        // then
        Member findMember = service.fineOne(memeberId).get();
        org.assertj.core.api.Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        service.join(member1);
        IllegalStateException e = Assertions.assertThrows(IllegalStateException.class, () -> {
            service.join(member2);
        });

        //then
        org.assertj.core.api.Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
    }
}
