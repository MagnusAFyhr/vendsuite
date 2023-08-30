package com.packapuff.services.site.service.utilities;

import org.springframework.stereotype.Component;

@Component
public class SiteUtilities {

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }

}
