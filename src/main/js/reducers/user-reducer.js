import {
	REGISTER_USER_REQUEST, 
	REGISTER_USER_SUCCESS, 
	REGISTER_USER_FAILURE, 
	LOGIN_USER_REQUEST,
	LOGIN_USER_SUCCESS,
	LOGIN_USER_FAILURE 
} from '../actions/action-types';

const INITIAL_STATE = {
	status: null,
	token: null,
	message: null,
	error: null,
	loading: false
};

var err;

export default function(state = INITIAL_STATE, action) {
	switch (action.type) {
		case LOGIN_USER_REQUEST:
		case REGISTER_USER_REQUEST:
			return Object.assign(
				{},
				state,
				{
					status: null,
					token: null,
					message: null,
					error: false,
					loading: true
				}
			);

		case LOGIN_USER_FAILURE:
		case REGISTER_USER_FAILURE:
			return Object.assign(
				{},
				state,
				{
					status: action.payload.status,
					token: null,
					message: action.payload.err,
					error: true,
					loading: false
				}
			);   

		case REGISTER_USER_SUCCESS:
			return Object.assign(
				{},
				state,
				{
					status: action.payload.status,
					token: null,
					message: action.payload.response,
					error: false,
					loading: true
				}
			);

		case LOGIN_USER_SUCCESS:
			return Object.assign(
				{},
				state,
				{
					status: action.payload.status,
					token: action.payload.token,
					message: null,
					error: false,
					loading: true
				}
			);

		default:
			return state; 
	}
}