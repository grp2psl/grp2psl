import React from 'react';

import {Navbar, Nav, Container} from 'react-bootstrap';
import {Link} from 'react-router-dom'

class NavigationBar extends React.Component{
    render(){
        return(
            <div>
                <Navbar bg="dark" variant="dark">
                    <Link to={""} className='navbar-brand'>
                        Learning Manager
                    </Link>
                    <Nav className="me-auto">
                        <Link to={"register"} className='nav-link'>Register</Link>
                        <Link to={"show"} className='nav-link'>Show Trainers</Link>
                    </Nav>
                </Navbar>
            </div>);
    }
}

export default NavigationBar;