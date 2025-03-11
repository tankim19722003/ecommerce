import { SET_SHOP_INFO, UPDATE_SHOP_INFO } from "../../contexts/User/contants";

export const shopReducer = (state, action) => {
  const { type, payload } = action;
  switch (type) {
    case SET_SHOP_INFO:
      return {
        ...state,
        shopLoading: false,
        statusShop: payload.statusShop,
        shopInfo: payload.shopInfo,
      };
    case UPDATE_SHOP_INFO:
      return {
        ...state,
        shopInfo: {
          ...state.shopInfo,
          ...payload.shopInfo,
        },
      };
    default:
      return state;
  }
};
