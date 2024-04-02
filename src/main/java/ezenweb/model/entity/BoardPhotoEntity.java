package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Entity // 해당 클래스와 연동DB내 테이블 과 매핑/연결 ( ORM (Object Relational Mapping) )
@Table( name = "boardphoto" )
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardPhotoEntity extends BaseTime{

    @Id
    private String photoname; // 파일명( 중복 없다 -- 유저는 다수 고 서버는 하나 ) // 식별이 가능




    // 단방향 설정
    @ManyToOne
    @JoinColumn(name = "bno_fk")
    private BoardEntity boardEntity;

}
