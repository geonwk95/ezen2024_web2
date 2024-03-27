import { useState } from "react";

export default function NameForm(props){

    // 1. 
    function 제출1(){
        let nameInput = document.querySelector('#nameInput').value;
        console.log(nameInput);    
    }
    // 2. 
        // 2-1 state 변수
    const [ value , setValue ] = useState('');
        // 2-2 state 변경 함수
    const handleChange = ( e ) => { 
        setValue(e.target.value);
        e.preventDefault();
        console.log( e );
    }
        // 2-3 제출 함수
    const handleSubmit = ( e ) => { 
        console.log(value);
        // 3-3
        console.log(value2);
        // 4-3
        console.log(value3);
    }        

    // 3.
        // 3-1
    const [ value2 , setValue2 ] = useState('');
        // 3-2
    const handleChange2 = ( e ) => { 
        setValue2(e.target.value);
        e.preventDefault(); // 브라우저들의 이벤트들을 제거
        console.log ( e );
        console.log ( e.target );
        console.log ( e.target.value );
    }

    // 4.
    const [ value3 , setValue3 ] = useState('grape');

    const handleChange3 = (e) => {
        setValue3(e.target.value);
        console.log ( e );
        console.log ( e.target );
        console.log ( e.target.value );
    }

    return(<>
        <form>
            이름 : <input id="nameInput" />
            <button type="button" onClick={제출1}>제출</button>
        </form> 
        <br/>
        <form>
            이름 : <input type="text" value={value} onChange={handleChange} /> <br/>
            요청사항 : <textarea value={value2} onChange={handleChange2} ></textarea> <br/>
            과일을 선택하세요 : 
            <select value={value3} onChange={handleChange3}>
                <option value="apple">사과</option>
                <option value="banana">바나나</option>
                <option value="grape">포도</option>
                <option value="watermelon">수박</option>
            </select>
            <button type="button" onClick={ handleSubmit }>제출</button>
        </form>
    </>)
}