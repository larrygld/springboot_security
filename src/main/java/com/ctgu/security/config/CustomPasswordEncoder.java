package com.ctgu.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author larry
 * @date 2021/1/15 16:36
 */
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}