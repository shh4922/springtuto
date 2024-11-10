package com.example.springtuto1.reposiroty;

import com.example.springtuto1.domain.Member;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class JPAMemberRepository implements MemberRepository{

    // 스프링부트 실행시, EntityManager 스프링이 알아서 EntityManager라는곳에 만들어줌.
    // 그래서 그냥 인젝션 해서 사용하면 됨.
    private  final EntityManager em;

    public JPAMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> fineAll() {
        return em.createQuery("select m from Member m",Member.class).getResultList();
    }
}
