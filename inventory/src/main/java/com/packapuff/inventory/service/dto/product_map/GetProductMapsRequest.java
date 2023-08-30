package com.packapuff.inventory.service.dto.product_map;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductMapsRequest {

    List<Integer> productMapIds;

}
