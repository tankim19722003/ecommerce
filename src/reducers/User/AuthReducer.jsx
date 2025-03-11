import { SET_AUTH, UPDATE_AUTH } from "../../contexts/User/contants";

export const authReducer = (state, action) => {
  const { type, payload } = action;
  switch (type) {
    case SET_AUTH:
      return {
        ...state,
        authLoading: false,
        isAuthenticated: payload.isAuthenticated,
        user: payload.user,
      };
    case UPDATE_AUTH:
      return {
        ...state,
        user: {
          ...state.user,
          ...payload,
        },
      };
    default:
      return state;
  }
};
