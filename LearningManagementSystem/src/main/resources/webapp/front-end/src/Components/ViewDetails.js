import React from 'react';

import { TRAINER_URL, MANAGER_URL, LEARNER_URL } from '../constants';

class ViewDetails extends React.Component{
    
	render(){
        if(localStorage.getItem('user') === 'manager'){
            return(
                <div>
                {
                this.props.history.push(MANAGER_URL+"/view-details")
                }
                </div>
            );
        }
        else if(localStorage.getItem('user') === 'trainer'){
            return(
                <div>
                {
                this.props.history.push(TRAINER_URL+"/view-details")
                }
                </div>
            );
        }
        else if(localStorage.getItem('user') === 'learner'){
            return(
                <div>
                {
                this.props.history.push(LEARNER_URL+"/view-details")
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

export default ViewDetails;