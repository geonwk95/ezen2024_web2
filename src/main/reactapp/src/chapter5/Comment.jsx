
// import styles from 'CSS파일경로' 
import styles from './Comment.css'
// ../ : 상위 폴더 , ./ : 현재 폴더
// 이미지 파일호출 : import 이미지변수명 from '이미지파일 경로'
import image from './다운로드.jpg'

export default function Comment ( props ){
    return (
        <div className="wrapper">
            <div>
                <img className="image" src={ image } />
            </div>
            <div className="contentContainer">
                <span className="nameText"> {props.name} </span>
                <span className="commentText"> {props.content} </span>
            </div>        
        </div>
    )
}