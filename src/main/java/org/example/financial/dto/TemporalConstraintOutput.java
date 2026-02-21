package org.example.financial.dto;

import lombok.Data;

import java.util.List;

@Data
public class TemporalConstraintOutput {
    List<ValidTransactionDTO> valid;
    List<InvalidTransactionDTO> invalid;
}
