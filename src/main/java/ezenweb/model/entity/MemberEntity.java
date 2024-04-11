package ezenweb.model.entity;

import ezenweb.model.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "member")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberEntity extends BaseTime {
    @Id // Pk
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // MySQL auto_increment
    private int mno; // 회원 번호 pk
    @Column( length = 50 , unique = true )
    private String memail; // varchar()
    @Column( length = 255 )
    private String mpassword;
    @Column( length = 20 , nullable = false)
    private String mname;
    @Column( name = "mrol" )
//    @ColumnDefault("'user'") // 문자 ''
    @Builder.Default
    private String mrol = "user";

    @OneToMany( mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용
    // 양방향 : 게시물 FK @OneToMany( mappedBy = "해당테이블 FK 필드명")
    private List<BoardEntity> boardEntityList = new ArrayList<>();
    @OneToMany( mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용
    // 양방향 : 댓글 FK
    private List<ReplyEntity> replyEntities = new ArrayList<>();

    // - 엔티티를 dto로 변환하는 메소드
    public MemberDto toDto(){ // R
        return MemberDto.builder()
                .mno( this.mno )
                .mname( this.mname )
                .memail( this.memail )
                .mrol( this.mrol )
                .build();
    }

//    @PrePersist
//    public void prePersist(){
//        if (this.mrol == null){
//            this.mrol = "user";
//        }
//    }
}
