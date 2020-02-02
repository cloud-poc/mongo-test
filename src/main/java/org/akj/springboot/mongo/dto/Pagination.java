package org.akj.springboot.mongo.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Pagination {
    private Boolean hasNext;

    @NotNull
    @Min(value = 1)
    @Max(value = 9999)
    private Integer pageSize = 200;

    @NotNull
    @Min(value = 1)
    @Max(value = 9999)
    private Integer pageNumber = 0;

    private Long totalCount;
}
