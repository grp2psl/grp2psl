import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';

class ViewCourseOfferings extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            courseOfferings: [],
            msg:""
        };
    }
    
    showLearnersCourse(id){
        console.log(id);
        this.props.history.push({
            pathname: '/ShowCourseAttended',
            state: { detail: id }
        });
    }

    showTrainers(id){
        console.log(id);
        this.props.history.push({
            pathname: '/viewCoursesOffered',
            state: { id: id }
        });
    }

    formatDate(string){
        var options = { year: 'numeric', month: 'long', day: 'numeric' };
        return new Date(string).toLocaleDateString([],options);
    }
    
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/managers/viewCourseOfferingsDetails");
			if(response.data != null) {
				this.setState({
                    courseOfferings: response.data                 
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
                <Card.Header>View Course Offerings</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Course Offering ID</th>
                                <th>Learner ID</th>
                                <th>Learner Name</th>
                                <th>TCID</th>
                                <th>Trainer Name</th>
                                <th>Course Name</th>
                                <th>Start Date</th>
                                <th>End Date</th>
                                <th>Percentage</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.courseOfferings.length === 0? (
                                <tr align="center">
                                    <td colSpan="10">No Courses Offered.</td>
                                </tr>
                                ) : (
                                this.state.courseOfferings.map((courseOffering) => (
                                    <tr key={courseOffering.offering.courseofferingid}>
                                    <td>{courseOffering.offering.courseofferingid}</td>
                                    <td>{courseOffering.learner.learnerid}</td>
                                    <td onClick={() => {
										this.showLearnersCourse(courseOffering.learner.learnerid);
									}}>{courseOffering.learner.name}</td>
                                    <td>{courseOffering.offering.tcid}</td>
                                    <td onClick={() => {
										this.showTrainers(courseOffering.trainer.trainerid);
									}}>{courseOffering.trainer.name}</td>
                                    <td><a href={courseOffering.course.syllabus} className="text-white"  style={{ textDecoration: 'none' }}>{courseOffering.course.coursename}</a></td>
                                    <td>{this.formatDate(courseOffering.offering.startdate)}</td>
                                    <td>{this.formatDate(courseOffering.offering.enddate)}</td>
                                    <td>{courseOffering.offering.percentage}</td>
                                    <td>{courseOffering.offering.status}</td>
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

export default ViewCourseOfferings;