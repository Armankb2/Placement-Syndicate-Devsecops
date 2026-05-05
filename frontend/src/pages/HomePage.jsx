import { Link } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import "./HomePage.css";

export default function HomePage() {
  const { user } = useAuth();

  return (
    <div className="container animate-up">
      <header className="hero-section">
        <h1 className="hero-title">Welcome to the <span>Syndicate</span></h1>
        <p className="hero-subtitle">
          Hello <strong>{user?.name}</strong>. Your professional journey continues here. 
          Browse real interview stories, share your own process, and help the next candidate walk in prepared.
        </p>
      </header>

      <div className="bento-grid">
        <Link to="/experiences" className="bento-card glass glass-hover large-card">
          <div className="card-content">
            <span className="card-kicker">Explore</span>
            <h3>Company-wise interview intelligence</h3>
            <p>Scan experiences by company, role, year, round structure, difficulty, questions, and practical tips.</p>
          </div>
        </Link>

        <Link to="/experiences/new" className="bento-card glass glass-hover accent-card">
          <div className="card-content">
            <span className="card-kicker">Contribute</span>
            <h3>Add Experience</h3>
            <p>Document every round while the details are still fresh.</p>
          </div>
        </Link>

        <Link to="/my-experiences" className="bento-card glass glass-hover">
          <div className="card-content">
            <span className="card-kicker">Manage</span>
            <h3>My Experiences</h3>
            <p>Review your contributions and remove outdated entries.</p>
          </div>
        </Link>

        <Link to="/profile" className="bento-card glass glass-hover">
          <div className="card-content">
            <span className="card-kicker">Account</span>
            <h3>Profile Dashboard</h3>
            <p>Check your profile, role, and account metadata.</p>
          </div>
        </Link>
      </div>
    </div>
  );
}
