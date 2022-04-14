package com.techelevator.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UnauthorizedException
 */
@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UnauthorizedException extends Exception {

    private static final long serialVersionUId = 1L;

}