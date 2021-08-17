import React from 'react';
import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import {TRAINER_URL} from '../constants';

const cardStyle={
	height: '150px',
	marginTop: '20px'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

export default class WelcomeTrainer extends React.Component{
	componentWillMount(){
         if(localStorage.getItem('user') != 'trainer' || localStorage.getItem('loggedin') === false){
			 alert("User not logged in!");
			 console.log(localStorage.getItem('user'))
			 console.log(localStorage.getItem('loggedin'))
			 return this.props.history.push("/");
		 }
    }
    
    constructor(props){
        super(props);
    }
	
	render(){
		return (
			<div className="mt-5">
                <Container>
                		<Row xs={1} md={2} className="g-4 mb-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Change Credentials</Card.Title>
						          <Card.Text>
						            Change Password   
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={()=>{
                                                this.props.history.push({
                                                    pathname: TRAINER_URL+"/UpdateTrainerPassword",
                                                    
                                                })
                                            }}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>View Courses</Card.Title>
						          <Card.Text>
						            View all courses
						          </Card.Text>
						          <Button variant="primary" style={buttonStyle} onClick={()=>{
                                            this.props.history.push({
                                                pathname: TRAINER_URL+"/ViewCoursesTrainer",
                                                
                                               
                                            })
                                        }}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>		
                </Container>
            </div>
		)
	}
}
