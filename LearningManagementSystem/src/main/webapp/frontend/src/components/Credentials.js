import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';

export default class Credentials extends Component{
	constructor(props){
		super(props);
		this.state = {email:'', password:''};
		this.sendCredentials = this.sendCredentials.bind(this);
		this.CredentialsChange = this.CredentialsChange.bind(this);
	}
	
	sendCredentials(event){
		alert("Email = " + this.state.email + "\nPassword = " + this.state.password);
		event.preventDefault();
	}
	
	CredentialsChange(event){
		this.setState({
			[event.target.name]:event.target.value
		})
	}
	render(){
		return (<Card className="text-black">
			<Card.Header>
				Change Credentials
			</Card.Header>
			<Form id = "credentialsform" onSubmit = {this.sendCredentials}>
				<Card.Body>
					<Form.Row>
					  <Form.Group controlId = "formGridEmail">
					    <Form.Label>Email</Form.Label>
					    <Form.Control type="email"
					    required 
					    onChange = {this.CredentialsChange}
					    value = {this.state.email}
					    name = "email"
					    placeholder="Enter exisitng email" />
					  </Form.Group>
					  <Form.Group controlId = "formGridPassword">
					    <Form.Label>Password</Form.Label>
					    <Form.Control type="password" 
					    required
					    onChange = {this.CredentialsChange}
					    value = {this.state.password}
					    name = "password"
					    placeholder="Enter new password" />
					  </Form.Group>
					</Form.Row>				  			
				</Card.Body>
			<Card.Footer style = {{"textAlign":"right"}}>
				<Button size = "sm" variant="success" type="submit"> Submit </Button>				
			</Card.Footer>
			</Form>
		</Card>);
	}
}