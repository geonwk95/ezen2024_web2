package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardPhotoEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.BoardPhotoEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;
    @Autowired
    BoardPhotoEntityRepository boardPhotoEntityRepository;

    // 1. C
    @Transactional
    public boolean postBoard(BoardDto boardDto){// ============= 테스트 ===============
        MemberDto loginDto = memberService.doLoginInfo();
        if ( loginDto == null ) return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional< MemberEntity > optionalMemberEntity = memberEntityRepository.findById( loginDto.getMno() );
        // 2. 찾은 엔티티가 존재하지 않으면
        if( !optionalMemberEntity.isPresent() ) return false;
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

        // 글쓰기
        BoardEntity saveBoard = boardEntityRepository.save( boardDto.toEntity() );
        System.out.println("saveBoard = " + saveBoard);


        List<String> filenameList = new ArrayList<>();
            // 1. 하나씩 업로드 서비스에 업로드 요청
        boardDto.getUploadList().forEach( (e) -> {
                // 2. 하나씩 업로드 된 파일명 반환 받기
            String filename = fileService.fileUpload(e);
             filenameList.add(filename);
        });

        List<BoardPhotoEntity> photoList = new ArrayList<>();
        filenameList.forEach( (e) -> {
            // 3. 하나씩 업로드된 파일명으로 게시물파일엔티티 생성
            BoardPhotoEntity boardPhotoEntity = BoardPhotoEntity.builder()
                    .photoname(e) // 방금 위에서 업로드된 파일명을 엔티티에 대입
                    .build();
            photoList.add(boardPhotoEntity);
        });

        photoList.forEach( (e) -> {
            // 4. 엔티티 영속성 : 영속성이 필요한 이유 : DB 에 저장할려고 : 서버가 종료되도 영구적으로 저장할려고
            BoardPhotoEntity boardPhotoEntity = boardPhotoEntityRepository.save( e );
            System.out.println("boardPhotoEntity = " + boardPhotoEntity);

            if (boardPhotoEntity.getPhotoname() != null ){
                boardPhotoEntity.setBoardEntity(saveBoard);
            }
        });
            // - FK 대입
        if (saveBoard.getBno() >= 1){ // 글쓰기 성공했으면
            saveBoard.setMemberEntity( memberEntity );
            return true;
        }
        return false;
    }
    // 2. R
    @Transactional
    public PageDto getBoard(int page , int view ){
        // =============================== 1 ============================== //
        /*
        // 1. 리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        System.out.println("result = " + result);
        // 2. Entity ---> Dto 변환한다
        List<BoardDto> boardDtoList = new ArrayList<>();
            // 1. 꺼내온 entity 를 반복/순회한다
        for (int i = 0; i < result.size(); i++) {
                // 2. 하나씩 entity 꺼낸다
            BoardEntity boardEntity = result.get(i);
                // 3. 해당 엔티티를 dto로 변환한다.
            BoardDto boardDto = boardEntity.toDto();
                // 4. 변환된 dto를 리스트에 담는다
            boardDtoList.add( boardDto );
        }
        return boardDtoList;
         */
        // =============================== 1 ============================== //

        // 1. pageable 인터페이스 를 이용한 페이징처리
            // Pageable pageable = PageRequest.of( 현제페이지 , 페이지당 표시할레코드수)
                // pageable 은 page 순서 0 부터 시작하기 때문에 page가 1페이지일때 0페이지 반환하기 위해 -1
        Pageable pageable = PageRequest.of( page-1 , view );

            // 1. 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage = boardEntityRepository.findAll(pageable);

        // -- List 아닌 Page 타입일때 List 동일한 메소드 사용하고 추가 기능
            // 1. 전체 페이지수
        System.out.println("boardEntityPage.getTotalPages() = " + boardEntityPage.getTotalPages());
        int count = boardEntityPage.getTotalPages();
            // 2. 전체 게시물수
        System.out.println("boardEntityPage.getTotalElements() = " + boardEntityPage.getTotalElements());

        List<Object> data = boardEntityPage.stream().map( (boardEntity) -> {
            return boardEntity.toDto();
        }).collect(Collectors.toList());

        // 2. 페이지DTO 반환 값 구성
            // 1.
        PageDto pageDto = PageDto.builder()
                .data( data ) // 페이징 처리된 레코드들을 대입
                .page( page ) // 현재 페이지 수
                .count( count )
                .build();

        return pageDto;
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
    public boolean deleteBoard(int bno , int mno){
        MemberDto loginDto = memberService.doLoginInfo();
        if ( loginDto == null ) return false;

        // 1. 로그인된 회원 엔티티 찾기
        Optional< MemberEntity > optionalMemberEntity = memberEntityRepository.findById( loginDto.getMno() );
        // 2. 찾은 엔티티가 존재하지 않으면
        if( !optionalMemberEntity.isPresent() ) return false;
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();

        if( memberEntity.getMno() == mno ){
        boardEntityRepository.deleteById( bno );
        return true;
        }

        return false;
    }

}
