export const saveToken = (token) => localStorage.setItem('app_token', token);
export const getToken = () => localStorage.getItem('app_token');
export const clearToken = () => localStorage.removeItem('app_token');
