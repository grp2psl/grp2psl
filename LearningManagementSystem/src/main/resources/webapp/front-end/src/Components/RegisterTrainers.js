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

class RegisterTrainers extends React.Component{
		registerTrainer = () => {
			this.props.history.push(MANAGER_URL+"/registerTrainer");
		}
		
		registerMultipleTrainers = () => {
			this.props.history.push(MANAGER_URL+"/registerMultipleTrainers");
		}
	
    render(){
        return(
			<div className="mt-5">
                <Container>
                        <Row xs={1} md={2} className="g-4">
						    <Col>
						      <Card className="bg-light" style={cardStyle}> 
						        <Card.Body>
						          <Card.Title>Register A Trainer</Card.Title>
						          <Card.Text>
						            Register a trainer to a course by filling in a form
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerTrainer} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						    <Col>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						          <Card.Title>Register Multiple Trainers</Card.Title>
						          <Card.Text>
						            Register multiple trainers by uploading an EXCEL file
						          </Card.Text>
						          <Button variant="primary" onClick={this.registerMultipleTrainers} style={buttonStyle}>Go</Button>
						        </Card.Body>
						      </Card>
						    </Col>
						</Row>
                </Container>
            </div>);
    }
}

export default RegisterTrainers;