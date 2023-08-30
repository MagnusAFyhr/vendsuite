package com.packapuff.services.machine.service.utility;

import org.springframework.stereotype.Component;

@Component
public class MachineUtilities {

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }
}
