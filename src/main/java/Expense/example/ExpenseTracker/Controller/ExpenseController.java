package Expense.example.ExpenseTracker.Controller;

import Expense.example.ExpenseTracker.Model.Expenses;
import Expense.example.ExpenseTracker.Service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/ExpenseTracker")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("expense", new Expenses());
        return "home";
    }

    @GetMapping("/records")
    public ModelAndView allRecords() {
        List<Expenses> expenses = expenseService.getAllExpenses();
        return new ModelAndView("allRecords", "record", expenses);
    }

    @PostMapping("/add")
    public String addExpense(@ModelAttribute Expenses expenses) {
        expenseService.add(expenses);
        return "redirect:/api/ExpenseTracker/records";
    }

    @GetMapping("/dashboard")
    public ModelAndView chartData() {
        Map<String, Double> categoryTotals = expenseService.getGraph();
        ModelAndView modelAndView = new ModelAndView("graph"); // "pieChart" is the name of your view (HTML/Thymeleaf/JSP)
        modelAndView.addObject("categoryData", categoryTotals);
        return modelAndView;
    }
}
