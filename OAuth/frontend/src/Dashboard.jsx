import { useEffect, useState } from 'react';
import axios from 'axios';
import { getToken, clearToken } from './auth';
import { useNavigate } from 'react-router-dom';

const Dashboard = () => {
  const [user, setUser] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = getToken();
    if (!token) {
      navigate('/');
      return;
    }

    axios
      .get('/api/me', {
        headers: { Authorization: `Bearer ${token}` }
      })
      .then((res) => setUser(res.data))
      .catch((err) => {
        console.error('Auth error:', err);
        clearToken();
        navigate('/');
      });
  }, [navigate]);

  return user ? (
    <div>
      <h1>Welcome, {user.name}</h1>
      <p>Email: {user.email}</p>
    </div>
  ) : (
    <p>Loading user...</p>
  );
};

export default Dashboard;
