import React, {Component} from 'react';
import {Card, Form, Button} from 'react-bootstrap';
import MyToast from './MyToast';
import axios from 'axios';
import Slider from 'react-rangeslider';
import NumericInput from 'react-numeric-input';




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
						
		try{
			console.log(this.state.ratings)           
		const  response = await axios.put("http://localhost:8080/LearningManagementSystem/feedback/" + this.props.location.state.id, {feedback:this.state.feedback, ratings:this.state.ratings});
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
		return (<Card className="text-black">
			<Card.Header>
				Submit Feedback And Ratings
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
					
					<Form.Row>
						<Form.Group controlId = "formGridRatings">
							<Form.Label>Ratings</Form.Label>							
							<NumericInput 
								min={0} 
								max={5} 
								step = {1}
								onValueChange={this.ratingChange}
								value={this.state.ratings}
								name = "ratings"/>
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