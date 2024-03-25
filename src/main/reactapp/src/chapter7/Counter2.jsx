import { useEffect, useState } from "react"

export default function Counter2(props){

    // 1. state 상태변수
    const [ count , setCount ] = useState(0);
    const [ count2 , setCount2 ] = useState(0);

    // 2. useEffect 이펙트함수
    // useEffect( 이펙트함수 , 의존성배열 )

        // 1. 의존성배열 생략시 : 컴포넌트가 업데이트/재호출 될 때마다 (이펙트함수) 호출
            // mount(출생) , unmount(사망) 일때만 (이펙트함수) 호출
            // 모든 렌더링에 있어서 (이펙트함수) 호출됨
    // useEffect( () => {
    //     document.title =`총 ${count}번 클릭했습니다.`
    // });

        // 2. 의존성배열 [] : 컴포넌트가 mount(출생) , unmount(사망) 일때만 (이펙트함수) 호출
    // useEffect( () => {
    //     document.title =`총 ${count}번 클릭했습니다.`
    // } , [] );

        // 3. 의존성배열 [state 변수들] : 컴포넌트가 mount(출생) , unmount(사망) , 의존성배열에 포함된 state변수
            // 의존배열에 하나라도 포함된 변수가 변경되었을때 (이펙트함수)호출 됨
        useEffect( () => {
            console.log("useEffect()");
            document.title =`총 ${count}번 클릭했습니다.`
        } , [ count ] );        
    return(<>
        <div>
            <p>총 {count}번 클릭했습니다.</p>
            <button onClick={() => setCount(count+1)}> 클릭 </button>
        </div>

        <div>
            <p>총 {count2}번 클릭했습니다.</p>
            <button onClick={() => setCount2(count2+1)}> 클릭2 </button>
        </div>
    </>)
}