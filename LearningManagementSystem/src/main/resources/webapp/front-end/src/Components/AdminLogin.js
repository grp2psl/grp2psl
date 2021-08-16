import React from 'react';

import {Container, Row, Col, Card, Button, Form} from 'react-bootstrap';
import axios from 'axios';
import {DATABASE_URL, MANAGER_URL, ADMIN_USERNAME, ADMIN_PASSWORD} from '../constants';

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

class AdminLogin extends React.Component{
    constructor(props){
        super(props);
        this.state = this.initialState;
        this.loginAdmin = this.loginAdmin.bind(this);
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
    
    async loginAdmin(event){
		event.preventDefault();
        this.setState({
			msg:"Processing..\nPlease Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+"/managers/login?email="+this.state.email+"&password="+this.state.password, {
                auth: {
                  username: ADMIN_USERNAME,
                  password: ADMIN_PASSWORD
                }
              });
			if(response.data != null){
	            console.log(response.data);
                localStorage.setItem('loggedin', true);
                localStorage.setItem('userId', response.data.managerId);
                localStorage.setItem('user', 'manager');
                this.props.history.push(MANAGER_URL+"/");
	        	alert("Logged in successfully");
                console.log(localStorage.getItem('user'))
                console.log(localStorage.getItem('userId'))
                console.log(localStorage.getItem('loggedin'))
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
                            <Form id="registerId" onSubmit={this.loginAdmin}>  
						      <Card className="bg-light" style={cardStyle}>
						        <Card.Body>
						            <Card.Title>Admin Login</Card.Title>
                                                                          
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

export default AdminLogin;