import { useNavigate } from 'react-router-dom';

const LoginSuccess = () => {
  const navigate = useNavigate();

  return (
    <div>
      <h2>Login Successful!</h2>
      <p>You have been logged in successfully.</p>
      <button onClick={() => navigate('/dashboard')}>Go to Dashboard</button>
    </div>
  );
};

export default LoginSuccess;