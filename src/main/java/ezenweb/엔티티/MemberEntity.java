package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "member")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class MemberEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int mno;
    @Column( length = 20 , nullable = false , unique = true )
    private String mid;
    @Column( length = 20 , nullable = false )
    private String mpw;
    private int mstate;
}
