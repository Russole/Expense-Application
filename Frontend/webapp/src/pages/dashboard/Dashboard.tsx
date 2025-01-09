import { useEffect, useState } from "react";
import ExpenseList from "../../components/ExpenseList";
import { Expense } from "../../model/Expense";
import apiClient from "../../config/api-client";
const Dashboard = () => {
  const [expenses, setExpenses] = useState<Expense[]>([]);
  const [error, setErrors] = useState(null);
  const [isLoading, setLoader] = useState(false);
  useEffect(() => {
    //api call to backend system
    apiClient
      .get("/expenses")
      .then((response) => {
        setExpenses(response.data);
        setLoader(false);
      })
      .catch((error) => setErrors(error.message))
      .finally(() => setLoader(false));
  }, []);

  return <ExpenseList expenses={expenses} />;
};

export default Dashboard;
