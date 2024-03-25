package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table( name = "test2")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Test2Entity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int t2no;
    private String t2content; // 작성내용
    private String t2date; // 작성일
    private byte t2state; // 보고서 반품 체크


    @Builder.Default
    private Map<String , Integer> t2input = new HashMap<>(); // 투입량
}
