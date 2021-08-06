import React from 'react';

import {Card, Table} from 'react-bootstrap';
import axios from 'axios';

class ShowTrainers extends React.Component{
    constructor(props){
        super(props);
        this.state={
            trainers: []
        };
    }

    componentDidMount(){
        axios.get("http://localhost:8080/LearningManagementSystem/trainers/")
        .then(response => response.data)
        .then((data) => {
            console.log(data)
            this.setState({trainers : data});
        });
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
                            {
                                this.state.trainers.length === 0?
                                <tr align="center">
                                    <td colSpan="5">No trainer Available</td>
                                </tr>:
                                this.state.trainers.map((trainer) => {
                                    {console.log(trainer)}
                                    {console.log(trainer.trainerid)}
                                    {console.log(trainer.name)}
                                    {console.log(trainer.department)}
                                    {console.log(trainer.phonenumber)}
                                    {console.log(trainer.email)}
                                    <tr key={trainer.trainerid}> 
                                        <td>{trainer.trainerid}</td>
                                        <td>{trainer.name}</td>
                                        <td>{trainer.department}</td>
                                        <td>{trainer.phonenumber}</td>
                                        <td>{trainer.email}</td>                          
                                    </tr>
                                })
                            }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        );
    }
}

export default ShowTrainers;