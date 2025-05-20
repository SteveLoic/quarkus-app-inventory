package org.stve.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.stve.category.CategoryResponse;
import org.stve.product.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse {

    private int status;
    private String message;

    private List<CategoryResponse> categoryResponses;
    private CategoryResponse categoryResponse;

    private ProductResponse productResponse;
    private List<ProductResponse> productResponses;

    private  final LocalDateTime timeStamp = LocalDateTime.now();

}
