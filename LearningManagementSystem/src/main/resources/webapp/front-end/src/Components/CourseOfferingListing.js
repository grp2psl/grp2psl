import React, {Component} from 'react';
import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import MyToast from './MyToast';
import ReactStars from 'react-stars';
import { LEARNER_URL } from '../constants';

export default class CourseOfferingListing extends Component{
	
	constructor(props){
		super(props);
		this.state = {offerings:[]};
	}
	
	componentDidMount(){
		axios.get("http://localhost:8080/LearningManagementSystem/learners/Offering/" + localStorage.getItem("userId"), {
			auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
            }}
		).then(
			response => response.data
		).then((data) => {
			this.setState({offerings:data});
		});
	} 
	
	render(){
		return (
			<div>
				<div style = {{"display":this.state.show ? "block" : "none"}}>
					<MyToast show = {this.state.show} message = {"Feedback submitted sucessfully!"} type = {"danger"} /> 
				</div>
				
				<Card className={"border border-dark bg-dark text-white mt-5"}>
				<Card.Header>
					Course Offerings
				</Card.Header>
				<Card.Body>
					<Table bordered hover striped variant="dark">
						<thead>
							<tr>
								<th>Course Offering id</th>
								<th>Start Date</th>
								<th>End Date</th>
								<th>Status</th>
								<th>Ratings</th>
								<th>Percentage</th>
								<th>Feedback</th>
								<th>actions</th>
							</tr>
						</thead>
						
						<tbody>
							{
								this.state.offerings.length === 0 ?
							 	<tr align = "center">
							 		<td colspan = "8">No offerings available.</td>
							 	</tr>:
								this.state.offerings.map((offering) => (
									<tr key = {offering.tcId}>
										<td>{offering.courseOfferingId}</td>
										<td>{offering.startDate}</td>
										<td>{offering.endDate}</td>
										<td>{offering.status}</td>
										<td><ReactStars
                                            count={5}
                                            value={offering.ratings}
                                            size={20}
                                            edit={false}
                                            color2={'#ffd700'} 
                                        /></td>
										<td>{offering.percentage}</td>
										<td>{offering.feedback}</td>
										<td> 
											<ButtonGroup>
												<Button
                                            		size="sm"
                                            		variant="outline-primary"
                                            		onClick={() => this.props.history.push({
                                                	pathname: LEARNER_URL+"/course-details", 
                                                	state: {tcId: offering.tcId}
                                            		})}
                                        		>Show Details</Button>
												 <Button
                                            		size="sm"
                                            		variant="outline-primary"
                                            		onClick={() => this.props.history.push({
                                                	pathname: LEARNER_URL+"/feedback", 
                                                	state: {id: offering.courseOfferingId}
                                            		})}
                                        		>Edit Feedback</Button>
											</ButtonGroup>
										</td>
									</tr>			
								))
							}							
						</tbody>
					</Table>
				</Card.Body>
			</Card>
		</div>);
	}
}