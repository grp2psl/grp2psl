import React from 'react';

import {Container, Row, Card, Button} from 'react-bootstrap';

class Welcome extends React.Component{
    render(){
        return(
            <div>
                <Container>
                        <h1 className="text-white">Hello World</h1>
                </Container>
            </div>);
    }
}

export default Welcome;