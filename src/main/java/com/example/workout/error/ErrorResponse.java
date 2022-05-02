package com.example.workout.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class ErrorResponse {
    private int status;
    private String message;
    private List<CustomFieldError> errors;

    private ErrorResponse(final ErrorCode code) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.errors = new ArrayList<>();
    }

    private ErrorResponse(final ErrorCode code, final List<CustomFieldError> errors) {
        this.status = code.getStatus();
        this.message = code.getMessage();
        this.errors = errors;
    }

    public static ErrorResponse of(final ErrorCode code) {
        return new ErrorResponse(code);
    }

    public static ErrorResponse of(final ErrorCode code, final BindingResult bindingResult) {
        return new ErrorResponse(code, CustomFieldError.of(bindingResult));
    }

    public static ErrorResponse of(MethodArgumentTypeMismatchException e) {
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<CustomFieldError> errors = CustomFieldError.of(e.getName(), value, e.getErrorCode());
        return new ErrorResponse(ErrorCode.INVALID_PARAMETER_VALUE, errors);
    }

//    MethodArgumentNotValidException 발생 시 bindingResult에서 FieldError를 뽑아내어
//    어떤 Field에서 오류가 났는지 상세히 볼 수 있다 -> 이를 간소화한 클래스
    @Getter
    @NoArgsConstructor
    public static class CustomFieldError {
        private String field;
        private String value;
        private String reason;

        private CustomFieldError(final String field, final String value, final String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<CustomFieldError> of(final String field, final String value, final String reason) {
            List<CustomFieldError> customFieldErrors = new ArrayList<>();
            customFieldErrors.add(new CustomFieldError(field, value, reason));
            return customFieldErrors;
        }

        // BindingResult를 CustomFieldError로 변환
        private static List<CustomFieldError> of(final BindingResult bindingResult) {
            final List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(error -> new CustomFieldError(
                            error.getField(),
                            error.getRejectedValue() == null ? "" : error.getRejectedValue().toString(),
                            error.getDefaultMessage()))
                    .collect(Collectors.toList());
        }
    }
}
