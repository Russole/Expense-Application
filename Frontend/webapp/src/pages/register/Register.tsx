import profileValidationSchema from "../../validation/profileValidationSchema";
import { Profile } from "../../model/Profile";
import { useFormik } from "formik";
import { useRegister } from "../../hooks/useRegister";

const Register = () => {
  const { error, isLoading, signup, toast } = useRegister();
  const formik = useFormik<Profile>({
    initialValues: {
      name: "",
      email: "",
      password: "",
      confirmPassword: "",
    },
    validationSchema: profileValidationSchema,
    onSubmit: (profile: Profile, { resetForm }) => {
      signup(profile);
      resetForm();
    },
  });
  return (
    <div className="d-flex justify-content-center align-items-center login-background">
      <div className="container col-md-4 col-sm-12">
        {isLoading && <p>Loading...</p>}
        {error && <p className="text-danger">{error}</p>}
        {toast && <p className="text-primary">{toast}</p>}
        <form onSubmit={formik.handleSubmit}>
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
              value={formik.values.name}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
            />
            {formik.touched.name && formik.errors.name ? (
              <div className="text-danger fst-italic">{formik.errors.name}</div>
            ) : null}
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
              value={formik.values.email}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
            />
            {formik.touched.email && formik.errors.email ? (
              <div className="text-danger fst-italic">
                {formik.errors.email}
              </div>
            ) : null}
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
              value={formik.values.password}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
            />
            {formik.touched.password && formik.errors.password ? (
              <div className="text-danger fst-italic">
                {formik.errors.password}
              </div>
            ) : null}
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
              name="confirmPassword"
              value={formik.values.confirmPassword}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
            />
            {formik.touched.confirmPassword && formik.errors.confirmPassword ? (
              <div className="text-danger fst-italic">
                {formik.errors.confirmPassword}
              </div>
            ) : null}
          </div>
          {isLoading && (
            <button
              className="btn btn-sm app-primary-bg-color btn-outline-light"
              type="submit"
              disabled
            >
              Loading...
            </button>
          )}
          {!isLoading && (
            <button
              className="btn btn-sm app-primary-bg-color btn-outline-lightbtn btn-sm app-primary-bg-color btn-outline-light"
              type="submit"
            >
              Register
            </button>
          )}
          <button
            className="btn btn-sm app-primary-bg-color btn-outline-light"
            type="reset"
            onClick={formik.handleReset}
          >
            Reset
          </button>
        </form>
      </div>
    </div>
  );
};

export default Register;
