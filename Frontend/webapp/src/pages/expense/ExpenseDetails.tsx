import { useParams } from "react-router-dom";
import CurrencyUtils from "../../utils/CurrencyUtils";
import DateUtils from "../../utils/DateUtils";

const ExpenseDetails = () => {
  const { expenseId } = useParams();
  return (
    <div className="container mt-2">
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
