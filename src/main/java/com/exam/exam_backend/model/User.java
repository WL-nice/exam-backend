package com.exam.exam_backend.model;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
public class User implements Serializable {
    /**
     * 用户名
     */
    private String username;

    @Serial
    private static final long serialVersionUID = 1L;


}
