import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import axios from 'axios';

export default class Credentials extends Component{
	constructor(props){
		super(props);
		this.state = {password:''};
		this.sendPassword = this.sendPassword.bind(this);
		this.passwordChange = this.passwordChange.bind(this);
		this.goBack = this.goBack.bind(this);
	}
	
	async sendPassword(event){
		event.preventDefault();
		
		try{ 
			const response = await axios.put("http://localhost:8080/LearningManagementSystem/learners/updatelearner/" + this.props.location.state.learnerid, {password:this.state.password}, 
			{auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
                }});
				if(response != null){
					alert("Password updated successfully");
					localStorage.setItem("password", this.state.password);
				}
		} catch(error) {
			alert(error.response.data);
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
					    <Form.Label>New Password</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter new password" 
					    onChange = {this.passwordChange}
					    value = {this.state.password}
					    name = "password"/>
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