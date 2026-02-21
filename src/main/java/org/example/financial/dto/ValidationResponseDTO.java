package org.example.financial.dto;

import lombok.Data;

import java.util.List;

@Data
public class ValidationResponseDTO {
 private List<TransactionDTO> valid;
 private  List<InvalidTransactionDTO>  invalid;
}
