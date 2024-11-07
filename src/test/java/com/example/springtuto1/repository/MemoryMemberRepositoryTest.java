package com.example.springtuto1.repository;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.reposiroty.MemortMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemortMemberRepository repository = new MemortMemberRepository();

    // 테스트는 순서보장이 안됌.
    // 그래서 데이터가 겹쳐서 생각한 테스트랑 다를수있음
    // 그래서 clear함수만들어서 afterEach 어노테이션을 해서 각테스트 끝날때마다 특정함수 수행하게해서 데이터를 초기화 해버리는거임.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("신현호");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("신현호");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("신현호2");
        repository.save(member2);

        Member result = repository.findByName("신현호2").get();
        assertThat(result).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member2 = new Member();
        member.setName("spring2");
        repository.save(member2);

        Member member3 = new Member();
        member.setName("spring3");
        repository.save(member3);

        List<Member> result = repository.fineAll();
        assertThat(result.size()).isEqualTo(3);
    }
}
