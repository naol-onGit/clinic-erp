import { useAuth } from '../../context/AuthContext';
import {
  Users,
  Calendar,
  Receipt,
  TrendingUp,
  Clock,
  CheckCircle,
  AlertCircle,
  Stethoscope,
} from 'lucide-react';

const stats = [
  {
    label: 'Total Patients',
    value: '1,284',
    change: '+12%',
    positive: true,
    icon: Users,
    color: 'bg-blue-50 text-blue-600',
    border: 'border-blue-100',
  },
  {
    label: 'Today\'s Appointments',
    value: '38',
    change: '+4',
    positive: true,
    icon: Calendar,
    color: 'bg-teal-50 text-teal-600',
    border: 'border-teal-100',
  },
  {
    label: 'Active Doctors',
    value: '12',
    change: 'On duty',
    positive: true,
    icon: Stethoscope,
    color: 'bg-violet-50 text-violet-600',
    border: 'border-violet-100',
  },
  {
    label: 'Unpaid Invoices',
    value: '23',
    change: 'Needs attention',
    positive: false,
    icon: Receipt,
    color: 'bg-amber-50 text-amber-600',
    border: 'border-amber-100',
  },
  {
    label: 'Revenue This Month',
    value: 'ETB 84,500',
    change: '+18%',
    positive: true,
    icon: TrendingUp,
    color: 'bg-green-50 text-green-600',
    border: 'border-green-100',
  },
];

const recentAppointments = [
  { id: 1, patient: 'Abebe Kebede', doctor: 'Dr. Tigist Haile', time: '09:00 AM', status: 'COMPLETED' },
  { id: 2, patient: 'Meron Alemu', doctor: 'Dr. Samuel Bekele', time: '10:30 AM', status: 'SCHEDULED' },
  { id: 3, patient: 'Dawit Tadesse', doctor: 'Dr. Tigist Haile', time: '11:00 AM', status: 'SCHEDULED' },
  { id: 4, patient: 'Hana Girma', doctor: 'Dr. Yonas Tesfaye', time: '02:00 PM', status: 'CANCELLED' },
  { id: 5, patient: 'Kebede Worku', doctor: 'Dr. Samuel Bekele', time: '03:30 PM', status: 'SCHEDULED' },
];

const statusConfig = {
  COMPLETED: { label: 'Completed', className: 'bg-green-100 text-green-700', icon: CheckCircle },
  SCHEDULED: { label: 'Scheduled', className: 'bg-blue-100 text-blue-700', icon: Clock },
  CANCELLED: { label: 'Cancelled', className: 'bg-red-100 text-red-700', icon: AlertCircle },
};

export default function Dashboard() {
  const { user } = useAuth();

  const greeting = () => {
    const hour = new Date().getHours();
    if (hour < 12) return 'Good Morning';
    if (hour < 17) return 'Good Afternoon';
    return 'Good Evening';
  };

  return (
    <div className="space-y-6">
      {/* Hero banner */}
      <div className="relative bg-[#0f2d5e] rounded-2xl p-8 overflow-hidden">
        {/* Background decoration */}
        <div className="absolute right-8 top-1/2 -translate-y-1/2 w-32 h-32 bg-blue-500 rounded-full opacity-10" />
        <div className="absolute right-20 top-4 w-16 h-16 bg-blue-400 rounded-full opacity-10" />

        <div className="relative z-10">
          <div className="inline-flex items-center gap-2 bg-green-500 bg-opacity-20 text-green-300 text-xs font-medium px-3 py-1 rounded-full mb-4">
            <span className="w-1.5 h-1.5 bg-green-400 rounded-full" />
            All systems operational
          </div>
          <h1 className="text-2xl font-bold text-white mb-1">
            {greeting()}, {user?.username} 👋
          </h1>
          <p className="text-blue-200 text-sm max-w-lg">
            Monitor patients, manage appointments, and track billing — all from your centralized clinic dashboard.
          </p>
          <div className="flex items-center gap-6 mt-6">
            <div className="flex items-center gap-2 text-blue-200 text-sm">
              <Calendar size={14} />
              <span>{new Date().toLocaleDateString('en-US', { weekday: 'long', year: 'numeric', month: 'long', day: 'numeric' })}</span>
            </div>
          </div>
        </div>
      </div>

      {/* Stats grid */}
      <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-5 gap-4">
        {stats.map(({ label, value, change, positive, icon: Icon, color, border }) => (
          <div
            key={label}
            className={`bg-white rounded-2xl p-5 border ${border} shadow-sm hover:shadow-md transition-shadow`}
          >
            <div className="flex items-center justify-between mb-3">
              <div className={`w-9 h-9 rounded-xl flex items-center justify-center ${color}`}>
                <Icon size={18} />
              </div>
              <span className={`text-xs font-medium ${positive ? 'text-green-600' : 'text-amber-600'}`}>
                {change}
              </span>
            </div>
            <p className="text-2xl font-bold text-slate-800 mb-0.5">{value}</p>
            <p className="text-xs text-slate-500">{label}</p>
          </div>
        ))}
      </div>

      {/* Recent appointments table */}
      <div className="bg-white rounded-2xl border border-slate-100 shadow-sm overflow-hidden">
        <div className="flex items-center justify-between px-6 py-4 border-b border-slate-100">
          <div>
            <h2 className="text-base font-semibold text-slate-800">Today's Appointments</h2>
            <p className="text-xs text-slate-400 mt-0.5">Live schedule for {new Date().toLocaleDateString()}</p>
          </div>
          <a
            href="/appointments"
            className="text-xs text-blue-600 font-medium hover:text-blue-700"
          >
            View all &rarr;
          </a>
        </div>

        <div className="overflow-x-auto">
          <table className="w-full">
            <thead>
              <tr className="bg-slate-50">
                <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">#</th>
                <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Patient</th>
                <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Doctor</th>
                <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Time</th>
                <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Status</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-slate-50">
              {recentAppointments.map((apt) => {
                const { label, className, icon: StatusIcon } = statusConfig[apt.status];
                return (
                  <tr key={apt.id} className="hover:bg-slate-50 transition-colors">
                    <td className="px-6 py-4 text-sm text-slate-400">{apt.id}</td>
                    <td className="px-6 py-4">
                      <div className="flex items-center gap-3">
                        <div className="w-8 h-8 bg-blue-100 text-blue-700 rounded-full flex items-center justify-center text-xs font-bold">
                          {apt.patient[0]}
                        </div>
                        <span className="text-sm font-medium text-slate-700">{apt.patient}</span>
                      </div>
                    </td>
                    <td className="px-6 py-4 text-sm text-slate-600">{apt.doctor}</td>
                    <td className="px-6 py-4 text-sm text-slate-600">{apt.time}</td>
                    <td className="px-6 py-4">
                      <span className={`inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-xs font-medium ${className}`}>
                        <StatusIcon size={11} />
                        {label}
                      </span>
                    </td>
                  </tr>
                );
              })}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}