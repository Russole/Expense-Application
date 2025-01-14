import { FaBars } from "react-icons/fa";
import Logo from "./Logo";
import { NavLink } from "react-router-dom";
import { useAuthContext } from "../hooks/useAuthContext";

const Navbar = () => {
  const { isAuthenticated, updateAuth } = useAuthContext();

  const handleLogout = () => {
    localStorage.clear();
    updateAuth(false);
  };
  return (
    <nav className="navbar navbar-expand-lg">
      <div className="container">
        <Logo />
        <div className="collapse navbar-collapse" id="navbarNav">
          {isAuthenticated ? (
            <div className="navbar-nav">
              <NavLink className="nav-link" to="/">
                Dashboard
              </NavLink>

              <NavLink className="nav-link" to="/new">
                New Expense
              </NavLink>

              <NavLink className="nav-link" to="/reports">
                Reports
              </NavLink>
            </div>
          ) : null}
        </div>
        <div className="d-flex" role="search">
          {!isAuthenticated ? (
            <>
              <NavLink className="btn btn-sm btn-outline-light" to="/login">
                Login
              </NavLink>
              <NavLink
                className="btn btn-sm btn-outline-light mx-1"
                to="/register"
              >
                Register
              </NavLink>
            </>
          ) : null}
          {isAuthenticated ? (
            <button
              className="btn btn-sm app-primary-bg-color btn outline-light"
              onClick={handleLogout}
            >
              Logout
            </button>
          ) : null}
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarNav"
            aria-controls="navbarNav"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <FaBars color="white" />
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
