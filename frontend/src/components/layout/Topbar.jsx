import { Bell, Search } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';
import { useNavigate } from 'react-router-dom';

export default function Topbar() {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <header className="h-16 bg-white border-b border-slate-200 flex items-center justify-between px-6 shrink-0">
      {/* Search */}
      <div className="flex items-center gap-2 bg-slate-100 rounded-lg px-3 py-2 w-72">
        <Search size={16} className="text-slate-400" />
        <input
          type="text"
          placeholder="Search patients, doctors..."
          className="bg-transparent text-sm text-slate-600 placeholder-slate-400 outline-none w-full"
        />
      </div>

      {/* Right side */}
      <div className="flex items-center gap-4">
        <button className="relative p-2 rounded-lg hover:bg-slate-100 transition-colors">
          <Bell size={20} className="text-slate-600" />
          <span className="absolute top-1.5 right-1.5 w-2 h-2 bg-red-500 rounded-full" />
        </button>

        <div className="flex items-center gap-3">
          <div className="w-8 h-8 bg-blue-600 rounded-full flex items-center justify-center text-white text-sm font-bold">
            {user?.username?.[0]?.toUpperCase()}
          </div>
          <div className="text-sm">
            <p className="font-medium text-slate-700">{user?.username}</p>
            <p className="text-xs text-slate-400">{user?.role}</p>
          </div>
          <button
            onClick={handleLogout}
            className="text-xs text-red-500 hover:text-red-700 font-medium ml-2"
          >
            Logout
          </button>
        </div>
      </div>
    </header>
  );
}