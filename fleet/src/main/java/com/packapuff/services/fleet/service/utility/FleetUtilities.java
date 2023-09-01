package com.packapuff.services.fleet.service.utility;


import org.springframework.stereotype.Component;

import java.util.List;

import static com.packapuff.vendsuite.common.error_library.constants.VendSuiteErrorLibrary.VEND_SUITE_GENERIC_GENERIC;

@Component
public class FleetUtilities {

    MachineController machineController;

    UserController userController;

    public boolean isBlank(String target) {
        if (null == target) {
            return true;
        }
        return "".equals(target.trim());
    }

    public Boolean machineExists(Integer machineId) {
        Boolean exists;
        try {
            exists = machineController.getMachineExists(machineId).getBody();

        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        return exists;
    }

    public Boolean userExists(Integer userId) {
        Boolean exists;
        try {
            GetUsersRequest request = GetUsersRequest.builder().userIds(List.of(userId)).build();

            List<UserResponse> response = userController.getUsers(request).getBody();

            exists = (Boolean) !(response == null || response.isEmpty());

        } catch (Exception e) {
            throw new VendSuiteException(VEND_SUITE_GENERIC_GENERIC.getVendSuiteError());
        }

        return exists;
    }

}
