import { combineReducers } from 'redux';
import { reducer as formReducer } from 'redux-form';

import userReducer from './user-reducer';

// Combine Reducers
const rootReducer = combineReducers({
	//currentUser: currentUser
	users: userReducer,
	form: formReducer
});

export default rootReducer;