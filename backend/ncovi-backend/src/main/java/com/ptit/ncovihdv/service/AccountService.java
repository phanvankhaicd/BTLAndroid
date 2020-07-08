package com.ptit.ncovihdv.service;

import com.ptit.ncovihdv.dto.request.*;
import com.ptit.ncovihdv.dto.response.AccountResponse;
import com.ptit.ncovihdv.dto.response.GetListSocialAccountResponse;
import com.ptit.ncovihdv.dto.response.LoginResponse;
import com.ptit.ncovihdv.exception.ApplicationException;
import com.ptit.ncovihdv.model.Account;

public interface AccountService {
    LoginResponse loginFacebookAction(LoginFacebookRequest request) throws ApplicationException;

    LoginResponse loginGoogleAction(LoginGoogleRequest request) throws ApplicationException;

    LoginResponse loginByUsernameAction(LoginByUsernameRequest request)
            throws ApplicationException;

    void addFacebookAction(AddFacebookRequest request) throws ApplicationException;

    void addGoogleAction(AddGoogleRequest request) throws ApplicationException;

    void deleteAccountAction(DeleteAccountRequest request) throws ApplicationException;

    Account findAccountExist(String username);

    GetListSocialAccountResponse getListSocialAccountAction()
            throws ApplicationException;

    void createAccount(CreateAccountRequest createAccountRequest);

    AccountResponse updateAccount(UpdateAccountRequest updateAccountRequest)
            throws ApplicationException;

    AccountResponse changePassword(ChangePasswordRequest changePasswordRequest)
            throws ApplicationException;

    AccountResponse getUserInfo() throws ApplicationException;

}
