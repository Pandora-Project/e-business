import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import { CartProvider } from './contexts/CartContext';
import Products from './components/Products';
import Cart from './components/Cart';
import Payments from './components/Payments';
import 'bootstrap/dist/css/bootstrap.min.css';

const App = () => {
  return (
    <CartProvider>
      <Router>
        <nav>
          <Link to="/products">Products</Link> | 
          <Link to="/cart">Cart</Link> | 
          <Link to="/payments">Payments</Link>
        </nav>
        <Routes>
          <Route path="/products" element={<Products />} />
          <Route path="/cart" element={<Cart />} />
          <Route path="/payments" element={<Payments />} />
          <Route path="/" element={<Products />} />
        </Routes>
      </Router>
    </CartProvider>
  );
};

export default App;