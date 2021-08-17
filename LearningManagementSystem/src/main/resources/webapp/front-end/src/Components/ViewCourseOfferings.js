import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faTrash
  } from "@fortawesome/free-solid-svg-icons";
import {DATABASE_URL,MANAGER_URL} from '../constants'

class ViewCourseOfferings extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            courseOfferings: [],
            msg:""
        };
    }
    componentWillMount(){
		if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
			alert("User not logged in!");
			return this.props.history.push("/");
		}
   	}
    showLearnersCourse(id){
        console.log(id);
        this.props.history.push({
            pathname: MANAGER_URL+'/ShowCourseAttended',
            state: { detail: id }
        });
    }

    showTrainers(id){
        console.log(id);
        this.props.history.push({
            pathname: MANAGER_URL+'/viewCoursesOffered',
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
			const response = await axios.get(DATABASE_URL+MANAGER_URL+"/viewCourseOfferingsDetails", {
                auth: {
                    username: localStorage.getItem("username"),
                    password: localStorage.getItem("password")
              }
            });
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
    
    async deleteData(id){
		this.setState({
			msg:"Processing.. Please Wait",
            show: true
		});
		try{
            const response = await axios.delete(DATABASE_URL+MANAGER_URL+"/course-offering/"+id, {
                auth: {
                    username: localStorage.getItem("username"),
                    password: localStorage.getItem("password")
              }
            });
            console.log(response);
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
        this.showData();
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
                                    <tr key={courseOffering.offering.courseOfferingId}>
                                    <td>{courseOffering.offering.courseOfferingId}</td>
                                    <td>{courseOffering.learner.learnerId}</td>
                                    <td onClick={() => {
										this.showLearnersCourse(courseOffering.learner.learnerId);
									}}>{courseOffering.learner.name}</td>
                                    <td>{courseOffering.offering.tcId}</td>
                                    <td onClick={() => {
										this.showTrainers(courseOffering.trainer.trainerId);
									}}>{courseOffering.trainer.name}</td>
                                    <td><a href={courseOffering.course.syllabus} className="text-white"  style={{ textDecoration: 'none' }}>{courseOffering.course.courseName}</a></td>
                                    <td>{this.formatDate(courseOffering.offering.startDate)}</td>
                                    <td>{this.formatDate(courseOffering.offering.endDate)}</td>
                                    <td>{courseOffering.offering.percentage}</td>
                                    <td>{courseOffering.offering.status}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
                                            onClick={() => this.deleteData(courseOffering.offering.courseOfferingId)}
                                        >
                                            <FontAwesomeIcon icon={faTrash} />
                                        </Button>
                                        </ButtonGroup>
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

export default ViewCourseOfferings;