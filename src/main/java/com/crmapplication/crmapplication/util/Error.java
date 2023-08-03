package com.crmapplication.crmapplication.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error {

    public String message;

    private HttpStatus httpStatus;


}
