package ezenweb.controller;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // ★요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/signup/post.do") // 1. 회원가입
    public boolean doSignUpPost(@RequestBody MemberDto memberDto ){
        System.out.println("axios ~ MemberController.doSignUpPost");
        System.out.println("memberDto = " + memberDto);

        return memberService.doSignUpPost( memberDto );
    }

    @PostMapping("/login/post.do") // 2. 로그인
    public boolean doLoginPost( MemberDto memberDto ){
        System.out.println("MemberController.doLoginPost");
        System.out.println("memberDto = " + memberDto);

        return memberService.doLoginPost( memberDto );
    }

    @GetMapping("/logout/get.do") // 3. 로그아웃
    public boolean doLogoutGet(){

        return memberService.doLogoutGet();
    }

    @GetMapping("/login/info/get.do") // 4. 내정보
    public MemberDto doLoginInfo(){
        System.out.println("MemberController.doLoginInfo");
        return memberService.doLoginInfo();
    }
}
