package com.elex.oa.saweb.filter;
import com.elex.oa.util.EncryptUtil;
import org.apache.commons.codec.DecoderException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomPwdEncoder implements PasswordEncoder {
    private static ThreadLocal<Boolean> ingorePwd = new ThreadLocal();

    public CustomPwdEncoder() {
    }

    public static void setIngore(boolean ingore) {
        ingorePwd.set(Boolean.valueOf(ingore));
    }

    public String encode(CharSequence rawPassword) {
        String pwd = "";

        try {
            pwd = EncryptUtil.hexToBase64((String)rawPassword);
        } catch (DecoderException var4) {
            ;
        }

        return pwd;
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if(ingorePwd.get() != null && ((Boolean)ingorePwd.get()).booleanValue()) {
            return true;
        } else {
            String enc = this.encode(rawPassword);
            return enc.equals(encodedPassword);
        }
    }
}

