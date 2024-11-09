package com.example.springtuto1;

import com.example.springtuto1.reposiroty.MemberRepository;
import com.example.springtuto1.reposiroty.MemortMemberRepository;
import com.example.springtuto1.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemortMemberRepository();
    }
}
