package br.com.bxblue.pokecoin.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@Data
public class UserValidationException extends Exception {
    final String errorMessage;

}
