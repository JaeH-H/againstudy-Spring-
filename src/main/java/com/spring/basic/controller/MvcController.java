package com.spring.basic.controller;


import com.spring.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/members")
public class MvcController {

    //간이 데이터베이스 만들기
    private final List<Member> memberList = new ArrayList<>();


    /**
     * 전체 회원 조회 API
     * View가 반환해서 그려준다.
     * resources -> templates(여기서는 타임리프를 씀)
     */
    @GetMapping
    public String getMemberListView(Model model) {
        model.addAttribute("members",memberList);//타임리프 문법에서 반복문
        return "member/member-list"; //회원 목록 페이지
    }

    @GetMapping("/new")
    public String createMemberView() {
        return "member/member-create"; // 회원 가입 페이지
    }

    //회원 생성 API
    @PostMapping
    public String createMemberAPI(@ModelAttribute Member member) {
        memberList.add(member);
//        return "member/member-list";
        return  "redirect:/members"; // redirect를 쓰면 데이터를 다시 조회
    }
}
