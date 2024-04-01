// 1. 컴포넌트1 : 최상위 : 할머니
export default function ContextApp(props){

    return(<>
        <Toolbak theme="dark" />
    </>)
}

// 2. 컴포넌트1 : 상위 : 부모
function Toolbak(props){
    console.log(props); // props = { theme="dark" }
    return(<>
        <ThemedButton theme={props.theme} /> 
    </>)
}

// 3. 컴포넌트3 : 하위 : 자식
function ThemedButton(props){
    console.log(props); // props = { theme="dark" }
    return(<>
        <Button theme={props.theme} />;
    </>)
}