import { useEffect } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import axios from 'axios';
import { saveToken } from './auth';

const AuthCallback = () => {
  const navigate = useNavigate();
  const location = useLocation();

  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const code = params.get('code');

    if (code) {
      axios
        .post('/auth/handle-callback', { code })
        .then((res) => {
          saveToken(res.data.token);
          navigate('/dashboard');
        })
        .catch((err) => {
          console.error('Login failed:', err);
          navigate('/');
        });
    }
  }, [location, navigate]);

  return <div>Logging in...</div>;
};

export default AuthCallback;
