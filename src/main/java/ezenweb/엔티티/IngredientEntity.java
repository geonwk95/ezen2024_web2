package ezenweb.엔티티;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table( name = "ingredient")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class IngredientEntity {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private int ino;
    private String iname;
    private String iexdate; // 유통기한
    private String idate; // 등록일자
    private String iunit; // 단위
    private int icount; // 개수

}
