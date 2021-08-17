import React from 'react';

import { TRAINER_URL, MANAGER_URL, LEARNER_URL } from '../constants';

const cardStyle={
	height: '150px',
	marginTop: '20px'
};

const navStyle={
	width: '100%'
};

const buttonStyle={
	width: '40%',
    position: 'absolute',
    bottom: '20px',
    left: '30%'
};

class Home extends React.Component{
    constructor(props){
        super(props);
    }
	render(){
        if(localStorage.getItem('user') === 'manager'){
            return(
                <div>
                    {
                    this.props.history.push(MANAGER_URL+"/")
                    }
                </div>
            );
        }
        else if(localStorage.getItem('user') === 'trainer'){
            return(
                <div>
                {
                this.props.history.push(TRAINER_URL+"/welcometrainer")
                }
                </div>
            );
        }
        else if(localStorage.getItem('user') === 'learner'){
            return(
                <div>
                {
                this.props.history.push(LEARNER_URL+"/")
                }
                </div>
            );       
        }
        else{
            return(
                <div>
                {
                this.props.history.push("/")
                }
                </div>
            )
        }
    }
}

export default Home;