import { NavLink } from 'react-router-dom';
import {
  LayoutDashboard,
  Users,
  Stethoscope,
  Calendar,
  Receipt,
  Settings,
  ChevronRight,
  Cross,
} from 'lucide-react';
import { useAuth } from '../../context/AuthContext';

const navItems = [
  { to: '/dashboard', icon: LayoutDashboard, label: 'Dashboard', roles: ['ADMIN', 'DOCTOR', 'RECEPTIONIST'] },
  { to: '/patients', icon: Users, label: 'Patients', roles: ['ADMIN', 'RECEPTIONIST'] },
  { to: '/doctors', icon: Stethoscope, label: 'Doctors', roles: ['ADMIN'] },
  { to: '/appointments', icon: Calendar, label: 'Appointments', roles: ['ADMIN', 'DOCTOR', 'RECEPTIONIST'] },
  { to: '/billing', icon: Receipt, label: 'Billing', roles: ['ADMIN', 'RECEPTIONIST'] },
];

export default function Sidebar({ expanded, setExpanded }) {
  const { user } = useAuth();

  const visibleItems = navItems.filter(item =>
    item.roles.includes(user?.role)
  );

  return (
    <aside
      onMouseEnter={() => setExpanded(true)}
      onMouseLeave={() => setExpanded(false)}
      className={`
        flex flex-col h-full bg-[#0f2d5e] text-white
        transition-all duration-300 ease-in-out
        ${expanded ? 'w-56' : 'w-16'}
        shrink-0 overflow-hidden
      `}
    >
      {/* Logo */}
      <div className="flex items-center gap-3 px-4 py-5 border-b border-blue-800">
        <div className="w-8 h-8 bg-blue-500 rounded-lg flex items-center justify-center shrink-0">
          <Cross size={16} className="text-white" />
        </div>
        {expanded && (
          <div className="overflow-hidden">
            <p className="text-sm font-bold text-white whitespace-nowrap">Clinic ERP</p>
            <p className="text-xs text-blue-300 whitespace-nowrap">Management System</p>
          </div>
        )}
      </div>

      {/* Nav items */}
      <nav className="flex-1 py-4 space-y-1 px-2">
        {visibleItems.map(({ to, icon: Icon, label }) => (
          <NavLink
            key={to}
            to={to}
            className={({ isActive }) => `
              flex items-center gap-3 px-2 py-2.5 rounded-lg
              transition-all duration-150
              ${isActive
                ? 'bg-blue-600 text-white'
                : 'text-blue-200 hover:bg-[#1a3f7a] hover:text-white'
              }
            `}
          >
            <Icon size={20} className="shrink-0" />
            {expanded && (
              <span className="text-sm font-medium whitespace-nowrap overflow-hidden">
                {label}
              </span>
            )}
          </NavLink>
        ))}
      </nav>

      {/* User info at bottom */}
      <div className="px-2 py-4 border-t border-blue-800">
        <div className="flex items-center gap-3 px-2 py-2">
          <div className="w-8 h-8 bg-blue-500 rounded-full flex items-center justify-center shrink-0 text-sm font-bold">
            {user?.username?.[0]?.toUpperCase()}
          </div>
          {expanded && (
            <div className="overflow-hidden">
              <p className="text-sm font-medium text-white whitespace-nowrap">{user?.username}</p>
              <p className="text-xs text-blue-300 whitespace-nowrap">{user?.role}</p>
            </div>
          )}
        </div>
      </div>
    </aside>
  );
}