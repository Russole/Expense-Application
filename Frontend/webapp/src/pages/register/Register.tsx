import profileValidationSchema from "../../validation/profileValidationSchema";

const Register = () => {
  return (
    <div className="d-flex justify-content-center align-items-center login-background">
      <div className="container col-md-4 col-sm-12">
        <form>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Name
            </label>
            <input
              type="text"
              className="form-control"
              id="name"
              placeholder="Enter your name"
              name="name"
            />
            <div className="text-danger fst-italic">Name is required</div>
          </div>
          <div className="mb-3">
            <label htmlFor="email" className="form-label">
              E-mail
            </label>
            <input
              type="email"
              className="form-control"
              id="email"
              placeholder="Enter your email"
              name="email"
            />
            <div className="text-danger fst-italic">E-mail is required</div>
          </div>
          <div className="mb-3">
            <label htmlFor="password" className="form-label">
              Password
            </label>
            <input
              type="password"
              className="form-control"
              id="password"
              placeholder="Enter password"
              name="password"
            />
            <div className="text-danger fst-italic">Password is required</div>
          </div>
          <div className="mb-3">
            <label htmlFor="retypePassword" className="form-label">
              Retype Password
            </label>
            <input
              type="password"
              className="form-control"
              id="retypePassword"
              placeholder="Confirm password"
              name="retypePassword"
            />
            <div className="text-danger fst-italic">Password did not match</div>
          </div>
          <button
            className="btn btn-sm btn-primary btn-outline-light mx-1"
            type="submit"
          >
            Register
          </button>
        </form>
      </div>
    </div>
  );
};

export default Register;
