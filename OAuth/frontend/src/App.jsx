import { BrowserRouter, Routes, Route } from 'react-router-dom';
import LoginPage from './LoginPage.jsx';
import AuthCallback from './AuthCallback.jsx';
import Dashboard from './Dashboard.jsx';
import LoginSuccess from './LoginSuccess.jsx';
import OAuthCallback from './OAuthCallback.jsx';


function App() {
  return (
    <BrowserRouter>
      <h1>App Loaded</h1>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/auth/callback" element={<AuthCallback />} />
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/login-success" element={<LoginSuccess />} />
        <Route path="/oauth-callback" element={<OAuthCallback />} />
        <Route path="*" element={<div>404 Not Found</div>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;