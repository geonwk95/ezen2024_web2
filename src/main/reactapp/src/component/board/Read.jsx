import axios from "axios"
import { useEffect, useState } from "react";
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";



export default function Read(props){

    const [ pageDto , setpageDto ] = useState({
        page : 1 , count : 0 , data : []
    });        

     useEffect(  () =>{
        
        const info = { page : pageDto.page , view : 2 }            

        axios.get("/board/get.do" , { params : info })
        .then( response => { console.log(response.data);
            setpageDto(response.data);        
        })
        .catch( e => { console.log(e); })

}, [pageDto.page] )
        
    const handleChange = async (event, value) => {
        pageDto.page = value;
         setpageDto({...pageDto})
    };

    return(<>
        <div style={ { display : "flex" } }>
        {            
            pageDto.data.map( (board) => {                
                return (
                    <MediaCard board = {board} setpageDto = { setpageDto } />
                )
            })            
        }
        </div>         
            <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />           
    </>)
}