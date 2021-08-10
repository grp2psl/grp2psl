import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

class ShowLearners extends React.Component{
    constructor(props){
        super(props);
        this.state={
            learners: [],
            msg:""
        };
        
    }
    
    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get("http://localhost:8080/LearningManagementSystem/learners/");
			if(response.data != null) {
				this.setState({
					learners: response.data
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
            const response = await axios.delete("http://localhost:8080/LearningManagementSystem/learners/"+id);
            console.log(response);
		} catch(error) {
			alert(error);
		}
        this.setState({
			msg: ""
		})
        this.showData();
    }
    
    showCourse(id){
       console.log(id);
       this.props.history.push({
           pathname: '/ShowCourseAttended',
           state: { detail: id }
       });
    }
    
    editDetails(learner){
        this.props.history.push({
            pathname: '/editLearnerDetails',
            state: { learner: learner }
        });
     }
    
    
	
    componentDidMount(){
        this.showData();
         
    }

    render(){
        
        return(            
            
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Learners</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Learner Id</th>
                                <th>Learner Name</th>
                                <th>Department</th>
                                <th>Phone Number</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.learners.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Learner Available.</td>
                                </tr>
                                ) : (
                                this.state.learners.map((learner) => (
									
                                    <tr key={learner.learnerid}>  
                                            
                                    <td>{learner.learnerid}</td>
                                    <td>{learner.name}</td>
                                    <td>{learner.department}</td>
                                    <td>{learner.phonenumber}</td>
                                    <td>{learner.email}</td>
                                    <td>
                                        <ButtonGroup>
                                       
                                       
                                        
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={() => this.showCourse(learner.learnerid)}
                                        >
                                            Courses
                                        </Button>{" "}
                                        
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={() => this.editDetails(learner)}
                                        >
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Button>{" "}
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
                                            onClick={() => this.deleteData(learner.learnerid)}
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

export default ShowLearners;