package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service

public class MemberService {

    @Autowired
    MemberEntityRepository memberEntityRepository;
    // 1. 회원가입
    public boolean doSignUpPost( MemberDto memberDto ){
        System.out.println("MemberService.doSignUpPost");
        System.out.println("memberDto = " + memberDto);

        // 1. 엔티티 생성
        // 2. 리포지토리 통한 엔티티를 저장한다
        MemberEntity savedEntity =
                memberEntityRepository.save(memberDto.toEntity()); // insert
        // 3. 엔티티 생성이 되었는지 아닌지 확인 ( PK )
        if( savedEntity.getMno() > 0 ) return true;
        return false;
    }

    // * 로그인 했다는 증거/기록
    @Autowired
    private HttpServletRequest request;
    // 2. 로그인( 세션 저장 )
    public boolean doLoginPost( MemberDto memberDto ){
        System.out.println("MemberService.doLoginPost");
        System.out.println("memberDto = " + memberDto);

        // 1. 리포지토리를 통한 모든 회원엔티티 호출
        List< MemberEntity > memberEntityList
                = memberEntityRepository.findAll();
        // 2. dto 와 동일한 아이디/패스워드 를 찾는다
        boolean findState = false;
        for ( int i = 0 ; i < memberEntityList.size() ; i++ ){
            MemberEntity m = memberEntityList.get(i);
            // 3. 만약에 아이디가 동일하면 ( 엔티티와 dto )
            if( m.getMemail().equals( memberDto.getMemail()) ){
                // 4. 만약에 비밀번호가 동일하면 ( 엔티티와 dto )
                if ( m.getMpassword().equals( memberDto.getMpassword())){
                    // 5. 세션 저장
                    request.getSession().setAttribute( "loginInfo" , memberDto );
                    return true;
                }
            }
        }
        return false;
    } // f end
    // 3. 로그아웃( 세션 삭제 )
    public boolean doLogoutGet(){
        request.getSession().setAttribute("loginInfo" , null );
        return true;
    }
    // 4. 현제 로그인 회원정보 호출 ( 세션 값 반환/호출 )
    public MemberDto doLoginInfo(){
        Object object = request.getSession().getAttribute("loginInfo");
        if ( object != null ){
            return (MemberDto)object; // 강제형변환
        }
        return null;
    }
}
