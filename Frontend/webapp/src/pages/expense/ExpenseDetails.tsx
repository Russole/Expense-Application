import { useParams } from "react-router-dom";
import CurrencyUtils from "../../utils/CurrencyUtils";
import DateUtils from "../../utils/DateUtils";
import { getExpenseByExpenseId } from "../../services/expense-service";
import { useEffect, useState } from "react";
import { Expense } from "../../model/Expense";

const ExpenseDetails = () => {
  const { expenseId } = useParams<{ expenseId: string }>();
  const [expense, setExpense] = useState<Expense | undefined>();
  const [errors, setErrors] = useState<string>("");
  const [isLoading, setLoader] = useState<boolean>(false);

  useEffect(() => {
    if (expenseId) {
      setLoader(true);
      getExpenseByExpenseId(expenseId)
        .then((response) => setExpense(response.data))
        .catch((error) => setErrors(error.message))
        .finally(() => setLoader(false));
    }
  }, []);
  return (
    <div className="container mt-2">
      <div className="d-flex flex-row-reverse mb-2">
        <button className="btn btn-sm btn-danger">Delete</button>
        <button className="btn btn-sm btn-warning mx-2">Edit</button>
        <button className="btn btn-sm btn-secondary">Back</button>
      </div>
      <div className="card">
        <div className="card-body p-3">
          <table className="table table-borderless table-responsive">
            <tbody>
              <tr>
                <th>Name</th>
                <td>Water bill</td>
              </tr>
              <tr>
                <th>Category</th>
                <td>Bills</td>
              </tr>
              <tr>
                <th>Amount</th>
                <td>{CurrencyUtils.formatToINR(500)}</td>
              </tr>
              <tr>
                <th>Date</th>
                <td>{DateUtils.formatDateString("2024-7-14")}</td>
              </tr>
              <tr>
                <th>Note</th>
                <td>My first spending</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
};

export default ExpenseDetails;
