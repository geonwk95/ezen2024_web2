
export default function AttendanceBook(props){

    // 1. 샘플 데이터
    const students = [
        { id : 1 , name : "inje"} , 
        { id : 2 , name : "steve"} , 
        { id : 3 , name : "bill"} , 
        { id : 4 , name : "jeff"}  
    ]


    return(<>
        <ul>
            {
            students.map( (student , index) => {
                return <li key={student.id}>{student.name}</li>
            })
        }
        </ul>
    </>)
}