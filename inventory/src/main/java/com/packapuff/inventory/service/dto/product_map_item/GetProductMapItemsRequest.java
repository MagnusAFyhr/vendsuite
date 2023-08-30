package com.packapuff.inventory.service.dto.product_map_item;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetProductMapItemsRequest {

    List<Integer> productMapIds;

}
