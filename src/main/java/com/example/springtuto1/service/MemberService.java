package com.example.springtuto1.service;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.reposiroty.MemberRepository;
import com.example.springtuto1.reposiroty.MemortMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 비지니스 로직
 */
//JPA 는 데이터 변경이 Transactional 안에서 실행도어야함. 그래서 작성해줘야함.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검즘
        memberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers() {
        return memberRepository.fineAll();
    }

    public Optional<Member> fineOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}


