package com.fayardev.regms.auth;

public class AuthConstants {
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/sign-up";
    public static final String LOGIN_URL = "/login";
    public static final String FORGOT_PASSWORD_URL = "/password/reset-password";
    public static final String FORGOT_PASSWORD_CHANGED_URL = "/password/save-password-forgot";
    public static final String FORGOT_PASS_CHANGE_URL = "/password/change-password";
    public static final String VALID_URL = "/validate/**";
}
