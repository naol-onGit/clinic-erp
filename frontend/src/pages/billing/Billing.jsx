import { useState, useEffect, useCallback } from 'react';
import api from '../../api/axios';
import { Plus, Search, Edit2, Trash2, X, Loader2, Receipt, CheckCircle, AlertCircle } from 'lucide-react';

const EMPTY_FORM = { patientId: '', appointmentId: '', totalAmount: '', status: 'UNPAID' };

const statusConfig = {
  PAID: { label: 'Paid', className: 'bg-green-100 text-green-700', icon: CheckCircle },
  UNPAID: { label: 'Unpaid', className: 'bg-amber-100 text-amber-700', icon: AlertCircle },
};

export default function Billing() {
  const [invoices, setInvoices] = useState([]);
  const [loading, setLoading] = useState(true);
  const [search, setSearch] = useState('');
  const [modalOpen, setModalOpen] = useState(false);
  const [editingId, setEditingId] = useState(null);
  const [form, setForm] = useState(EMPTY_FORM);
  const [formLoading, setFormLoading] = useState(false);
  const [formError, setFormError] = useState('');
  const [deleteId, setDeleteId] = useState(null);

  const fetchInvoices = useCallback(async () => {
    setLoading(true);
    try {
      const res = await api.get('/invoices');
      setInvoices(res.data);
    } catch (err) {
      console.error('Failed to fetch invoices', err);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => { fetchInvoices(); }, [fetchInvoices]);

  const filtered = invoices.filter(inv =>
    inv.status?.toLowerCase().includes(search.toLowerCase()) ||
    String(inv.patientId).includes(search)
  );

  const totalRevenue = invoices
    .filter(inv => inv.status === 'PAID')
    .reduce((sum, inv) => sum + (inv.totalAmount || 0), 0);

  const unpaidCount = invoices.filter(inv => inv.status === 'UNPAID').length;

  const openCreate = () => {
    setEditingId(null);
    setForm(EMPTY_FORM);
    setFormError('');
    setModalOpen(true);
  };

  const openEdit = (inv) => {
    setEditingId(inv.id);
    setForm({
      patientId: inv.patientId || '',
      appointmentId: inv.appointmentId || '',
      totalAmount: inv.totalAmount || '',
      status: inv.status || 'UNPAID',
    });
    setFormError('');
    setModalOpen(true);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setFormLoading(true);
    setFormError('');
    try {
      const payload = {
        ...form,
        patientId: Number(form.patientId),
        appointmentId: form.appointmentId ? Number(form.appointmentId) : null,
        totalAmount: form.totalAmount ? Number(form.totalAmount) : null,
      };
      if (editingId) {
        await api.put(`/invoices/${editingId}`, payload);
      } else {
        await api.post('/invoices', payload);
      }
      setModalOpen(false);
      fetchInvoices();
    } catch (err) {
      setFormError(err.response?.data?.error || 'Something went wrong');
    } finally {
      setFormLoading(false);
    }
  };

  const handleDelete = async (id) => {
    try {
      await api.delete(`/invoices/${id}`);
      setDeleteId(null);
      fetchInvoices();
    } catch (err) {
      console.error('Failed to delete invoice', err);
    }
  };

  const handleMarkPaid = async (inv) => {
    try {
      await api.put(`/invoices/${inv.id}`, { ...inv, status: 'PAID' });
      fetchInvoices();
    } catch (err) {
      console.error('Failed to mark as paid', err);
    }
  };

  return (
    <div className="space-y-5">
      {/* Header */}
      <div className="flex items-center justify-between">
        <div>
          <h1 className="text-xl font-bold text-slate-800">Billing</h1>
          <p className="text-sm text-slate-500 mt-0.5">{invoices.length} invoices total</p>
        </div>
        <button
          onClick={openCreate}
          className="flex items-center gap-2 bg-blue-600 hover:bg-blue-700 text-white text-sm font-medium px-4 py-2.5 rounded-xl transition-colors"
        >
          <Plus size={16} />
          New Invoice
        </button>
      </div>

      {/* Summary cards */}
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-4">
        <div className="bg-white rounded-2xl border border-green-100 p-5 shadow-sm">
          <p className="text-xs text-slate-500 mb-1">Total Revenue (Paid)</p>
          <p className="text-2xl font-bold text-slate-800">ETB {totalRevenue.toLocaleString()}</p>
        </div>
        <div className="bg-white rounded-2xl border border-amber-100 p-5 shadow-sm">
          <p className="text-xs text-slate-500 mb-1">Unpaid Invoices</p>
          <p className="text-2xl font-bold text-amber-600">{unpaidCount}</p>
        </div>
        <div className="bg-white rounded-2xl border border-blue-100 p-5 shadow-sm">
          <p className="text-xs text-slate-500 mb-1">Total Invoices</p>
          <p className="text-2xl font-bold text-slate-800">{invoices.length}</p>
        </div>
      </div>

      {/* Search */}
      <div className="bg-white rounded-2xl border border-slate-100 shadow-sm p-4">
        <div className="flex items-center gap-2 bg-slate-50 rounded-xl px-3 py-2.5 max-w-sm">
          <Search size={16} className="text-slate-400 shrink-0" />
          <input
            type="text"
            placeholder="Search by status or patient ID..."
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            className="bg-transparent text-sm text-slate-600 placeholder-slate-400 outline-none w-full"
          />
          {search && (
            <button onClick={() => setSearch('')}>
              <X size={14} className="text-slate-400 hover:text-slate-600" />
            </button>
          )}
        </div>
      </div>

      {/* Table */}
      <div className="bg-white rounded-2xl border border-slate-100 shadow-sm overflow-hidden">
        {loading ? (
          <div className="flex items-center justify-center py-20">
            <Loader2 size={24} className="animate-spin text-blue-600" />
          </div>
        ) : filtered.length === 0 ? (
          <div className="flex flex-col items-center justify-center py-20 text-slate-400">
            <Receipt size={40} className="mb-3 opacity-30" />
            <p className="text-sm font-medium">No invoices found</p>
          </div>
        ) : (
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead>
                <tr className="bg-slate-50 border-b border-slate-100">
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">#</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Patient ID</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Appointment ID</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Amount</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Status</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Created</th>
                  <th className="text-left text-xs font-semibold text-slate-500 px-6 py-3">Actions</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-slate-50">
                {filtered.map((inv) => {
                  const { label, className, icon: StatusIcon } = statusConfig[inv.status] || statusConfig.UNPAID;
                  return (
                    <tr key={inv.id} className="hover:bg-slate-50 transition-colors">
                      <td className="px-6 py-4 text-sm text-slate-400">{inv.id}</td>
                      <td className="px-6 py-4">
                        <span className="px-2 py-1 bg-blue-50 text-blue-700 text-xs font-medium rounded-lg">
                          Patient #{inv.patientId}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-sm text-slate-500">
                        {inv.appointmentId ? `#${inv.appointmentId}` : '—'}
                      </td>
                      <td className="px-6 py-4">
                        <span className="text-sm font-semibold text-slate-700">
                          ETB {inv.totalAmount?.toLocaleString() || '—'}
                        </span>
                      </td>
                      <td className="px-6 py-4">
                        <span className={`inline-flex items-center gap-1.5 px-2.5 py-1 rounded-full text-xs font-medium ${className}`}>
                          <StatusIcon size={11} />
                          {label}
                        </span>
                      </td>
                      <td className="px-6 py-4 text-sm text-slate-500">
                        {inv.createdAt ? new Date(inv.createdAt).toLocaleDateString() : '—'}
                      </td>
                      <td className="px-6 py-4">
                        <div className="flex items-center gap-2">
                          {inv.status === 'UNPAID' && (
                            <button
                              onClick={() => handleMarkPaid(inv)}
                              className="px-2.5 py-1 bg-green-50 text-green-700 hover:bg-green-100 text-xs font-medium rounded-lg transition-colors"
                            >
                              Mark Paid
                            </button>
                          )}
                          <button
                            onClick={() => openEdit(inv)}
                            className="p-1.5 text-slate-400 hover:text-blue-600 hover:bg-blue-50 rounded-lg transition-colors"
                          >
                            <Edit2 size={15} />
                          </button>
                          <button
                            onClick={() => setDeleteId(inv.id)}
                            className="p-1.5 text-slate-400 hover:text-red-600 hover:bg-red-50 rounded-lg transition-colors"
                          >
                            <Trash2 size={15} />
                          </button>
                        </div>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {/* Modal */}
      {modalOpen && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
          <div className="absolute inset-0 bg-black bg-opacity-40 backdrop-blur-sm" onClick={() => setModalOpen(false)} />
          <div className="relative bg-white rounded-2xl shadow-2xl w-full max-w-md">
            <div className="flex items-center justify-between px-6 py-4 border-b border-slate-100">
              <h2 className="text-base font-semibold text-slate-800">
                {editingId ? 'Edit Invoice' : 'New Invoice'}
              </h2>
              <button onClick={() => setModalOpen(false)} className="p-1.5 text-slate-400 hover:text-slate-600 hover:bg-slate-100 rounded-lg">
                <X size={18} />
              </button>
            </div>
            <form onSubmit={handleSubmit} className="p-6 space-y-4">
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1.5">Patient ID <span className="text-red-500">*</span></label>
                  <input
                    type="number"
                    value={form.patientId}
                    onChange={(e) => setForm({ ...form, patientId: e.target.value })}
                    placeholder="e.g. 1"
                    required
                    className="w-full px-3.5 py-2.5 rounded-xl border border-slate-200 text-sm text-slate-700 placeholder-slate-400 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all"
                  />
                </div>
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1.5">Appointment ID</label>
                  <input
                    type="number"
                    value={form.appointmentId}
                    onChange={(e) => setForm({ ...form, appointmentId: e.target.value })}
                    placeholder="Optional"
                    className="w-full px-3.5 py-2.5 rounded-xl border border-slate-200 text-sm text-slate-700 placeholder-slate-400 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all"
                  />
                </div>
              </div>
              <div className="grid grid-cols-2 gap-3">
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1.5">Amount (ETB)</label>
                  <input
                    type="number"
                    step="0.01"
                    value={form.totalAmount}
                    onChange={(e) => setForm({ ...form, totalAmount: e.target.value })}
                    placeholder="e.g. 500.00"
                    className="w-full px-3.5 py-2.5 rounded-xl border border-slate-200 text-sm text-slate-700 placeholder-slate-400 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all"
                  />
                </div>
                <div>
                  <label className="block text-xs font-semibold text-slate-600 mb-1.5">Status</label>
                  <select
                    value={form.status}
                    onChange={(e) => setForm({ ...form, status: e.target.value })}
                    className="w-full px-3.5 py-2.5 rounded-xl border border-slate-200 text-sm text-slate-700 outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all bg-white"
                  >
                    <option value="UNPAID">Unpaid</option>
                    <option value="PAID">Paid</option>
                  </select>
                </div>
              </div>
              {formError && (
                <div className="bg-red-50 border border-red-200 text-red-600 text-sm px-4 py-3 rounded-xl">{formError}</div>
              )}
              <div className="flex gap-3 pt-2">
                <button type="button" onClick={() => setModalOpen(false)} className="flex-1 py-2.5 border border-slate-200 text-slate-600 text-sm font-medium rounded-xl hover:bg-slate-50 transition-colors">Cancel</button>
                <button type="submit" disabled={formLoading} className="flex-1 py-2.5 bg-blue-600 hover:bg-blue-700 disabled:bg-blue-400 text-white text-sm font-medium rounded-xl transition-colors flex items-center justify-center gap-2">
                  {formLoading ? <><Loader2 size={15} className="animate-spin" /> Saving...</> : editingId ? 'Update' : 'Create'}
                </button>
              </div>
            </form>
          </div>
        </div>
      )}

      {/* Delete confirmation */}
      {deleteId && (
        <div className="fixed inset-0 z-50 flex items-center justify-center p-4">
          <div className="absolute inset-0 bg-black bg-opacity-40 backdrop-blur-sm" onClick={() => setDeleteId(null)} />
          <div className="relative bg-white rounded-2xl shadow-2xl w-full max-w-sm p-6">
            <div className="w-12 h-12 bg-red-100 rounded-2xl flex items-center justify-center mb-4">
              <Trash2 size={22} className="text-red-600" />
            </div>
            <h3 className="text-base font-semibold text-slate-800 mb-1">Delete Invoice</h3>
            <p className="text-sm text-slate-500 mb-6">Are you sure? This action cannot be undone.</p>
            <div className="flex gap-3">
              <button onClick={() => setDeleteId(null)} className="flex-1 py-2.5 border border-slate-200 text-slate-600 text-sm font-medium rounded-xl hover:bg-slate-50 transition-colors">Cancel</button>
              <button onClick={() => handleDelete(deleteId)} className="flex-1 py-2.5 bg-red-600 hover:bg-red-700 text-white text-sm font-medium rounded-xl transition-colors">Delete</button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}