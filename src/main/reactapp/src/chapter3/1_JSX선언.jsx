
import React from "react";

const user = { name : '유재석' , age : 18 } // JS 객체(전역)

function formatName( user ){ // JS 함수
    return user.name + ' ' + user.age;
} // f end

function JSX선언( props ){

    let name = '김건우'; // JS 변수

    return (
        <div>
            안녕하세요 리액트 공간 <br/> 
            저는 { name } 입니다.  <br/>
            { formatName( user ) }
        </div>
    ); // JSX 형식
}
export default JSX선언;