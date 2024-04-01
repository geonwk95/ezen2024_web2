import { useRef } from "react"

export default function TextInputWithFocusButton(props){

    const inputElem = useRef(null);

    const onButtonClick = () => {
        inputElem.current.focus();
        console.log(inputElem);
        console.log(inputElem.current);
        console.log(inputElem.current.focus());
    };

    return(<>
        <input ref={inputElem} type="text" />
        <button onClick={onButtonClick}>Focus the input</button>
    </>)
}