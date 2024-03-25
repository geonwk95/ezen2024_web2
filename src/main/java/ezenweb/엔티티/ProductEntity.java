package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "bulk")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class ProductEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int pno;
    private String pname; // 제품 이름
    private String pdate; // 제품 등록일자
    // ========= 레시피가 들어갈곳 ========== //

    // ========= 레시피가 들어갈곳 ========== //
}
