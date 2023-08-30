package com.packapuff.services.machine.service.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMachinesRequest {

    List<Integer> machineIds;

}
