package com.ptit.ncovihdv.util.message;

public class ApplicationCode {
    public static final int SUCCESS = 0;
    public static final int ERROR = 1;

    // Error
    public static final String SUCCESS_MSG = "Success";
    public static final String ERROR_MSG = "Error";
    public static final String REQUEST_ERROR_MSG = "Wrong request";
    public static final String DATA_NOT_FOUND = "No data found";
    public static final String PASSWORD_WRONG = "Password is wrong";
    public static final String DATA_INVALID = "The data invalid";

    // account
    public static final String LOGIN_SOCIAL_FAIL_MSG = "Token social invalid, no account found";
    public static final String ACCOUNT_NOT_EXIST_MSG = "Account not exist";
    public static final String USER_NOT_FOUND_MSG = "User not found";
    public static final String ACCOUNT_HAS_EXIST_MSG = "Account has already existed";
    public static final String TOKEN_INVALID_MSG = "Token invalid";
    public static final String DELETE_ACCOUNT_USING_MSG = "Account is being use, can not delete";

    public static final String ADD_FB_MSG = "Add facebook account successfully";
    public static final String ADD_GG_MSG = "Add google account successfully";
    public static final String CREATE_ACCOUNT = "Register successfully";
    public static final String UPDATE_ACCOUNT = "Update account successfully";
    public static final String DELETE_ACCOUNT = "Delete account successfully";

    // password
    public static final String OLD_PASSWORD_INCORRECT = "Old passwrod incorrect";
    public static final String OLD_PASSWORD_NOT_SAME_NEW_PASSWORD =
            "The old password is not the same as the new password";

    public static final String CHANGE_PASSWORD_SUCCESS = "Change password successfully";

    // health monitor
    public static final String SEND_DATA_HEALTH_SUCCESS = "Send data health monitor success";
    public static final String GET_DATA_HEATH_SUCCESS = "Get list data health monitor success";

    //Reflection
    public static final String SEND_DATA_REFLECTION_CONTACT_SUCCESS = "Send data reflection contact success";
    public static final String SEND_DATA_REFLECTION_INFO_SUCCESS = "Send data reflection info success";
}
