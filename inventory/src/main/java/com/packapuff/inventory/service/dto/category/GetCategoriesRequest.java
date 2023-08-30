package com.packapuff.inventory.service.dto.category;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Getter
@Setter
@Validated
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetCategoriesRequest {

    List<Integer> categoryIds;

}
