import React, {Component} from 'react';
import {Card, Table, Button} from 'react-bootstrap';
import axios from 'axios';

export default class CourseDetails extends Component{
	
	constructor(props){
		super(props);
		this.state = {course:[]};
		this.goBack = this.goBack.bind(this);
	}
	
	componentDidMount(){
		axios.get("http://localhost:8080/LearningManagementSystem/learners/GetCourseDetails/" + this.props.location.state.tcId, {
			auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
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
										<td>{<a target='_blank' href={this.state.course.syllabus}>Click here to see syllabus</a>}</td>
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