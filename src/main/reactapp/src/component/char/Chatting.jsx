import React, { useContext, useEffect, useRef, useState } from "react";
import { LoginInfoContext } from "../Index";
import styles from "./Chatting.css"
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';

export default function Chatting(props){

    // 1. 해당 컴포넌트가 렌더링 될때 소켓은 재랜더링 방지 useRef
        // useRef(초기값) :     { current : 값 }
        // - 컴포넌트가 렌더링시 참조값을 고정 할수 있다
    let clientSocket = useRef( null );
    // 2.
    if( !clientSocket.current ){

    // ============== (클라이언트) 웹소켓 구현 =============== //
        // 1. new WebSocket( 서버소켓url ); // 비동기 // 서버소켓에게 접속 요청
    clientSocket.current = new WebSocket("ws://192.168.17.54:8080/chat");
        // 확인
    console.log( clientSocket.current );
    // onclose // onerror // onmessage // onopen : WebSocket 객체내 포함된 메소드들
        // 2. 각 메소드 정의
            // - 1. 클라이언트 소켓이 close 되었을때 콜백함수 정의
            clientSocket.current.onclose = (e) => { console.log(e); console.log("서버소켓 닫힘");};
            // - 2. 클라이언트 소켓이 error 발생했을때 콜백함수 정의 (* error 이미 발생했고 다음 행동 정의 )
            clientSocket.current.onerror = (e) => { console.log(e); console.log("서버소켓 에러");};
            // - 3. 클라이언트 소켓이 message 받았을때 콜백함수 정의
            clientSocket.current.onmessage = (e) => { console.log(e); console.log(e.data);                 
                msgList.push( JSON.parse(e.data) );
                setMsgList( [...msgList] );
            };
            // - 4. 클라이언트 소켓이 open 발생했을때 콜백함수 정의
            clientSocket.current.onopen = (e) => { console.log(e); console.log("서버소켓연결성공");};
    } // if e
           
    
    // ===================================================== //

    const { loginInfo } = useContext( LoginInfoContext );

    const datetime = new Date()    
    let hours = datetime.getHours(); // 시
    const minutes = datetime.getMinutes(); // 분    
    let period = "";
    if (hours >= 0 && hours <= 12) {
        period = "오전";
        
    } else{
        period = "오후";
        hours = (hours - 12);
    }

    
    
    const onSend = ()=>{

        let info = {
            msg : msgInput , // 작성된 내용
            fromMname : loginInfo.mname , // 현재 로그인 한 작성자             
            minutes : minutes ,
            hours : hours ,
            period : period ,
            type : "msg" 
        }


        console.log( info );
        // 3. 연결된 서버소켓에게 메세지 보내기
            // send() : 데이터 타입 : 텍스트
                // JSON -> 문자 : JSON.stringify( js객체 )
                // 문자 <- JSON : JSON.parse( 문자열 )
        clientSocket.current.send( JSON.stringify( info )); // 입력받은 데이터를 서버소켓 에게 보내기        

        setMsgInput("");
    }

    // - 채팅 내용 입력창
    const [ msgInput , setMsgInput ] = useState("");
    // - 채팅 창 의 내용물 들 
    const [ msgList , setMsgList ] = useState([]);
    // - 채팅 내용 입력창에서 엔터를 했을때 / ctrl+엔터 했을때
    const activeEnter = (e) => {
        console.log(e);
        
        // 1. ctrl+엔터 했을때
        if( e.keyCode == 13 && e.ctrlKey ){
            setMsgInput( msgInput + `\n` ); return;
        }
        // 2. 엔터 했을때
        if( e.keyCode == 13 ){ // 엔터 눌렀을때
            onSend(); return;            
        }
    }
    // - 스크롤 자동으로 최하단으로 내리기
    useEffect( () => {
        // 1.
        let chatcont = document.querySelector('.chatcont');
        console.log( chatcont.scroll );
        console.log( chatcont.scrollTop );  // 현재 스크롤의 상단 위치
        console.log( chatcont.scrollHeight );   // 스크롤 전체 높이길이 ( 본문이 길어졌기 때문에 )
        // 2.
        chatcont.scrollTop = chatcont.scrollHeight; // 상단 위치를 최하단 위치로 변경
        setMsgInput("");
    }, [msgList])

    // - 드랍다운 MUI
        const [anchorEl, setAnchorEl] = React.useState(null);
        const open = Boolean(anchorEl);
        const handleClick = (event) => {
            setAnchorEl(event.currentTarget);
        };
        const handleClose = () => {
            setAnchorEl(null);
        };

        console.log( Array(43) ); // 길이만큼의 배열선언
        console.log( Array(43).fill(2) );

    // - 이모티콘 이미지를 클릭했을때 전송
        const onEmoSend = ( emo ) => { 
            let info = {
                msg : emo ,
                fromMname : loginInfo.mname ,
                minutes : minutes ,
                hours : hours ,
                period : period ,
                type : "emo"
            }
            clientSocket.current.send( JSON.stringify(info) );
            // - 드롭다운 닫기
            handleClose();
        }

    // - msg타입에 따른 HTML 반환 함수
        const typeHTML = (m) => {
            if(m.type == "msg"){
                return <div className="content"> { m.msg} </div>
            }else if(m.type == "emo" ){
                return <img src={"/emo/"+m.msg}/>
            }

        }

    return (<>
        <h3> 채팅방 </h3>
        <div className="chatbox">
            <div className="chatcont">

                {
                    msgList.map( (m)=>{
                        return (<>
                            {
                                loginInfo.mname == m.fromMname ? 
                                    (
                                        <div className="rcont">
                                            <div className="subcont">
                                                <div className="date"> {m.period+m.hours+":"}{m.minutes < 10 ? "0"+m.minutes : m.minutes} </div>
                                                { typeHTML(m) }
                                            </div>
                                        </div>
                                    ) :
                                    <div className="lcont">
                                        <img className="pimg" src={'/uploadimg/default.jpg'} />
                                        <div className="tocont">
                                            <div className="name">{ m.fromMname } </div>
                                            <div className="subcont">
                                                { typeHTML(m) }
                                                <div className="date"> {m.period+m.hours+":"}{m.minutes < 10 ? "0"+m.minutes : m.minutes}</div>
                                            </div>
                                        </div>
                                    </div>
                            }       
                        </>);
                    })
                }
            </div>
            <div className="chatbottom">
                <textarea className="scrollhidden" value={msgInput} onChange= { (e)=>{ setMsgInput( e.target.value) }} 
                onKeyDown = { activeEnter }>                                 
                </textarea>
                <button type="button" onClick={ onSend }> 전송 </button>
            </div>

            <div>
            <Button
                id="basic-button"
                aria-controls={open ? 'basic-menu' : undefined}
                aria-haspopup="true"
                aria-expanded={open ? 'true' : undefined}
                onClick={handleClick}
            >
                이모티콘
            </Button>
                <Menu
                    id="basic-menu"
                    anchorEl={anchorEl}
                    open={open}
                    onClose={handleClose}
                    MenuListProps={{
                    'aria-labelledby': 'basic-button',
                    }}
                >
                    <div style={ { height : 200 , overflowY : 'scroll' }}>
                    {
                        Array(43).fill().map( ( v , i )  => {
                            return (<>                            
                            <img src={`/emo/emo${i+1}.gif`} onClick={ () => onEmoSend(`emo${i+1}.gif`)}/>                            
                            { (i+1) % 5 == 0 && <br/> }
                            </>)                            
                        })
                    }
                    </div>
                 </Menu>
            </div>
        </div>
    </>)
}