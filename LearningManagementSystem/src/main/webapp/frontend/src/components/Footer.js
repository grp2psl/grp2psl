import React, {Component} from 'react';
import {Navbar, Container, Col} from 'react-bootstrap';

export default class Footer extends Component{
	
	render(){
		let FullYear = new Date().getFullYear();
			return(
				<Navbar fixed = "bottom">
					<Container>
						<Col lg = {12} className = {"text-center text-muted"}>
							<div> Group 2 Mini Project, {FullYear} - {FullYear + 1} </div>
						</Col>
					</Container>
				</Navbar>
			);
	}
	
}