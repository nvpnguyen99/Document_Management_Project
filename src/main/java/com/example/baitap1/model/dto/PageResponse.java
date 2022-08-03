package com.example.baitap1.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    private int numberOfElements;
    private int numberOfPages;
    private int currentPage;
}
