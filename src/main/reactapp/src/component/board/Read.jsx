import axios from "axios"
import { useEffect, useState } from "react";

export default function Read(props){

        const [ board , setBoard ] = useState([]);    
        useEffect( () =>{
        axios.get("/board/get.do" )
        .then( response => { console.log(response); 
        setBoard(response.data);
        
        })
       .catch( e => { console.log(e); })
}, [] )
console.log(board);
        
    return(<>
        <table>
            <thead>
                <tr>
                    <th>작성자</th>
                    <th>제목</th>
                </tr>
            </thead>
            <tbody>                                    
                    {                                                                                                           
                     board.map( (민형 , index ) => {
                        return (
                            <tr key={index}>
                                <td>{민형.memail}</td>
                                <td>{민형.bcontent}</td>
                            </tr>
                        )
                     })
                    }                                    
            </tbody>
            </table>    
    </>)
}