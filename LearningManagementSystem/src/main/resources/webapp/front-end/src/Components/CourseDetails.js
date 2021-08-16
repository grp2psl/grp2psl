import React, {Component} from 'react';
import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import {BrowserRouter as useParams, Link} from 'react-router-dom';
import MyToast from './MyToast';
import ReactStars from 'react-stars';
import {LEARNER_USERNAME, LEARNER_PASSWORD, LEARNER_URL } from '../constants';

export default class CourseDetails extends Component{
	
	constructor(props){
		super(props);
		this.state = {course:[]};
		this.goBack = this.goBack.bind(this);
	}
	
	componentDidMount(){
		axios.get("http://localhost:8080/LearningManagementSystem/GetCourseDetails/" + this.props.location.state.tcId, {
			auth: {
              username: LEARNER_USERNAME,
              password: LEARNER_PASSWORD
            }}
		).then(
			response => response.data
		).then((data) => {
			this.setState({course:data});
		});
	} 
	
	goBack(){
    	this.props.history.goBack();
	}
	
	render(){
		return (
			<div>
				<Card className={"border border-dark bg-dark text-white mt-5"}>
				<Card.Header>
					Course Offerings
				</Card.Header>
				<Card.Body>
					<Table bordered hover striped variant="dark">
						<thead>
							<tr>
								<th>Course Id</th>
								<th>Course Name</th>
								<th>Course Syllabus</th>
							</tr>
						</thead>
						
						<tbody>
							{
								this.state.course.length === 0 ?
							 	<tr align = "center">
							 		<td colspan = "8">No offerings available.</td>
							 	</tr>:
									<tr>
										<td>{this.state.course.courseId}</td>
										<td>{this.state.course.courseName}</td>
										<td>{this.state.course.syllabus}</td>
									</tr>			
							}							
						</tbody>
					</Table>
				</Card.Body>
				<Card.Footer style = {{"textAlign":"right"}}>
					<Button size = "sm" variant="info" type="button" onClick = {this.goBack}> Go Back </Button>								
				</Card.Footer>
			</Card>
		</div>);
	}
}