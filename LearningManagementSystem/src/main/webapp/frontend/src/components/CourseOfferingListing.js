import React, {Component} from 'react';
import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import {BrowserRouter as useParams} from 'react-router-dom';

export default class CourseOfferingListing extends Component{
	
	constructor(props){
		super(props);
		this.state = {offerings:[]};
	}
	
	componentDidMount(){
		axios.get("http://localhost:8080/LearningManagementSystem/Offering/" + this.props.match.params.learnerid
		).then(
			response => response.data
		).then((data) => {
			this.setState({offerings:data});
		});
	} 
	
	render(){
		return (<Card className="text-white">
			<Card.Header>
				Course Offerings
			</Card.Header>
			<Card.Body>
				<Table bordered hover striped>
					<thead>
						<tr>
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
						 		<td colspan = "8">offerings available.</td>
						 	</tr>:
							this.state.offerings.map((offering) => (
								<tr key = {offering.tcId}>
									<td>{offering.startDate}</td>
									<td>{offering.endDate}</td>
									<td>{offering.status}</td>
									<td>{offering.ratings}</td>
									<td>{offering.percentage}</td>
									<td>{offering.feedback}</td>
									<td> 
										<ButtonGroup>
											<Button size = "12"> Send Feedback </Button>
										</ButtonGroup>
									</td>
								</tr>			
							))
						}							
					</tbody>
				</Table>
			</Card.Body>
		</Card>);
	}
}