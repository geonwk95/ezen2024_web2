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
public class BulkEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int bno;
    private String bdate; // 벌크 등록시간
    private int bstate; // 벌크의 상태 ex) 벌크 0 : 벌크중 , 벌크 1 : 벌크완료 , 벌크 2 : 불량/폐기


    @JoinColumn
    @ManyToOne
    private ProductEntity productEntity;
}
