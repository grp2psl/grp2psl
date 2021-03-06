import React from 'react';

import {Container, Row, Col, Card, Button} from 'react-bootstrap';
import {MANAGER_URL} from '../constants';

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

class RegisterLearners extends React.Component{
	registerLearner = () => {
		this.props.history.push(MANAGER_URL+"/register-learner");
	}
		
	registerMultipleLearners = () => {
		this.props.history.push(MANAGER_URL+"/register-multiple-learners");
	}
	
	componentWillMount(){
		if(localStorage.getItem('user') !== 'manager' || localStorage.getItem('loggedin') === false){
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
						          <Card.Title>Register A Learner</Card.Title>
						          <Card.Text>
						            Register a learner to a course by filling in a form
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerLearner} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Multiple Learners</Card.Title>
						          <Card.Text>
						            Register multiple learners by uploading an EXCEL file
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerMultipleLearners} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default RegisterLearners;