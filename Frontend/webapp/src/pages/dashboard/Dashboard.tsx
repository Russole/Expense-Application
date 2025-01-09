const Dashboard = () => {
  const expenses = [
    {
      id: 1,
      name: "Water bill",
      amount: 288.0,
      date: new Date().toDateString(),
    },
    {
      id: 2,
      name: "Electricity bill",
      amount: 500.0,
      date: new Date().toDateString(),
    },
    {
      id: 3,
      name: "Wifi bill",
      amount: 700.0,
      date: new Date().toDateString(),
    },
  ];
  return (
    <div>
      <table border={1}>
        <thead>
          <tr>
            <th>Title</th>
            <th>Amount</th>
            <th>Date</th>
          </tr>
        </thead>
        <tbody>
          {expenses.map((expense) => (
            <tr key={expense.id}>
              <td>{expense.name}</td>
              <td>{expense.amount}</td>
              <td>{expense.date}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Dashboard;
