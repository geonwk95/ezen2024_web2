import axios from "axios";
import { useRef } from "react";

export default function Write(props){
    
    // 1. 재렌더링 고정 참조 변수
    const boardWriteFormRef = useRef(); // {current : undefined}
    console.log( boardWriteFormRef );
        
    const onWirte = (  ) => {
        console.log( boardWriteFormRef );   console.log( boardWriteFormRef.current );
                
        axios.post("/board/post.do" , boardWriteFormRef.current ) // axios contentType : multipart/Form
            .then( response => { console.log( response );
                if( response.data ){
                    alert("글쓰기 성공");
                    window.location.href = "/board";
                }else{
                    alert("글쓰기 실패")
                }
            })
            .catch( e => { console.log(e); })
    }

    return(<>
        <form ref={ boardWriteFormRef }>
            내용 : <input name="bcontent" type="text"   />
            <input type="file" name="uploadList" multiple accept="image/*"/>
            <button type="button" onClick={onWirte}>등록</button>
        </form>
    </>)
}