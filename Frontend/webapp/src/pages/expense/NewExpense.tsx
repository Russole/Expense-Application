import { useFormik } from "formik";
import split from "./../../../node_modules/lodash-es/split";
import { Expense } from "../../model/Expense";
import * as Yup from "yup";

const expenseValidationSchema = Yup.object({
  name: Yup.string().required("Expense name is required"),
});

const NewExpense = () => {
  const formik = useFormik({
    initialValues: {
      name: "",
      amount: 0,
      note: "",
      category: "",
      date: new Date().toISOString().split("T")[0],
    },
    onSubmit: (values: Expense) => {
      console.log(values);
    },
    validationSchema: expenseValidationSchema,
  });
  return (
    <div className="d-flex justify-content-center align-items-center mt-2">
      <div className="container col-md-4 col-sm-8 col-xs-12">
        <form onSubmit={formik.handleSubmit}>
          <div className="mb-3">
            <label htmlFor="name" className="form-label">
              Name
            </label>
            <input
              type="text"
              id="name"
              name="name"
              className="form-control"
              value={formik.values.name}
              onChange={formik.handleChange}
              onBlur={formik.handleBlur}
            />
            {formik.touched.name && formik.errors.name ? (
              <div className="text-danger fst-italic">{formik.errors.name}</div>
            ) : null}
          </div>
          <div className="mb-3">
            <label htmlFor="amount" className="form-label">
              Amount
            </label>
            <input
              type="number"
              id="amount"
              name="amount"
              className="form-control"
              value={formik.values.amount}
              onChange={formik.handleChange}
            />

            <div className="text-danger fst-italic">
              Expense amount is required
            </div>
          </div>
          <div className="mb-3">
            <label htmlFor="note" className="form-label">
              Note
            </label>
            <textarea
              id="note"
              name="note"
              className="form-control"
              value={formik.values.note}
              onChange={formik.handleChange}
            />
          </div>
          <div className="mb-3">
            <label htmlFor="date" className="form-label">
              Date
            </label>
            <input
              type="date"
              id="date"
              name="date"
              className="form-control"
              value={formik.values.date}
              onChange={formik.handleChange}
            />
            <div className="text-danger fst-italic">
              Expense date is required
            </div>
          </div>
          <button
            className="btn btn-sm btn-primary btn-outline-light"
            type="submit"
          >
            Save
          </button>
        </form>
      </div>
    </div>
  );
};

export default NewExpense;
