import { useState } from "react";

export default function UseStateList( props ){
    // 2. point 상태관리변수
        // 1. input 입력된 값을 저장하는 상태관리변수
    let [ pointInput , setPointInput ] = useState(''); // 원시타입( 10번지 )
        // 2. input 입력된 값들을 저장하는 리스트 상태관리변수
    let [ pointList , setPointList] = useState( [] ); // 배열( 32번지( ) )



    // 1. 등록 버튼 클릭시
    function 등록( ){
        console.log('등록함수실행')              
        // ======= 리액트 방식 ======== //
        pointList.push(pointInput); // 배열( 32번지 [ 33번지 , 34번지 ] )
        setPointList([...pointList]); // 배열( 32번지 ) 해결방안 : 새로운 배열을 만든다 
        //
    } // f e

    // 3.
    function 입력변경( e ){
        setPointInput( e.target.value ); // 원시타입 ( 20번지 ) 재렌더링; 
        console.log( e.target.value );
    }


    return (<>
        <div>
            <div>
                <input type="text" value={pointInput} onChange={입력변경} /> <br/>
                <button onClick={등록} type="button">등록</button>
            </div>
            <div>
                {
                    pointList.map( (point) => {
                        return ( <div> {point} </div> )
                     })                
                }                
            </div>
        </div>    
        </>
    )
}