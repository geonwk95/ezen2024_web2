package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table( name = "test1")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Test1Entity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int t1no;
    private String t1content; // 작성내용
    private String t1date; // 작성일
    private byte t1state; // 보고서 반품 체크

    @Builder.Default
    private Map<String , Integer> t1input = new HashMap<>(); // 투입량
}
