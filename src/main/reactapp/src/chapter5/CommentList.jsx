import Comment from "./Comment"

export default function CommentList ( props ){
    console.log('안녕');
    let response = [ {name : '유재석' , content : '안녕하세요1'} ,
                     {name : '강호동' , content : '안녕하세요2'} ,
                     {name : '신동엽' , content : '안녕하세요3'} 
                ];
    return (
        <div>
            {
                response.map( (map) => {
                    return(
                        <Comment name = {map.name} content = {map.content} />
                    )
                })
            }
        </div>
    )
}