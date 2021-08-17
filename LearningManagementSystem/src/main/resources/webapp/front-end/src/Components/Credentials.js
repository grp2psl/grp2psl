import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import axios from 'axios';
import {DATABASE_URL, MANAGER_URL,LEARNER_URL} from '../constants';

export default class Credentials extends Component{
	constructor(props){
		super(props);
		this.state = {currentPassword:'',newpassword:'',confirmpassword:''};
		this.sendPassword = this.sendPassword.bind(this);
		this.passwordChange = this.passwordChange.bind(this);
		this.goBack = this.goBack.bind(this);
	}
	
	async sendPassword(event){
		event.preventDefault();
		
		try{ 
			const response = await axios.put(DATABASE_URL+LEARNER_URL+"/updatelearner/" + localStorage.getItem("userId")+"/currentPassword/"+this.state.currentPassword+"/newPassword/"+this.state.newpassword, 
			{},{auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
                }});
				if(response != null){
					alert("Password updated successfully");
					localStorage.setItem("password", this.state.newpassword);
				}
		} catch(error) {
			alert(error.response);
		}
	};
	
	passwordChange(event){
		this.setState({
			[event.target.name]:event.target.value
		})
	}
	
	goBack(){
    	this.props.history.goBack();
	}
	
	initialState = {
		password:''
	};
	
	render(){
		return (<Card className={"border border-dark bg-dark text-white mt-5"}>
			<Card.Header>
				Change Password
			</Card.Header>
			<Form id = "credentialsform" onSubmit = {this.sendPassword}>
			
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>Current Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter current password" 
					    onChange = {this.passwordChange}
					    value = {this.state.currentPassword}
					    name = "currentPassword"/>
					  </Form.Group>
				</Card.Body>
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>New Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter new password" 
					    onChange = {this.passwordChange}
					    value = {this.state.newpassword}
					    name = "newpassword"/>
					  </Form.Group>
				</Card.Body>
				<Card.Body>
					  <Form.Group controlId = "formGridCredentials">
					    <Form.Label>Confirm Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Reenter password" 
					    onChange = {this.passwordChange}
					    value = {this.state.confirmpassword}
					    name = "confirmpassword"/>
					  </Form.Group>
				</Card.Body>
				
				
			<Card.Footer style = {{"textAlign":"right"}}>
				<Button size = "sm" variant="success" type="submit"> Submit </Button>{" "}				
				<Button size = "sm" variant="info" type="button" onClick = {this.goBack}> Go Back </Button>								
			</Card.Footer>
			</Form>
		</Card>);
	}
}