
export default function Clock( props ){ // 일반함수 : 첫글자 소문자 , 컴포넌트 함수 : 첫글자 대문자 

    let name = "유재석";

    // JSX 문법이 들어가는곳
    return ( 
        <div>
            <h1> 안녕, 리액트! </h1>
            <h2> 현재 시간 : {new Date().toLocaleTimeString() } </h2>                       
        </div>
    ); 
}