
import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function MediaCard(props) {
  console.log(props);
  console.log(props.board.bno);

  const navigate = useNavigate();

  const onDelete = (bno) => {
    console.log(bno);
      
    axios.delete("/board/delete.do?bno="+bno+"&mno="+props.board.mno_fk )
        .then( response => { console.log( response )          
          if( response.data){
            // 1. get 방식
            window.location.href ="/board"
            // 2. 라우터 방식
            // navigate("/board");
            // 3. props 방식 
            
          }else{
            alert('니아이디로 쓴 글만 지우렴');
          }
        } )
        .catch( (e) => { console.log(e); } )
  }


  return (
    <Card sx={{ width : '20%' }} style={{ margin : 10 }}>
      <CardMedia
        sx={{ height: 200 }}
        image={"/uploadimg/"+props.board.photonameList[0] }
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
          {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small">Share</Button>
        <Button onClick={ () => onDelete(props.board.bno) } size="small">삭제</Button>
      </CardActions>
    </Card>
  );
}