import { useState } from "react";

export default function Counter( props ){
    // 1. 일반변수
    let countValue = 0;
    // 2. 함수 // 변수 값 1증가
    function 클릭함수(){
        countValue++;
        console.log("함수 내부 " + countValue)
    }
    console.log("컴포넌트 내부(mount:컴포넌트 생성될때)" + countValue)

// 3. 상태관리변수 ★★★ : state 값의 변화( set~~ )가 있으면 해당 컴포넌트 재호출/렌더링
    // useState('초기값')
        // 반환 : 배열
        // [0] : state 초기값 또는 값;
        // [1] : state의 set 함수 ( state (참조주소)값을 변경할때 사용되는 함수 )
        //      - 참조주소값이 변경될때
        //      - 배열/객체 사용시에는 복사 필요 
    let 상태관리변수 = useState("훅이란 무엇인가?")
    console.log(상태관리변수);
    console.log(상태관리변수[0]);
    console.log(상태관리변수[1]);

    const [ count , setCount ] = useState(0);
    
    const [ inputValue1 , setInputValue1 ] = useState('');

    function inputValue1Update( e ){
        console.log(e)  // 이벤트 발생한 결과물 객체
        console.log(e.target)   // 이벤트를 발생시킨 주체자(마크업) == this
        console.log(e.target.value) // 이벤트를 발생시킨 주체자의 value 속성
        setInputValue1( e.target.value ); // state 변경함수의 set함수를 호출해서 값 대입 == 재렌더링 == ★★★ 컴포넌트 다시호출 

    }

    return (           
        <><div>
            <p>p.215 일반 변수 : 총 {countValue}번 클릭했습니다.</p>
            <button onClick={() => countValue++} type="button">
                클릭
            </button>
        </div>

        <div>
            <p>p.216 state 변수 : 총 {count}번 클릭했습니다.</p>
            <button onClick={() => setCount(count+1) } type="button">
                클릭
            </button>
        </div>
        
        <div>
            <input type="text" /> <br/>
            <input type="text" value={ inputValue1 } /> <br/>
            <input type="text" value={ inputValue1 } onChange={ inputValue1Update } />
        </div>
        
        </>

    )
}