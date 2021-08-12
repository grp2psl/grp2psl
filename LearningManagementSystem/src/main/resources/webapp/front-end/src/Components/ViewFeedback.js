import React from 'react';

import {Card, Table, Row, Col} from 'react-bootstrap';
import axios from 'axios';
import ReactStars from 'react-stars';

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

    formatDate(string){
        var options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(string).toLocaleDateString([],options);
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
                <Card.Header>Feedback for Course - {this.state.courseDetails.courseName}
                <Row className="mt-2" xs={4} md={4} lg={4}>
                    <Col md={{offset:8}}><h5 className="text-white mt-2">Average Rating</h5></Col>
                </Row>
                <Row>
                    <Col md={{offset:9}}>
                        <ReactStars
                            count={5}
                            value={this.state.avgRating}
                            size={20}
                            edit={false}
                            color2={'#ffd700'} 
                        />
                    </Col>
                </Row>
                </Card.Header>
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
                                    <td colSpan="4">No Feedback Found.</td>
                                </tr>
                                ) : (
                                this.state.offerings.map((offering) => (
                                    <tr key={offering.courseOfferingId}>
                                    <td>{this.formatDate(offering.startDate)}</td>
                                    <td>{this.formatDate(offering.endDate)}</td>
                                    <td>{offering.feedback}</td>
                                    <td>
                                        <ReactStars
                                            count={5}
                                            value={offering.ratings}
                                            size={20}
                                            edit={false}
                                            color2={'#ffd700'} 
                                        />
                                    </td>
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