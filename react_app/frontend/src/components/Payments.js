import React, { useState } from 'react';
import axios from 'axios';
import { useCart } from '../contexts/CartContext';

const Payments = () => {
  const { cart, dispatch } = useCart();
  const [paymentData, setPaymentData] = useState({ cardNumber: '', expiry: '' });

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post('/api/payments', { cart, paymentData })
      .then(() => {
        dispatch({ type: 'CLEAR_CART' });
        alert('Payment Successful!');
      })
      .catch(err => console.error(err));
  };

  return (
    <div>
      <h2>Payment</h2>
      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Card Number"
          value={paymentData.cardNumber}
          onChange={(e) => setPaymentData({ ...paymentData, cardNumber: e.target.value })}
        />
        <input
          type="text"
          placeholder="Expiry Date"
          value={paymentData.expiry}
          onChange={(e) => setPaymentData({ ...paymentData, expiry: e.target.value })}
        />
        <button type="submit">Pay Now</button>
      </form>
    </div>
  );
};

export default Payments;