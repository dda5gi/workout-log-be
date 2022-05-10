package com.example.workout.error.exception;

import com.example.workout.error.ErrorCode;

public class PasswordNotMatchedException extends BusinessException{
    public PasswordNotMatchedException() {
        super(ErrorCode.PASSWORD_NOT_MATCHED);
    }
}
