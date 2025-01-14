import ExpenseList from "../../components/ExpenseList";
import useExpenses from "../../hooks/useExpenses";
import { Expense } from "../../model/Expense";
import DashboardStatus from "./DashboardStatus";

const Dashboard = () => {
  const loggedInUser: string = "Luffy@example.com";
  const { expenses, error, isLoading } = useExpenses();
  const totalExpenses = expenses.reduce(
    (acc: number, expense: Expense) => acc + expense.amount,
    0
  );
  return (
    <div className="container">
      {isLoading && <p>Loading...</p>}
      {error && <p className="text-danger">{error}</p>}
      <DashboardStatus
        loggedInUser={loggedInUser}
        totalExpenses={totalExpenses}
      ></DashboardStatus>
      <hr />
      <ExpenseList expenses={expenses} />;
    </div>
  );
};

export default Dashboard;
