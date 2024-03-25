package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "test4")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Test4Entity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int t4no;
    private String t4content; // 작성내용
    private String t4date; // 작성일
    private byte t4state; // 보고서 반품 체크
    private int t4normal; // 정상 제품 개수
    private int t4error; // 불량 제품 개수

}
