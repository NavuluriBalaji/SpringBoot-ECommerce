package com.ecommerce.sb_project.payLoad;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class categoryResponse {
    private List<categoryDTO> content;
    private Integer pageNumber;
    private Integer pageSize;
    private long totalElements;
    private int totalPages;
    private boolean lastPage;
}
