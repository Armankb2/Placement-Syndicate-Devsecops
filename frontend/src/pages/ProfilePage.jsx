import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { getMyProfile } from "../services/api";
import "./ProfilePage.css";

export default function ProfilePage() {
  const [profile, setProfile] = useState(null);
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    getMyProfile()
      .then((res) => setProfile(res.data))
      .catch((err) => setError(err.response?.data?.message || err.message))
      .finally(() => setLoading(false));
  }, []);

  if (loading) return <div className="loader">Loading your workspace...</div>;
  if (error) return <div className="error-message">Error: {error}</div>;
  if (!profile) return <div className="error-message">No profile found.</div>;

  const initial = profile.firstname?.[0] || profile.email?.[0] || "P";
  const joinedDate = profile.createdDate
    ? new Date(profile.createdDate).toLocaleDateString()
    : "Not available";

  return (
    <div className="container animate-up">
      <div className="profile-dashboard glass">
        <header className="profile-header">
          <div className="avatar-large">{initial}</div>
          <div className="header-info">
            <h1>{profile.firstname} {profile.lastname}</h1>
            <p className="role-tag">{profile.role}</p>
          </div>
        </header>

        <div className="profile-grid">
          <section className="profile-section">
            <h3>Account Details</h3>
            <div className="detail-row">
              <span className="label">Email</span>
              <span className="value">{profile.email}</span>
            </div>
            <div className="detail-row">
              <span className="label">System ID</span>
              <span className="value">#{profile.id?.slice(-8) || "pending"}</span>
            </div>
            <div className="detail-row">
              <span className="label">Joined</span>
              <span className="value">{joinedDate}</span>
            </div>
          </section>

          <section className="profile-section contribution-panel">
            <h3>Contribution Hub</h3>
            <p>
              Keep your shared interview experiences current so juniors can prepare with real context.
            </p>
            <div className="profile-actions">
              <Link to="/my-experiences" className="btn profile-link">My Experiences</Link>
              <Link to="/experiences/new" className="btn btn-primary profile-link">Add Experience</Link>
            </div>
          </section>
        </div>
      </div>
    </div>
  );
}
