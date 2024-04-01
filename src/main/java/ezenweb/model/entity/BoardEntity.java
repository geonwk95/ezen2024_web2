package ezenweb.model.entity;

import ezenweb.model.dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity // 해당 클래스와 연동DB내 테이블 과 매핑/연결 ( ORM (Object Relational Mapping) )
@Table( name = "board" )
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardEntity extends BaseTime{ // 테이블
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // MySQL auto_increment
    private int bno; // 게시물번호

    @Column( columnDefinition = "longtext" )
    private String bcontent;
    @Column
    @ColumnDefault("0")
    private int bview;

    // FK 필드
    @JoinColumn( name = "mno_fk" ) // fk필드명
    @ManyToOne // 해당 필드 참조
    private MemberEntity memberEntity;

    @OneToMany( mappedBy = "boardEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용시 해당 필드의 초기값을 빌더 초기값으로 사용
    // 양방향 : 댓글 FK
    private List<ReplyEntity> replyEntities = new ArrayList<>();

    // - 게시물 출력
    public BoardDto toDto(){
        return BoardDto.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .bview(this.bview)
                .mno_fk(this.memberEntity.getMno())
                .memail(this.memberEntity.getMemail())
                .cdate(this.getCdate())
                .udate(this.getUdate())
                .build();
    }
}

/*
@Column(columnDefinition = "longtext")
    private String btitle2;
    @Column()
    private boolean 필드0;
    private byte 필드1;
    private short 필드2;
    private long 필드3;
    private char 필드4;
    private double 필드5;
    private float 필드6;
    private Date 필드7;
    private LocalDateTime 필드8;

 */