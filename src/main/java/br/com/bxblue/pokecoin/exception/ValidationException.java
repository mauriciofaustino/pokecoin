package br.com.bxblue.pokecoin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class ValidationException extends Exception {
    final String errorMessage;

}
