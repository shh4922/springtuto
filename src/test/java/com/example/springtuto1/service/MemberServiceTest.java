package com.example.springtuto1.service;

import com.example.springtuto1.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;


class MemberServiceTest {

    MemberService service = new MemberService();


    @Test
    void 회원가입() {
        // given
        Member member = new Member();
        member.setName("hello");

        // when
        Long memeberId = service.join(member);

        // then
        Member findMember = service.fineOne(memeberId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
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
        assertThatThrownBy(IllegalStateException.class, () -> service.join(member2))
//        try {
//            service.join(member2);
//            fail("회원가입 예외가 발생해야함");
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원2입니다");
//        }


        //then

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