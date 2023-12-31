package com.ExpenseTracker.ExpenseTracker.Service;

import com.ExpenseTracker.ExpenseTracker.Model.Expense;
import com.ExpenseTracker.ExpenseTracker.Model.Product;
import com.ExpenseTracker.ExpenseTracker.Model.User;
import com.ExpenseTracker.ExpenseTracker.Repository.IExpenseRepo;
import com.ExpenseTracker.ExpenseTracker.Repository.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class ExpenseService {
    @Autowired
    IExpenseRepo expenseRepo;

    @Autowired
    IUserRepo userRepo;

    public void saveExpense(Expense expense){
        expenseRepo.save(expense);
    }

    public List<Expense> getAllExpense(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        List<Expense> expenseList = expenseRepo.findByUser(user);
        for(Expense expense : expenseList){
            expense.getUser().setUserPassword("Confidential.....");
        }
        return expenseList;
    }

    public String updateExpense(Long id, String email, String desc) {
        User user = userRepo.findFirstByUserEmail(email);
        List<Expense> expenseList = expenseRepo.findByUser(user);
        for(Expense expense : expenseList){
            if(expense.getId().equals(id)){
                expense.setDescription(desc);
                expenseRepo.save(expense);
                return "Update SuccessFully";
            }
        }
        return "Unknown Error Occurred";
    }

    public String deleteExpense(Long id, String email) {
        User user = userRepo.findFirstByUserEmail(email);
        List<Expense> expenseList = expenseRepo.findByUser(user);
        for(Expense expense : expenseList){
            if(expense.getId().equals(id)){
                expenseRepo.delete(expense);
                return "Deleted Expense";
            }
        }
        return "Unknown Error Occurred";
    }

    
}
