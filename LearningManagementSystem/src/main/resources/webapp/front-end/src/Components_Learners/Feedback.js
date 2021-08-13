import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import MyToast from './MyToast';
import axios from 'axios';

export default class Feedback extends Component{
	
	constructor(props){
		super(props);
		this.state = {feedback:''};
		this.sendFeedback = this.sendFeedback.bind(this);
		this.feedbackChange = this.feedbackChange.bind(this);
		this.goBack = this.goBack.bind(this);
	}
	
	async sendFeedback(event){
		event.preventDefault();
		
		try{            const 
			response = await axios.put("http://localhost:8080/LearningManagementSystem/feedback/" + this.props.location.state.id, {feedback:this.state.feedback});
		} catch(error) {
			alert(error.response.data);
		}
	};
	
	feedbackChange(event){
		this.setState({
			[event.target.name]:event.target.value
		})
	}
		
	goBack(){
    	this.props.history.goBack();
	}
	
	initialState = {
		feedback:''
	};
	
	
	render(){
		return (<Card className="text-black">
			<Card.Header>
				Submit Feedback
			</Card.Header>
			<Form id = "feedbackform" onSubmit = {this.sendFeedback}>
				<Card.Body>
					<Form.Row>
					  <Form.Group controlId = "formGridFeedback">
					    <Form.Label>Feedback</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter feedback" 
					    onChange = {this.feedbackChange}
					    value = {this.state.feedback}
					    name = "feedback"/>
					  </Form.Group>
					</Form.Row>				  			
				</Card.Body>
			<Card.Footer style = {{"textAlign":"right"}}>
				<Button size = "sm" variant="success" type="submit"> Submit </Button>{" "}				
				<Button size = "sm" variant="info" type="button" onClick = {this.goBack}> Go Back </Button>								
			</Card.Footer>
			</Form>
		</Card>);
	}
}