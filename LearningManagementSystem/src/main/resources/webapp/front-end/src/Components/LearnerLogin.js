import React from 'react';

import {Container, Row, Col, Card, Button, Form} from 'react-bootstrap';
import axios from 'axios';
import {DATABASE_URL} from '../constants';

const cardStyle={
	height: '300px',
	marginTop: '20px'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

class LearnerLogin extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.loginLearner = this.loginLearner.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        email: "",
        password: "",
        msg:""
    };

    
    formChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }
    
    async loginLearner(event){
		event.preventDefault();
        this.setState({
			msg:"Processing..\nPlease Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+"/learners/login?email="+this.state.email+"&password="+this.state.password);
			alert("Tried login")
            if(response.data != null){
	        	alert("Logged in successfully");
	            console.log(response.data);
                localStorage.setItem('loggedin', true);
                localStorage.setItem('userId', response.data.learnerId);
                localStorage.setItem('user', 'learner');
        	}	
		} catch(error) {
			alert("Login failed");
		}
        this.setState(this.initialState);
    }

	render(){
        return(
			<div className="mt-5">
                <Container>
                        <h3 className="text-white mt-2">{this.state.msg}</h3>
                		<Row xs={1} md={3} className="g-4 mb-4">
                            <Col></Col>
						    <Col>
                                <Form id="registerId" onSubmit={this.loginLearner}> 
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						            <Card.Title>Learner Login</Card.Title>
                                                                           
                                        <Form.Group className="mb-3" controlId="email">
                                            <Form.Label>Email ID</Form.Label>
                                            <Form.Control required autoComplete="off"
                                                type="email" 
                                                value={this.state.email}
                                                onChange={this.formChange}
                                                name="email"
                                                placeholder="Enter email" />
                                        </Form.Group>
                                        <Form.Group className="mb-3" controlId="password">
                                            <Form.Label>Password</Form.Label>
                                            <Form.Control required autoComplete="off"
                                                type="password" 
                                                value={this.state.password}
                                                onChange={this.formChange}
                                                name="password"
                                                placeholder="Enter password" />
                                        </Form.Group>
                                    
						        </Card.Body>
                                <Card.Footer>
                                    <Button variant="success" type="submit" style={buttonStyle}>
                                        Log in
                                    </Button>
                                </Card.Footer>
						      </Card>
                            </Form>
						    </Col>  
                            <Col></Col>
						</Row>
						
                </Container>
            </div>);
    }
}

export default LearnerLogin;