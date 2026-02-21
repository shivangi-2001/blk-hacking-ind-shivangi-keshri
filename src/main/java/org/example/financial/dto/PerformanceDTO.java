package org.example.financial.dto;

import lombok.Data;

@Data
public class PerformanceDTO {

    private String time;
    private String memory;
    private int threads;
}