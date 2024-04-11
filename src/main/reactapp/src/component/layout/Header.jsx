import { useContext, useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";
import { LoginInfoContext } from "../Index";

export default function Header( props ){

    const { loginInfo , setLoginInfo } = useContext( LoginInfoContext )
    
    // 컴포넌트 생성시 axios 실행해서 로그인 회원정보 호출
    // 1. 컴포넌트가 실행될때 1번 axios 요청 보내서 회원정보 가져온다.
    useEffect( ( ) => {
        axios.get("/member/login/info/get.do")
            .then( (r) => { console.log(r); 
                setLoginInfo( r.data ); // 현재 로그인정보를 state 에 담는다
            })
            .catch( (e) => { console.log(e); })
    } , [] );

    const logout = () =>{
        axios.get("/member/logout/get.do")
        .then( (r) => { console.log(r); 
            setLoginInfo(""); 
        })
        .catch( (e) => { console.log(e); })
    }

    return(<>
        <div>
            { loginInfo && <>
            <span> { loginInfo.memail } 님 </span>
            <button onClick={logout}> 로그아웃 </button></>
            }
            <ul>
                <li> <Link to="/" > 홈 </Link> </li>
                <li> <Link to="/member/signup" > 회원가입 </Link> </li>
                <li> <Link to="/member/login" > 로그인 </Link> </li>    
                <li> <a href="/board/write" > 글쓰기 </a> </li>    
                <li> <a href="/board" > 전체글보기 </a> </li>            
                <li> <a href="/chat" > 채팅방 </a> </li>                
            </ul>
        </div>
    </>)
}