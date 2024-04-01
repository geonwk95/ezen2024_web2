import React from "react";

// 1. ThemeContext 라는 이름으로 컨텍스트 를 만든다.
const ThemeContext = React.createContext();
// 2. 개발자도구에서 컨텍스트의 이름을 확인하기위해 
ThemeContext.displayName = "ThemeContext";
// 3. 해당 파일 내보내기
export default ThemeContext;