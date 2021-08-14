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
import {MANAGER_URL} from '../constants';

const buttonStyle={
    fontSize: "1rem",
    borderRadius: "0.25rem",
    display: "inline-block",
    fontWeight: "400",
    marginTop: "8.3px",
    marginLeft: "7px",
    width: "38px",
    height: "37.5px"
};
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
    render(){
        return(
			<div>
                <Navbar bg="dark" variant="dark">
					<Container>
    				<Link to={MANAGER_URL+"/"} className='navbar-brand'>
                    <img
                        src={book}
                        width="35"
                        height="35"
                        alt="brand"
                    />{" "}
                        Learning Manager
                    </Link>
                    <Nav>
                        <Link to={MANAGER_URL+"/"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-success"
                            >
                            <FontAwesomeIcon icon={faHome} />
                            </Button>{" "}</Link>
                        <Link to={"/view-details-"+localStorage.getItem('user')} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-primary"
                            >
                            <FontAwesomeIcon icon={faUser} />
                            </Button>{" "}</Link>
                        <Link to={"/"} className='nav-link'>
                            <Button
                                size="md"
                                variant="outline-danger"
                                // style={buttonStyle}
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