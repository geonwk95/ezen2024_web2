package ezenweb.model.dto;

import ezenweb.model.entity.MemberEntity;
import jakarta.persistence.Column;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberDto {
    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto 엔티티로 변환하는 메소드
    public MemberEntity toEntity(){ // C
        return MemberEntity.builder()
                .mno( this.mno )
                .mpassword( this.mpassword )
                .mname( this.mname )
                .memail( this.memail )
                .mrol( this.mrol )
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }
}
