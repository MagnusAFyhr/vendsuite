package com.packapuff.inventory.service.dto.product;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductsRequest {

    List<Integer> productIds;

}
