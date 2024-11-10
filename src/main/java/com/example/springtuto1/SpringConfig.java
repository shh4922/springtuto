package com.example.springtuto1;

import com.example.springtuto1.aop.TimeTraceAop;
import com.example.springtuto1.reposiroty.JPAMemberRepository;
import com.example.springtuto1.reposiroty.MemberRepository;
import com.example.springtuto1.reposiroty.MemortMemberRepository;
import com.example.springtuto1.reposiroty.SpringDataJpaMemberRepository;
import com.example.springtuto1.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }
//
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }
//    @Bean
//    public MemberRepository memberRepository() {
//        return new JPAMemberRepository(em);
//
//    }
}
