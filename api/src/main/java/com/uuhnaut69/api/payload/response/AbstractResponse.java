package com.uuhnaut69.api.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author uuhnaut
 * @project demo
 */
@Getter
@Setter
public class AbstractResponse implements Serializable {

    private Long id;

    private LocalDateTime createdDate;

    private LocalDateTime lastModifiedDate;
}
