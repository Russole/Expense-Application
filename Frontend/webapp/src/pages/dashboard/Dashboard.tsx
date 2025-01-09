import ExpenseList from "../../components/ExpenseList";
import { Expense } from "../../model/Expense";
const Dashboard = () => {
  const expenses: Expense[] = [
    {
      id: 1,
      name: "Water bill",
      amount: 288.0,
      date: new Date().toDateString(),
      category: "Utilities",
      note: "bulls",
    },
    {
      id: 2,
      name: "Electricity bill",
      amount: 500.0,
      date: new Date().toDateString(),
      category: "Utilities",
      note: "bulls",
    },
    {
      id: 3,
      name: "Wifi bill",
      amount: 700.0,
      date: new Date().toDateString(),
      category: "Utilities",
      note: "bulls",
    },
  ];
  return <ExpenseList expenses={expenses} />;
};

export default Dashboard;
