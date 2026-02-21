package org.example.financial.controller;

import org.example.financial.dto.ReturnsOutputDTO;
import org.example.financial.dto.ReturnsRequestDTO;
import org.example.financial.service.ReturnService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/blackrock/challenge/v1")
public class ReturnController {
    private  final ReturnService service;

    public ReturnController(ReturnService service){
        this.service=service;
    }

    @PostMapping("/returns:nps")
    public ReturnsOutputDTO nps( @RequestBody ReturnsRequestDTO request){
       return service.calculateReturns(request, 0.0711, true);
    }

    @PostMapping("/returns:index")
    public ReturnsOutputDTO index(@RequestBody ReturnsRequestDTO request){
        return service.calculateReturns(request, 0.1449, false);
    }
}
