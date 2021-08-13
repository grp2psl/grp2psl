import React from 'react';
import Jumbotron from 'react-bootstrap/Jumbotron'
import ButtonGroup from 'react-bootstrap/ButtonGroup'
import Button from 'react-bootstrap/Button'
// import {Jumbotron, ButtonGroup, Button} from 'react-bootstrap';

export default class Welcome extends React.Component{
	render(){
		return (
			<Jumbotron className = "bg-white text-black">
				<ButtonGroup>
					<Button size = "12">Change Credentials</Button>
				</ButtonGroup>
			</Jumbotron>
		)
	}
}