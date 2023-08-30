package com.packapuff.vendsuite.common.event_models.model;

import com.packapuff.vendsuite.common.event_models.enums.ConnectionEventType;

public class MachineRegistrationEvent extends BaseSystemEvent {

    public MachineRegistrationEvent(Object source) {
        super(source, "register");
    }

}
