import React from 'react';

import {Card, Table} from 'react-bootstrap';
import axios from 'axios';
import { DATABASE_URL, TCMAPPING_URL, ADMIN_USERNAME, ADMIN_PASSWORD } from '../constants';

class ScoreAndStatus extends React.Component{
	
    constructor(props){
		
        super(props);
        this.state={
            offerings: [],
            msg:""
        };
    }
    componentWillMount(){
		if(localStorage.getItem('user') != 'manager' || localStorage.getItem('loggedin') === false){
			alert("User not logged in!");
			return this.props.history.push("/");
		}
   	}
    async showData(){
		
		this.setState({
			msg:"Processing.. Please Wait"
		});
		const id = this.props.location.state.learnerId;
		const courseid = this.props.location.state.courseId;
		try{
			const response = await axios.get(DATABASE_URL+TCMAPPING_URL+"/learner/"+id+"/course/"+courseid+"/", {
                auth: {
                username: ADMIN_USERNAME,
                password: ADMIN_PASSWORD
              }
            });
			if(response.data != null) {
				console.log(response.data)
				this.setState({
                    offerings: response.data  
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
                <Card.Header>Course Details</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                            	
                                <th>Course ID</th>
                                <th>Score</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.offerings.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Courses Attended.</td>
                                </tr>
                                ) : (
                                this.state.offerings.map((course) => (
                                    <tr key={course.tcId}>
                                	<td>{this.props.location.state.courseId}</td>
                                    <td>{course.percentage}</td>
                                    <td>{course.status}</td>
                                    
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

export default ScoreAndStatus;