import React from 'react';

import {Container, Row, Col, Card, Button, Form} from 'react-bootstrap';
import axios from 'axios';
import {DATABASE_URL, TRAINER_URL} from '../constants';

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

class TrainerLogin extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.loginTrainer = this.loginTrainer.bind(this);
        this.formChange = this.formChange.bind(this);
    }

    initialState = {
        email: "",
        password: "",
        msg:"",
        trainer:[]
    };


    formChange(event){
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    async loginTrainer(event){
		event.preventDefault();
        this.setState({
			msg:"Processing..\nPlease Wait"
		});
		try{
            console.log(TRAINER_URL);
			const response = await axios.get(DATABASE_URL+"/trainers/login?email="+this.state.email+"&password="+this.state.password, {
                auth: {
                    username: this.state.email,
                    password: this.state.password
              }
            });
			if(response.data != null){
	            console.log(response.data);
                this.state.trainer = response.data;
                localStorage.setItem('loggedin', true);
                localStorage.setItem('userId', response.data.trainerId);
                localStorage.setItem('user', 'trainer');
                localStorage.setItem('username', this.state.email);
                localStorage.setItem('password', this.state.password);
			    this.props.history.push({
	               pathname: TRAINER_URL+'/',
	                state: { trainerid: response.data.trainerId }
	           });
               alert("Logged in successfully");
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
                            <Form id="registerId" onSubmit={this.loginTrainer}>
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						            <Card.Title>Trainer Login</Card.Title>

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

export default TrainerLogin;
