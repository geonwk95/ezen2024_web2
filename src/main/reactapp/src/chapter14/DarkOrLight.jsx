import { useCallback, useState } from "react"
import ThemeContext from "./ThemeContext";
import MainContent from "./MainContent";

export default function DarkOrLight(props){

    // 1. 테마 상태
    const [theme , settheme] = useState("light");

    // 2. 테마 상태 변경
    const toggleTheme = useCallback(() => {        
        console.log(theme);
        if(theme == "light"){
            settheme("dark");
        }else if(theme == "dark"){
            settheme("light");
        }
    } , [theme] );

    return(<>
        <ThemeContext.Provider value={{ theme , toggleTheme }}>
            <MainContent />
        </ThemeContext.Provider>
    </>)
}