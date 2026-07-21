import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import { AuthProvider } from './context/AuthContext';
import ProtectedRoute from './routes/ProtectedRoute';
import Layout from './components/layout/Layout';
import Login from './pages/auth/Login';
import Dashboard from './pages/dashboard/Dashboard';
import Patients from './pages/patients/Patients';
import Doctors from './pages/doctors/Doctors';
import Appointments from './pages/appointments/Appointments';
import Billing from './pages/billing/Billing';

export default function App() {
  return (
    <AuthProvider>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Navigate to="/dashboard" replace />} />
          <Route
            path="/"
            element={
              <ProtectedRoute>
                <Layout />
              </ProtectedRoute>
            }
          >
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="patients" element={<Patients />} />
            <Route
              path="doctors"
              element={
                <ProtectedRoute allowedRoles={['ADMIN']}>
                  <Doctors />
                </ProtectedRoute>
              }
            />
            <Route path="appointments" element={<Appointments />} />
            <Route path="billing" element={<Billing />} />
          </Route>
        </Routes>
      </BrowserRouter>
    </AuthProvider>
  );
}