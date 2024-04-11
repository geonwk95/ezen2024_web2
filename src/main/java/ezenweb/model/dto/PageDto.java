package ezenweb.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
@ToString
public class PageDto {

    private int page;   // 현제 페이지
    private int count;  // 총 페이지수
    private List<Object> data;  // 본문 내용들

}
