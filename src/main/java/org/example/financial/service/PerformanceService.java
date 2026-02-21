package org.example.financial.service;

import org.example.financial.dto.PerformanceDTO;
import org.example.financial.dto.ReturnsRequestDTO;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;

@Service
public class PerformanceService {
    private final ReturnService returnService;

    public  PerformanceService(ReturnService returnService){
        this.returnService=returnService;
    }

    public PerformanceDTO measurePerformance(ReturnsRequestDTO request, double rate, boolean isNPS){
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        long memoryBefore = runtime.totalMemory()-runtime.freeMemory();

        long startTime = System.nanoTime();
        returnService.calculateReturns(request, rate, isNPS);

        long endTime = System.nanoTime();

        long memoryAfter = runtime.totalMemory()-runtime.freeMemory();

        long memoryUsed = memoryAfter - memoryBefore;

        double memoryMB = memoryUsed /(1024.0 * 1024.0);

        long durationNano = endTime-startTime;
        double durationMillis = durationNano / 1_000_000.0;

        int threadCount = Thread.activeCount();
        PerformanceDTO output = new PerformanceDTO();

        output.setTime(String.format("%.3f ms", durationMillis));

        DecimalFormat df = new DecimalFormat("0.00");

        output.setMemory(df.format(memoryMB)+ " MB");

        output.setThreads(threadCount);
        return output;

    }
}
