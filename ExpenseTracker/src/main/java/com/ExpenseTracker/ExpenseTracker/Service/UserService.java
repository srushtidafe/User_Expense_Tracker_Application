package com.ExpenseTracker.ExpenseTracker.Service;

import com.ExpenseTracker.ExpenseTracker.Model.AuthenticationToken;
import com.ExpenseTracker.ExpenseTracker.Model.User;
import com.ExpenseTracker.ExpenseTracker.Model.dto.SignInInput;
import com.ExpenseTracker.ExpenseTracker.Model.dto.SignUpOutput;

import com.ExpenseTracker.ExpenseTracker.Repository.IAuthRepo;
import com.ExpenseTracker.ExpenseTracker.Repository.IUserRepo;
import com.ExpenseTracker.ExpenseTracker.Service.Hashing.Encrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {
    @Autowired
    IUserRepo userRepo;
    @Autowired
    IAuthRepo authUser;

    @Autowired
    AuthenticationService authenticationService;
    public SignUpOutput signUp(User user) throws NoSuchAlgorithmException {
        String userEmail = user.getUserEmail();
        User user1 = userRepo.findFirstByUserEmail(userEmail);
        if(user1!=null){
            return new SignUpOutput("Email Already registered",false);
        }
        String hexPassWord = Encrypt.encryptPassword(user.getUserPassword());
        user.setUserPassword(hexPassWord);
        userRepo.save(user);
        return new SignUpOutput("sign Up SuccessFull",true);
    }


    public String signIn(SignInInput signInInput) throws NoSuchAlgorithmException {

            String email = signInInput.getEmail();
            String password = signInInput.getPassword();
            if(email==null) return "Invalid Credential";
            User user1 = userRepo.findFirstByUserEmail(email);
            if(user1==null) return "Email not exist...sign Up first";
            if(!Encrypt.encryptPassword(password).equals(user1.getUserPassword()))return "Invalid Sign In Credential...";

            AuthenticationToken authenticationTokenUser = new AuthenticationToken(user1);
            authUser.save(authenticationTokenUser);
            return "Signed In";

    }

    public String logOut(String email) {
        User user = userRepo.findFirstByUserEmail(email);
        AuthenticationToken authenticationToken = authUser.findFirstByUser(user);
        authUser.delete(authenticationToken);
        return "Logged Out.....";
    }
}
