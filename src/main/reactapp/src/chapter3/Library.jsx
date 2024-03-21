import Book from "./Book";

export default function Library( props ){
    let successData = [
        { name : '처음 만난 파이썬' , numOfPage : 300 } ,
        { name : '처음 만난 AWS' , numOfPage : 400 } ,
        { name : '처음 만난 리액트' , numOfPage : 500 }
    ]
    return(
        <div>
            {
                successData.map( (data) => {
                    return(
                        <Book name = { data.name } numOfPage = { data.numOfPage } />
                    )
                })
            }
        </div>
    ); // { } : JSX에서 JS코드 입력하겠다는 뜻
}