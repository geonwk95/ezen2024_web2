package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // ★요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    MemberService memberService;

    @PostMapping("/signup/post.do") // 1. 회원가입
    public byte doSignUpPost(@RequestBody MemberDto memberDto ){
        System.out.println("axios ~ MemberController.doSignUpPost");
        System.out.println("memberDto = " + memberDto);

        return memberService.doSignUpPost( memberDto );
    }


// ================ 스프링 시큐리티로 인한 로그인/로그아웃 사용 안함 ========== //
//    @PostMapping("/login/post.do") // 2. 로그인
//    public boolean doLoginPost( MemberDto memberDto ){
//        System.out.println("MemberController.doLoginPost");
//        System.out.println("memberDto = " + memberDto);
//
//        return memberService.doLoginPost( memberDto );
//    }
//
//    @GetMapping("/logout/get.do") // 3. 로그아웃
//    public boolean doLogoutGet(){
//
//        return memberService.doLogoutGet();
//    }
// ====================================================================== //
    @GetMapping("/login/info/get.do") // 4. 내정보
    public MemberDto doLoginInfo(){
        System.out.println("MemberController.doLoginInfo");
        return memberService.doLoginInfo();
    }

    @GetMapping("/find/idcheck/get.do") // 5. 아이디 유효성검사
    public byte doFindIdCheck( MemberDto memberDto ){

        return memberService.doFindIdCheck( memberDto );
    }
    @GetMapping("/find/myboard/get.do")
    public List<Map<Object,Object>> findByMyBoardList(){
        return memberService.findByMyBoardList();
    }
}
