import React from 'react';

import {Navbar, Nav, Container} from 'react-bootstrap';
import {Link} from 'react-router-dom'

class NavigationBar extends React.Component{
    render(){
        return(
			<div>
                <Navbar bg="dark" variant="dark">
					<Container>
    				<Link to={""} className='navbar-brand'>
                        Learning Manager
                    </Link>
               
                    <Nav className="me-auto">
                        <Link to={""} className='nav-link'>Home</Link>
                        <Link to={""} className='nav-link'>My Profile</Link>
                    </Nav>
                    <Nav>
                        <Link to={""} className='nav-link'>Logout</Link>
                    </Nav>
                    </Container>                
                </Navbar>
            </div>);
    }
}

export default NavigationBar;