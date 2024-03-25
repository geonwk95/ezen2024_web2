package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table( name = "test3")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Test3Entity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int t3no;
    private String t3content; // 작성내용
    private String t3date; // 작성일
    private byte t3state; // 보고서 반품 체크
    private int t3success; // 완료 벌크 수량

    @Builder.Default
    private Map<String , Integer> t3input = new HashMap<>(); // 투입량
}
