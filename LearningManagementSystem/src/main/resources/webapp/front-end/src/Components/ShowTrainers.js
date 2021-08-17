import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";
import { DATABASE_URL, TRAINER_URL, MANAGER_URL } from '../constants';

class ShowTrainers extends React.Component{
    constructor(props){
        super(props);
        this.state={
            trainers: [],
            msg:""
        };
    }
    componentWillMount(){
		if(localStorage.getItem('user') !== 'manager' || localStorage.getItem('loggedin') === false){
			alert("User not logged in!");
			return this.props.history.push("/");
		}
   	}
    async showData(){
		this.setState({
			msg:"Processing.. Please Wait"
		});
		try{
			const response = await axios.get(DATABASE_URL+MANAGER_URL+TRAINER_URL+"/", {
                auth: {
                username: localStorage.getItem("username"),
                password: localStorage.getItem("password")
              }
            });
			if(response.data != null) {
				this.setState({
					trainers: response.data
				});	
			}	
		} catch(error) {
			alert("Operation failed!");
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
            const response = await axios.delete(DATABASE_URL+MANAGER_URL+TRAINER_URL+"/"+id, {
                auth: {
                    username: localStorage.getItem("username"),
                    password: localStorage.getItem("password")
              }
            });
            console.log(response);
		} catch(error) {
			alert("Operation failed!");
		}
        this.setState({
			msg: ""
		})
        this.showData();
    }

    editDetails(trainer){
        this.props.history.push({
            pathname: MANAGER_URL+'/edit-trainer-details',
            state: { trainer: trainer}
        });
    }

    componentDidMount(){
        this.showData();
    }

    render(){
        
        return(            
            
            <Card className={"border border-dark bg-dark text-white mt-5"}>
                <Card.Header>Trainers</Card.Header>
                <h3 className="text-white mt-2">{this.state.msg}</h3>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <th>Trainer Id</th>
                                <th>Trainer Name</th>
                                <th>Department</th>
                                <th>Phone Number</th>
                                <th>Email</th>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.trainers.length === 0? (
                                <tr align="center">
                                    <td colSpan="7">No Trainers Available.</td>
                                </tr>
                                ) : (
                                this.state.trainers.map((trainer) => (
                                    <tr key={trainer.trainerId}>
                                    <td>{trainer.trainerId}</td>
                                    <td>{trainer.name}</td>
                                    <td>{trainer.department}</td>
                                    <td>{trainer.phoneNumber}</td>
                                    <td>{trainer.email}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={() => this.props.history.push({
                                                pathname: MANAGER_URL+"/offered-courses",
                                                state: {id: trainer.trainerId}
                                            })}
                                        >Courses</Button>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                            onClick={() => this.editDetails(trainer)}
                                        >
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Button>{" "}
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
                                            onClick={() => this.deleteData(trainer.trainerId)}
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

export default ShowTrainers;