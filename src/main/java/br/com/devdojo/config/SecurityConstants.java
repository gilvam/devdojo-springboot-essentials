package br.com.devdojo.config;

public class SecurityConstants {
    // Authorization Bearer asdfxyz
    static final String SECRET = "DevDojoFoda";
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users/sign-up";
    static final long EXPIRATION_TIME = 86400000000L;

    //public static void main(String[] args) {
    //    System.out.println("EXPIRATION_TIME: " + TimeUnit.MICROSECONDS.convert(1, TimeUnit.DAYS));
    //}
}
