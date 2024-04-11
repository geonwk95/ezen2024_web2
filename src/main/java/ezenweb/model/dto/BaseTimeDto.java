package ezenweb.model.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class BaseTimeDto {
    private LocalDateTime cdate; // 작성일

    private LocalDateTime udate;
}
