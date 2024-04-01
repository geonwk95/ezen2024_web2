package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "reply")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReplyEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY ) // MySQL auto_increment
    private int rno;
    private String rcontent; // varchar(255)

    // FK 필드
    @JoinColumn( name = "bno_fk" ) // fk 필드명
    @ManyToOne
    private BoardEntity boardEntity;

    @JoinColumn( name = "mno_fk" ) // fk 필드명
    @ManyToOne
    private MemberEntity memberEntity;

}
