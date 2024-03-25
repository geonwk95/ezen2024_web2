
const styles ={
    wrapper : {
        padding : 16 ,
        display : "flex" ,
        flexDirection : "row" ,
        border : "1px solid grey" ,
    } ,
    greeting : {
        marginRight : 8 ,
    } ,
};


export default function Toolbar(props){
        const { isLoggedIn , onClickLogin , onClickLogout } = props;

    return(<>
        <div style ={styles.wrapper}>
            {isLoggedIn && <span style ={styles.wrapper}> 환영합니다! </span>}
            {isLoggedIn ? (
                <button onClick={onClickLogout}> 로그아웃 </button>
            ) : (
                <button onClick={onClickLogin}> 로그인 </button>
            )
        }
        </div>
    </>)
}