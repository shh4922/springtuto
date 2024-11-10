package com.example.springtuto1.controller;

import com.example.springtuto1.domain.Member;
import com.example.springtuto1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // 다른 컨트롤러에서도 memberService를 만들고 사용해야할때가 분명히 있음
    // 그때마다 new MemberService를 만드는것은 너무 비효율적임
    private final MemberService memberService;

    // 그래서 스프링컨테이너에 등록을 해두면 전역적으로 사용할수있음. 약간 싱글톤인듯

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm memberForm) {
        Member member = new Member();
        member.setName(memberForm.getName());

        memberService.join(member);
        System.out.println("등록");
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
