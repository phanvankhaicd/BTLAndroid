package com.ptit.ncovihdv.util.common;

import com.ptit.ncovihdv.model.Account;
import com.ptit.ncovihdv.repository.AccountRepository;
import com.ptit.ncovihdv.util.common.type.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public class Constant {

    @Autowired
    private static AccountRepository accountRepository;

    public static String getCurrentUser() {
        final Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return ((User) authentication.getPrincipal()).getUsername();
        }
        throw new IllegalStateException();
    }

    public static final com.ptit.ncovihdv.model.User getCurrentUser(String username) {
        Account account = accountRepository.findAccountByUsernameAndStatus(username,
                StatusEnum.ACTIVE.getValue());
        return account.getUserByUserId();
    }
}
