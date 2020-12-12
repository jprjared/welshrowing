package com.team1.welshrowing.domain;

import lombok.Value;
import java.util.Map;

/**
 * Adapted from code examples at https://gist.github.com/jonikarppinen/662c38fb57a23de61c8b#gistcomment-1397743 [Accessed: 7 December 2020]
 */
@Value
public class CustomError {
    public Integer status;
    public String error;
    public String message;
    public String timeStamp;
    public String trace;

    public CustomError(int status, Map<String, Object> errorAttributes) {
        this.status = status;
        this.error = (String) errorAttributes.get("error");
        this.message = (String) errorAttributes.get("message");
        this.timeStamp = errorAttributes.get("timestamp").toString();
        this.trace = (String) errorAttributes.get("trace");
    }
}
