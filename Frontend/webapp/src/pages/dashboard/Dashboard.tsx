import ExpenseList from "../../components/ExpenseList";
import useExpenses from "../../hooks/useExpenses";
import DashboardStatus from "./DashboardStatus";

const Dashboard = () => {
  const { expenses, error, isLoading } = useExpenses();
  return (
    <div className="container">
      {isLoading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      <DashboardStatus></DashboardStatus>
      <ExpenseList expenses={expenses} />;
    </div>
  );
};

export default Dashboard;
