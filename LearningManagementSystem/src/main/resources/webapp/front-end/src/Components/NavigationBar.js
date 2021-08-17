import React from 'react';

import {Navbar, Nav, Container, Button} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import book from '../books.png';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
    faHome,
    faUser,
    faSignOutAlt
  } from "@fortawesome/free-solid-svg-icons";

class NavigationBar extends React.Component{
    constructor(props){
        super(props);
        this.logout = this.logout.bind(this);
    }
    logout(){
        localStorage.setItem('loggedin', false);
        localStorage.setItem('userId', '');
        localStorage.setItem('user', '');
        alert("Logged out successfully!");
    }
    disableUse() {
        if(window.location.pathname === '/learning-manager/' || window.location.pathname === '/learning-manager/home' || window.location.pathname === '/learning-manager/admin-login' || window.location.pathname === '/learning-manager/trainer-login' || window.location.pathname === '/learning-manager/learner-login') {
            return true;
        } 
        return false;
    }
    render(){
        return(
			<div>
                <Navbar bg="dark" variant="dark">
					<Container>
    				<Link to={"/home"} className='navbar-brand'>
                    <img
                        src={book}
                        width="35"
                        height="35"
                        alt="brand"
                    />{" "}
                        Learning Manager
                    </Link>
                    <Nav>
                        <Link to={"/home"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-success"
                            >
                            <FontAwesomeIcon icon={faHome} />
                            </Button>{" "}</Link>
                        <Link to={"/view-details"} onClick={(e) => {
                            if(this.disableUse()) {
                                e.preventDefault();
                            }
                        }} className='nav-link'>
                            <Button
                                disabled={this.disableUse()}
                                size="md"
                                variant="outline-primary"
                            >
                            <FontAwesomeIcon icon={faUser} />
                            </Button>{" "}</Link>
                        <Link to={"/"} onClick={(e) => {
                            if(this.disableUse()) {
                                e.preventDefault();
                            }
                        }} className='nav-link'>
                            <Button
                                disabled={this.disableUse()}
                                size="md"
                                variant="outline-danger"
                                onClick={() => this.logout()}
                            >
                            <FontAwesomeIcon icon={faSignOutAlt} />
                            </Button>{" "}</Link>
                    </Nav>
                    </Container>                
                </Navbar>
            </div>);
    }
}

export default NavigationBar;