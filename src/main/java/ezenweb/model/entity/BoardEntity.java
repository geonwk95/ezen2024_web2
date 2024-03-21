package ezenweb.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // 해당 클래스와 연동DB내 테이블 과 매핑/연결 ( ORM (Object Relational Mapping) )
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardEntity { // 테이블
    @Id
    private int bno; // 게시물번호
    private String btitle; // 게시물제목

}
