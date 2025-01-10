const Navbar = () => {
  return (
    <nav className="navbar navbar-expand-lg">
      <div className="container">
        <div className="collapse navbar-collapse" id="navbarNav">
          <ul className="navbar-nav">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="#">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Features
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="#">
                Pricing
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" aria-disabled="true">
                Disabled
              </a>
            </li>
          </ul>
        </div>
        <div className="d-flex" role="search">
          <button className="btn btn-sm btn-bg-light">Login</button>
          <button className="btn btn-sm btn-bg-light">Logot</button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
