import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';

const cardStyle={
	height: '200px',
	marginTop: '30px'
};
const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

const MANAGER_URL = process.env.REACT_APP_MANAGER_URL;

class TeacherCourseMappingsRegister extends React.Component{
	assignTrainer = () => {
		this.props.history.push(MANAGER_URL+"/TeacherCourseMappingRegister");
	}
		
	assignMultipleTrainers = () => {
		this.props.history.push(MANAGER_URL+"/MultipleTeacherCourseMappingRegister");
	}
	componentWillMount(){
		if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
			alert("User not logged in!");
			return this.props.history.push("/");
		}
   	}
	
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Assign A trainer</Card.Title>
						          <Card.Text>
						            Assign a trainer to course
						          </Card.Text>
						          <Button variant="primary" onClick={this.assignTrainer} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Assign Multiple Trainers</Card.Title>
						          <Card.Text>
						            Assign multiple trainers to course
						          </Card.Text>
						          <Button variant="primary" onClick={this.assignMultipleTrainers} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default TeacherCourseMappingsRegister;