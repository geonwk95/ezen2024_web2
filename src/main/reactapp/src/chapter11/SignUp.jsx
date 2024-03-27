import { useState } from "react"

export default function SignUp(props){
    const [ name , setName ] = useState("");
    const [ gender , setGender ] = useState("여자");

    const handleChangeName = ( e ) => {
        setName( e.target.value );
        console.log( e.target.value );
    }

    const handleChangeGender = ( e ) => {
        setGender( e.target.value );
        console.log( e.target.value );
    }

    const handleSubmit = ( e ) => {
        alert(`이름 : ${name} , 성별 : ${gender}` );
        console.log(`${name} , ${gender}`);
        e.preventDefault(); 
    }
    return(<>
        <form>
            이름 :
            <input type="text" value={name} onChange={handleChangeName} />            
            <br/>
            성별 : 
            <select value={gender} onChange={handleChangeGender}>
                <option value="BOY">남자</option>
                <option value="GIRL">여자</option>
            </select>
            <button type="button" onClick={handleSubmit}>제출</button>
        </form>
    </>)
}