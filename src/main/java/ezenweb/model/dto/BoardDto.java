package ezenweb.model.dto;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardDto extends BaseTimeDto{
    private int bno;

    private String bcontent;

    private int bview;

    private int mno_fk; // 회원번호

    private String memail; // 회원 이메일

    @ToString.Exclude
    @Builder.Default
    // 1. 출력용 게시물 이미지 필드 ( 왜?? 파일이름만 여러개 출력하면 되니까   SPRING --> JS )
    private List<String> photonameList = new ArrayList<>();

    @ToString.Exclude
    @Builder.Default
    // 2. 등록용 게시물 이미지 필드( 왜??  JS --- multipart/From(바이트) -> SPRING )
    private List<MultipartFile> uploadList = new ArrayList<>();


    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bno(this.bno)
                .bcontent(this.bcontent)
                .build();
    }
}
