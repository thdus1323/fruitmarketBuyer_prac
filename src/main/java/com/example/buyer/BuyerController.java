package com.example.buyer;

import jakarta.persistence.Query;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class BuyerController {
    private final BuyerService buyerService;
    private final HttpSession session;

    //회원가입
    @PostMapping("/join")
    public String join(BuyerRequest.JoinDTO reqDTO) {
        buyerService.joinByNameAndPwAndEmail(reqDTO);
        System.out.println("회원가입 : " + reqDTO);
        return "redirect:login-form"; // 로그인 폼 구현 후 수정
    }

    @GetMapping({"/join-form"})
    public String joinForm() {
        return "buyer/join-form";
    }

    //로그인
    @PostMapping("login")
    public String login(BuyerRequest.LoginDTO reqDTO) {
        Buyer sessionBuyer = buyerService.loginByNameAndPw(reqDTO);
        session.setAttribute("sessionBuyer", sessionBuyer);
        return "redirect:/";
    }

    @GetMapping("login-form")
    public String loginForm() {
        return "buyer/login-form";
    }

    @GetMapping("/logout")
    public String logout()
    {session.invalidate();
    return "redirect:/";}
}
