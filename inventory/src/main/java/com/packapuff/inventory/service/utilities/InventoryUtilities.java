package com.packapuff.inventory.service.utilities;

import org.springframework.stereotype.Component;

@Component
public class InventoryUtilities {

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }

}
