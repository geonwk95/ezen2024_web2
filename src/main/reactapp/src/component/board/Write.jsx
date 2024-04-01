import axios from "axios";
import { useState } from "react";

export default function Write(props){
    
    // 1. state 변수
    const [ bcontent , setBcontent ] = useState("");

    // 2. 전송 함수
    const onWirte = ( e ) => {
        console.log( bcontent );
        
        axios.post("/board/post.do" , { bcontent : bcontent })
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
        <form>
            내용 : <input type="text" value={bcontent} onChange={ (e) => { setBcontent(e.target.value); }} />
            <button type="button" onClick={onWirte}>등록</button>
        </form>
    </>)
}