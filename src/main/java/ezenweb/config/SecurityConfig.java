package ezenweb.config;

import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig { // 시큐리티를 커스텀 하는 곳

    @Autowired
    private MemberService memberService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        // 1. HTTP 요청에 따른 부여된 권한/상태 확인후 PATH/자원 제한
        http.authorizeHttpRequests(
                authorizeRequest    // 매개변수 // HTTP 요청 매개변수
                ->                  // 람다식( 자바 화살표 함수 )

                authorizeRequest
                        // get HTTP : /board : 인증(로그인)된 회원
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board")).authenticated()
                        // get HTTP : /board/write : 인증(로그인)된 회원이면서 ROLE이 USER 이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).hasAnyRole("USER")
                        // get HTTP : /char : 인증(로그인)되고 ROLE이 TEAM1이거나 TEAM2 이면 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/chat")).hasAnyRole("TEAM1" , "TEAM2 ")
                        // get HTTP : 그외 PAHT(/**)는 모두 허가
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll()
        );

        // 2. 로그인 폼 커스텀 ( 기존 controller 매핑함수 주석/삭제 처리 )
        // http.formLogin( AbstractHttpConfigurer :: disable ); // 시큐리티가 제공하는 로그인 폼을 사용안함
        http.formLogin( // AXIOS , AJAX 사용시 contentType : form
                로그인관련매개변수
                        ->
                로그인관련매개변수
                        .loginPage("/member/login")                      // 로그인을 할 view url 정의
                        .loginProcessingUrl("/member/login/post.do")     // 로그인을 처리할 url 정의
                        .usernameParameter("memail")                     // 로그인에 사용할 id 변수명
                        .passwordParameter("mpassword")                  // 로그인에 사용할 password 변수명
                        .defaultSuccessUrl("/")                          // 로그인 성공하면 반환할 url
                        .failureForwardUrl("/member/login")              // 로그인 실패하면 반환될 url
                );

        // 3. 로그아웃 커스텀 ( 기존 controller 매핑함수 주석/삭제 처리 )
        http.logout(
                로그아웃관련매개변수
                ->
                로그아웃관련매개변수.logoutRequestMatcher( new AntPathRequestMatcher("/member/logout/get.do") ) // 로그아웃 처리 요청 url
                                .logoutSuccessUrl("/") // 로그아웃 겅공시 이동할 url
                                .invalidateHttpSession(true)   // 세션 초기화
        );

        // 4. csrf( post,put 요청 금지 ) 공격 방지 : 특정(인증/허가) url만 post,put 가능하도록
        http.csrf( AbstractHttpConfigurer :: disable ); // csrf 사용안함 // 개발작업시

        // 5. 로그인을 처리에 필요한 서비스를 등록
        http.userDetailsService( memberService );

        return http.build();
    }

    // 2. 시큐리티가 패스워드 검증할때 사용할 암호화 객체
    @Bean // 해당 메소드를 스프링컨테이너에 등록
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
