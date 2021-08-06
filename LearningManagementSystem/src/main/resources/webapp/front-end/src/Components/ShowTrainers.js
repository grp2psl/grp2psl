import React from 'react';

import {Card, Table, ButtonGroup, Button} from 'react-bootstrap';
import axios from 'axios';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faSave,
    faUndo,
    faEdit,
    faTrash
  } from "@fortawesome/free-solid-svg-icons";

class ShowTrainers extends React.Component{
    constructor(props){
        super(props);
        this.state={
            trainers: []
        };
    }
    
    showData(){
        axios.get("http://localhost:8080/LearningManagementSystem/trainers/")
        .then(response => response.data)
        .then((data) => {
            console.log(data)
            this.setState({trainers : data});
        });

    }

    componentDidMount(){
        this.showData();
    }

    render(){
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header>Trainers</Card.Header>
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
                                    <tr key={trainer.trainerid}>
                                    <td>{trainer.trainerid}</td>
                                    <td>{trainer.name}</td>
                                    <td>{trainer.department}</td>
                                    <td>{trainer.phonenumber}</td>
                                    <td>{trainer.email}</td>
                                    <td>
                                        <ButtonGroup>
                                        <Button
                                            size="sm"
                                            variant="outline-primary"
                                        >
                                            <FontAwesomeIcon icon={faEdit} />
                                        </Button>{" "}
                                        <Button
                                            size="sm"
                                            variant="outline-danger"
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