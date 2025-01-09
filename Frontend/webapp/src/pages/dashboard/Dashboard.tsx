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
    setLoader(true);
    apiClient
      .get("/expenses")
      .then((response) => {
        setExpenses(response.data);
      })
      .catch((error) => setErrors(error.message))
      .finally(() => setLoader(false));
  }, []);

  return (
    <div>
      {isLoading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      <ExpenseList expenses={expenses} />;
    </div>
  );
};

export default Dashboard;
