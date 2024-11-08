package com.example.springtuto1.service;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.reposiroty.MemortMemberRepository;

import java.util.List;
import java.util.Optional;

/**
 * 비지니스 로직
 */
public class MemberService {

    private final MemortMemberRepository memortMemberRepository;

    public MemberService(MemortMemberRepository memortMemberRepository) {
        this.memortMemberRepository = memortMemberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        validateDuplicateMember(member); // 중복 회원 검즘
        memortMemberRepository.save(member);
        return member.getId();
    }

    public void validateDuplicateMember(Member member) {
        memortMemberRepository.findByName(member.getName())
                .ifPresent(m-> {
                    throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

    public List<Member> findMembers() {
        return memortMemberRepository.fineAll();
    }

    public Optional<Member> fineOne(Long memberId) {
        return memortMemberRepository.findById(memberId);
    }
}


