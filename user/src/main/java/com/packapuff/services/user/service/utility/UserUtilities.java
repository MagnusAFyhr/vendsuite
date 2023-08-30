package com.packapuff.services.user.service.utility;

import org.springframework.stereotype.Component;

@Component
public class UserUtilities {

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }

    public boolean isValidPhoneNumber(Long phoneNumber) {
        // length is 10
        return (null != phoneNumber || 10 == phoneNumber.toString().length());
    }
}
