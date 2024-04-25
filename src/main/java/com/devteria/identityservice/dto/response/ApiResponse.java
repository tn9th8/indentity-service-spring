package com.devteria.identityservice.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // attribute is null => remove from json
public class ApiResponse <T>{
    int code = 200; // 200 is oke
    String message;
    T result;
    T error;

}
