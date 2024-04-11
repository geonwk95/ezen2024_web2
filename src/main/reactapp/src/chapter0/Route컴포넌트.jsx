import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import JSX선언 from "../chapter3/1_JSX선언";
import Library from "../chapter3/Library";
import Clock from "../chapter4/Clock";
export default function Route컴포넌트(props){

    return(<>
        
        
        <BrowserRouter>
            <div id="wrap" style={ { display : "flex" } }>
                <고정형컴포넌트/>

                <Routes>
                    <Route path="/chapter3/jsx선언" element = { <JSX선언 /> } />
                    <Route path="/chapter3/library" element = { <Library /> } />
                    <Route path="/chapter4/clock" element = { <Clock /> } />
                </Routes>                
            </div>
        </BrowserRouter>    
    </>);
}

function 고정형컴포넌트(props){
    return(<>
        <div>
            <ul>
                <li> <a href="/chapter3/jsx선언"> 1번째 가상 주소 A </a> </li>
            </ul>
            <ul>
                <li> <Link to="/chapter3/jsx선언"> 1번째 가상 주소 링크 </Link> </li>
                <li> <Link to="/chapter3/library"> 2번째 가상 주소 링크 </Link> </li>
                <li> <Link to="/chapter4/clock"> 3번째 가상 주소 링크 </Link> </li>
            </ul>
        </div>
    </>)
}