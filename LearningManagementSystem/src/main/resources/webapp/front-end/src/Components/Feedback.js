import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import MyToast from './MyToast';
import axios from 'axios';


export default class Feedback extends Component{
	
	constructor(props){
		super(props);
		this.state = {feedback:'', ratings:0};
		this.sendFeedback = this.sendFeedback.bind(this);
		this.feedbackChange = this.feedbackChange.bind(this);
		this.ratingChange = this.ratingChange.bind(this);
		this.goBack = this.goBack.bind(this);
	}
	
	async sendFeedback(event){
		event.preventDefault();
		const re = /^[0-9\b]+$/;
		
		try{
			if (re.test(this.state.ratings)) {
				const response = await axios.put("http://localhost:8080/LearningManagementSystem/learners/feedback/" + this.props.location.state.id, {feedback:this.state.feedback, ratings:this.state.ratings}, {auth: {
                  	username: localStorage.getItem("username"),
                	password: localStorage.getItem("password")
                }});
				if(response != null){
					alert("Feedback submitted successfully");
				}
			}
			else {
				const response = await axios.put("http://localhost:8080/LearningManagementSystem/learners/feedback/" + this.props.location.state.id, {feedback:this.state.feedback, ratings:0}, {auth: {
                  	username: localStorage.getItem("username"),
                	password: localStorage.getItem("password")
                }});
				if(response != null){
					alert("Feedback submitted successfully");
				}
			}
		} catch(error) {
			alert(error.response.data);
		}
	};
	
	feedbackChange(event){
		this.setState({
			feedback:event.target.value
		})
	}
	
	ratingChange(event){
		this.setState({
			ratings:+event.target.value
		})
	}
		
	goBack(){
    	this.props.history.goBack();
	}
	
	initialState = {
		feedback:'',
		ratings:0
	};
	
	
	render(){
		return (<Card className={"border border-dark bg-dark text-white mt-5"}>
			<Card.Header>
				Submit Feedback And Ratings
			</Card.Header>
			<Form id = "feedbackform" onSubmit = {this.sendFeedback}>
				<Card.Body>
					  <Form.Group controlId = "formGridFeedback">
					    <Form.Label>Feedback</Form.Label>
					    <Form.Control required 
					    type="text" 
					    placeholder="Enter feedback" 
					    onChange = {this.feedbackChange}
					    value = {this.state.feedback}
					    name = "feedback"/>
					  </Form.Group>
					
						<Form.Group controlId = "formGridRatings">
							<Form.Label>Ratings</Form.Label>							
							<Form.Control 
								min={0} 
								max={5} 
								step = {1}
								onChange={this.ratingChange}
								value={this.state.ratings}
								name = "ratings"
								className = "form-control"
								/>
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