import { useState } from "react";
import { registerUser, getUserById } from "../services/api";
import "./AdminPage.css";

export default function AdminPage() {
  // ─── Register User ───────────────────────────
  const [regForm, setRegForm] = useState({
    firstname: "",
    lastname: "",
    email: "",
    password: "",
    year: new Date().getFullYear(),
  });
  const [regResult, setRegResult] = useState(null);
  const [regError, setRegError] = useState(null);

  const handleRegChange = (e) =>
    setRegForm({ ...regForm, [e.target.name]: e.target.value });

  const handleRegister = (e) => {
    e.preventDefault();
    setRegError(null);
    setRegResult(null);
    registerUser({ ...regForm, year: parseInt(regForm.year, 10) })
      .then((res) => setRegResult(res.data))
      .catch((err) => setRegError(err.response?.data?.message || err.message));
  };

  // ─── Lookup User ─────────────────────────────
  const [lookupId, setLookupId] = useState("");
  const [lookupResult, setLookupResult] = useState(null);
  const [lookupError, setLookupError] = useState(null);

  const handleLookup = (e) => {
    e.preventDefault();
    setLookupError(null);
    setLookupResult(null);
    getUserById(lookupId)
      .then((res) => setLookupResult(res.data))
      .catch((err) => setLookupError(err.response?.data?.message || err.message));
  };

  return (
    <div className="container animate-up">
      <div className="page-header">
        <h1 className="page-title">Admin <span>Panel</span></h1>
        <p className="page-subtitle">Create users and inspect account records from one focused workspace.</p>
      </div>

      <div className="admin-grid">
        <section className="admin-panel glass">
          <h2>Register User</h2>
          {regError && <p className="error-message">{regError}</p>}
          {regResult && (
            <p className="success-message">
              User created: {regResult.firstname} {regResult.lastname} (ID: {regResult.id})
            </p>
          )}
          <form onSubmit={handleRegister} className="admin-form">
            <div className="admin-form-grid">
              <div className="input-group">
                <label>First Name</label>
                <input name="firstname" value={regForm.firstname} onChange={handleRegChange} required />
              </div>
              <div className="input-group">
                <label>Last Name</label>
                <input name="lastname" value={regForm.lastname} onChange={handleRegChange} required />
              </div>
            </div>
            <div className="input-group">
              <label>Email</label>
              <input name="email" type="email" value={regForm.email} onChange={handleRegChange} required />
            </div>
            <div className="admin-form-grid">
              <div className="input-group">
                <label>Password</label>
                <input name="password" type="password" value={regForm.password} onChange={handleRegChange} required />
              </div>
              <div className="input-group">
                <label>Year</label>
                <input name="year" type="number" value={regForm.year} onChange={handleRegChange} required />
              </div>
            </div>
            <button type="submit" className="btn-primary">Register User</button>
          </form>
        </section>

        <section className="admin-panel glass">
          <h2>Lookup User</h2>
          {lookupError && <p className="error-message">{lookupError}</p>}
          <form onSubmit={handleLookup} className="admin-form">
            <div className="input-group">
              <label>User ID</label>
              <input value={lookupId} onChange={(e) => setLookupId(e.target.value)} required />
            </div>
            <button type="submit">Lookup</button>
          </form>

          {lookupResult && (
            <div className="lookup-card">
              <div><span>ID</span><strong>{lookupResult.id}</strong></div>
              <div><span>First Name</span><strong>{lookupResult.firstname}</strong></div>
              <div><span>Last Name</span><strong>{lookupResult.lastname}</strong></div>
              <div><span>Email</span><strong>{lookupResult.email}</strong></div>
              <div><span>Role</span><strong>{lookupResult.role}</strong></div>
              <div><span>Keycloak ID</span><strong>{lookupResult.keyCloakId}</strong></div>
              <div><span>Created</span><strong>{lookupResult.createdDate || "Not available"}</strong></div>
            </div>
          )}
        </section>
      </div>
    </div>
  );
}
