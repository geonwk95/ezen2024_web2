package ezenweb.controller;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        System.out.println("멀티파티파일 = " + boardDto);
        return boardService.postBoard(boardDto) ;
    }
    @GetMapping("/get.do")
    public PageDto getBoard(int page , int view  ){
        return boardService.getBoard( page , view );
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(@RequestParam int bno , int mno ){




        return boardService.deleteBoard(bno , mno);
    }
}
