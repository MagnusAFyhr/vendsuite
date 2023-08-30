package com.packapuff.services.machine.service.dto.column;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListVendingColumnsRequest {

    List<Integer> machineIds;

}
