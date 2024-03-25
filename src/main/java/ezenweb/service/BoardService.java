package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    // 1. C
    @Transactional
    public boolean postBoard(){
        // ============= 테스트 ===============
        // 1. 회원가입
            // 1-1 엔티티 생성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("qwe@qwe.com")
                .mpassword("1234")
                .mname("유재석")
                .build();
            // 1-2 해당 엔티티를 DB에 저장할수 있도록 조작
        MemberEntity saveMemberEntity = memberEntityRepository.save( memberEntity );

        // 2. 회원가입된 회원으로 글쓰기
            // 2-1 엔티티 객체 생성
        BoardEntity boardEntity = BoardEntity.builder()
                .bcontent("게시물글입니다.")
                .build();
            // 2-2 글쓴이(FK 대입) ★★★★★★★★★★★★★
        boardEntity.setMemberEntity( saveMemberEntity ); // 회원 에티티 대입시 DB에서는 PK만 저장
            // 2-3 해당 엔티티를 DB에 저장할수 있도록 조작
        BoardEntity saveBoardEntity = boardEntityRepository.save( boardEntity );

        // 3. 해당 글에 댓글 작성
            // 3-1 엔티티 객체 생성
        ReplyEntity replyEntity = ReplyEntity.builder()
                .rcontent("댓글입니다.")
                .build();
            // 3-2 글쓴이,게시물번호(FK 대입) ★★★★★★★★★★★★★
        replyEntity.setMemberEntity( saveMemberEntity );
        replyEntity.setBoardEntity( saveBoardEntity );
            // 3-3 해당 엔티티를 DB에 저장할수 있도록 조작
        replyEntityRepository.save( replyEntity );

        return false;
    }
    // 2. R
    @Transactional
    public List<Objects> getBoard(){
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        return null;
    }
    // 3. U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById( 1 ).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }
    // 4. D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById( 1 );
        return false;
    }
}
