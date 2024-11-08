package com.example.springtuto1.service;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.reposiroty.MemberRepository;
import com.example.springtuto1.reposiroty.MemortMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;



class MemberServiceTest {

    MemortMemberRepository repository ;
    MemberService service ;

    @BeforeEach
    public void beforeEach() {
        repository = new MemortMemberRepository();
        service = new MemberService(repository);
    }

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
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

    @Test
    void validateDuplicateMember() {
    }

    @Test
    void findMembers() {
    }

    @Test
    void fineOne() {
    }
}