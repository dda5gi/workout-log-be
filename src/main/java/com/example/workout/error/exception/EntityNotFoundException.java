package com.example.workout.error.exception;

import com.example.workout.error.ErrorCode;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException() {
        super(ErrorCode.RESOURCE_NOT_FOUND);
    }
}
