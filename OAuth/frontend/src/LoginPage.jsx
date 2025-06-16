const LoginPage = () => {
  const handleLogin = (provider) => {
    window.location.href = `api/auth/${provider}`;
  };

  return (
    <div>
      <h2>Login</h2>
      <button onClick={() => handleLogin('google')}>Login with Google</button>
      <button onClick={() => handleLogin('github')}>Login with GitHub</button>
      <button onClick={() => handleLogin('facebook')}>Login with Facebook</button>
    </div>
  );
};

export default LoginPage;
