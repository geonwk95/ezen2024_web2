package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "member")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberEntity {
    @Id // Pk
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int mno; // 회원 번호 pk

    private String mid;
    @ToString.Exclude // 해당 객체 호출시 해당 필드 ToString 제외
    @OneToMany( mappedBy = "memberEntity")  // 하나가 다수에게 1 : M
    private List<BoardEntity> boardEntityList = new ArrayList<>();
}