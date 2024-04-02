import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";



export default function Read(props){

        const [ board , setBoard ] = useState([]);    
        useEffect( () =>{
        axios.get("/board/get.do" )
        .then( response => { console.log(response.data);
        setBoard(response.data);        

        })
       .catch( e => { console.log(e); })
}, [] )
console.log(board);
        
    return(<>
        <div style={ { display : "flex" } }>
        {            
            board.map( (board) => {                
                return (
                    <MediaCard board = {board} />
                )
            })
                

        }
        </div> 
    </>)
}