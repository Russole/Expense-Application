import CurrencyUtils from "../../utils/CurrencyUtils";
import DateUtils from "../../utils/DateUtils";

const DashboardStatus = () => {
  return (
    <div className="mt-2">
      <div className="text-center">
        <h2 className="mb-4">Total Expenses</h2>
        <h3>
          <span className="badge rounded-pill app-primary-bg-color">
            {CurrencyUtils.formatToINR(500)}
          </span>
        </h3>
      </div>
      <div className="d-flex justify-content-between">
        <div>
          Welcome, <b className="app-primary-color">"Luffy@example.com"</b>
        </div>
        <div>{DateUtils.getFormattedDate(new Date())}</div>
      </div>
    </div>
  );
};

export default DashboardStatus;
