package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto extends BaseTimeDto{
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto 엔티티로 변환하는 메소드
    public MemberEntity toEntity(){ // C
        return MemberEntity.builder()
                .mpassword( new BCryptPasswordEncoder().encode( this.mpassword))
                // new BCryptPasswordEncoder().encode( 암호화 할 데이터 )
                /*  암호화란 :
                        암호 : 정보를 이해할 수 없도록 = 사람이 이해할 수 없도록
                            - 이해할수 없도록 자기만의 방법으로 변경
                            - 스프링 시큐리티가 제외하는 방법 : bcrypt 암호화 제공
                */
                .mname( this.mname )
                .memail( this.memail )
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }
}
