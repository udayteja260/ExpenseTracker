package Expense.example.ExpenseTracker.Service;

import Expense.example.ExpenseTracker.DAO.ExpenseRepo;
import Expense.example.ExpenseTracker.Model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExpenseService {
   @Autowired
   ExpenseRepo expenseRepo;

    public void add(Expenses expenses)
    {
        expenseRepo.save(expenses);
    }

    public List<Expenses> getAllExpenses()
    {
        return expenseRepo.findAll();
    }
    public Map<String, Double> getGraph()
    {
        List<Expenses> allExpenses = expenseRepo.findAll();
        Map<String, Double> totalAmountByCategory = new HashMap<>();

        for (Expenses expense : allExpenses) {
            totalAmountByCategory.merge(expense.getCategory(), expense.getAmount(), Double::sum);
        }

        return totalAmountByCategory;

    }
}
