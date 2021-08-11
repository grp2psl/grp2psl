import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';

export default class Feedback extends Component{
	
	constructor(props){
		super(props);
		this.state = {feedback:''};
		this.sendFeedback = this.sendFeedback.bind(this);
		this.feedbackChange = this.feedbackChange.bind(this);
	}
	
	sendFeedback(event){
		alert(this.state.feedback);
		event.preventDefault();
	}
	
	feedbackChange(event){
		this.setState({
			[event.target.name]:event.target.value
		})
	}
	
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
				<Button size = "sm" variant="success" type="submit"> Submit </Button>				
			</Card.Footer>
			</Form>
		</Card>);
	}
}