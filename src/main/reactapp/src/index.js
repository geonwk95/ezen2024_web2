import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// * 내가 만든 컴포넌트 import(가져오기) 호출
import JSX선언 from './chapter3/1_JSX선언';

// charter3 실습
import Book from './chapter3/Book';
import Library from './chapter3/Library';
import Clock from './chapter4/Clock';

// chapter5 실습
import CommentList from './chapter5/CommentList';

// chapter7 예제/실습
import Counter from './chapter7/Counter'; // 예제
import UseStateList from './chapter7/UseStateList';

const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render( // !!!!!!!!!!!!! 여기가 렌더링 되는 곳 ~~~~~~~~~~~
//   <React.StrictMode> // 엄격한 실행 모드 : 유효성 검사
//     {/* <Book /> */}
//     {/* <Library /> */}
//     <Clock />
//   </React.StrictMode>
// );
// setInterval( 함수 ( ) , 밀리초 ) : 밀리초 마다 해당 함수 실행
// setInterval( () => {
//   root.render( <Clock /> )
// } , 1000 )
root.render(
  <React.StrictMode>
    {/* <CommentList /> */}
    {/* <Counter /> */}
    <UseStateList />
  </React.StrictMode>
  
)

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
