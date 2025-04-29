import React from 'react';
import { useCart } from '../contexts/CartContext';
import { Link } from 'react-router-dom';

const Cart = () => {
  const { cart, dispatch } = useCart();

  return (
    <div>
      <h2>Cart</h2>
      <ul>
        {cart.map((item, index) => (
          <li key={index}>
            {item.name} - ${item.price}
            <button onClick={() => dispatch({ type: 'REMOVE_ITEM', index })}>
              Remove
            </button>
          </li>
        ))}
      </ul>
      <Link to="/payments">Proceed to Payment</Link>
    </div>
  );
};

export default Cart;