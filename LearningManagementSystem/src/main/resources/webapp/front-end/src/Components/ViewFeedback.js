import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

class ViewFeedback extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            courseDetails: [],
            avgRating: 0,
            offerings: [],
            msg:""
        };
    }
    
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
		});
        const courseId = this.props.location.state.courseId;
        const trainerId = this.props.location.state.trainerId;
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/managers/trainer/"+trainerId+"/course/"+courseId);
			if(response.data != null) {
				this.setState({
                    courseDetails: response.data.courseDetails,
                    avgRating: response.data.avgRating,
                    offerings: response.data.offerings                    
                });	
			}	
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
    }

    componentDidMount(){
		
        this.showData();
    }

    render(){
        return(
	
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Feedback for Course - {this.state.courseDetails.coursename}</Card.Header>
                <h5 className="text-white mt-2">Average Rating - {this.state.avgRating}</h5>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Feedback</th>
                                <th>Rating</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.offerings.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Feedback Found.</td>
                                </tr>
                                ) : (
                                this.state.offerings.map((offering) => (
                                    <tr key={offering.courseofferingid}>
                                    <td>{offering.startdate}</td>
                                    <td>{offering.enddate}</td>
                                    <td>{offering.feedback}</td>
                                    <td>{offering.ratings}</td>
                                    </tr>
                                ))
                                )}

                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ViewFeedback;