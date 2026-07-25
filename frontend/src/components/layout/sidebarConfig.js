export const navItems = [
  { to: '/dashboard', label: 'Dashboard', roles: ['ADMIN', 'DOCTOR', 'RECEPTIONIST'] },
  { to: '/patients', label: 'Patients', roles: ['ADMIN', 'RECEPTIONIST'] },
  { to: '/doctors', label: 'Doctors', roles: ['ADMIN'] },
  { to: '/appointments', label: 'Appointments', roles: ['ADMIN', 'DOCTOR', 'RECEPTIONIST'] },
  { to: '/billing', label: 'Billing', roles: ['ADMIN', 'RECEPTIONIST'] },
];