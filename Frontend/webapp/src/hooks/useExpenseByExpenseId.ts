import { useEffect, useState } from "react";
import { Expense } from "../model/Expense";
import { getExpenseByExpenseId } from "../services/expense-service";

const useExpenseByExpenseId = (expenseId: string) => {
  const [expense, setExpense] = useState<Expense | undefined>();
  const [errors, setErrors] = useState<string>("");
  const [isLoading, setLoader] = useState<boolean>(false);

  useEffect(() => {
    if (expenseId) {
      setLoader(true);
      getExpenseByExpenseId(expenseId)
        .then((response) => setExpense(response.data))
        .catch((error) => {
          setErrors(error.message);
          console.log(error);
        })
        .finally(() => setLoader(false));
    }
  }, []);

  return {expense, errors, isLoading};
}

export default useExpenseByExpenseId;