package com.example.workout.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INVALID_PARAMETER_VALUE(400, "잘못된 입력 값."),
    INVALID_PARAMETER_TYPE(400, "입력 타입 불일치"),
    RESOURCE_NOT_FOUND(404, "해당 데이터를 찾을 수 없음"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    PASSWORD_NOT_MATCHED(400, "비밀번호 불일치")
    ;

    private final int status;
    private final String message;

    ErrorCode(final int status, final String message) {
        this.status = status;
        this.message = message;
    }
}
