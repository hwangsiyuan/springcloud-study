package com.hussein.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2020/1/2 10:20 AM
 */
@Data
public class User {

    private Long id;

    @JsonProperty("username")
    private String userName;

    private String name;

    private Integer age;

    private BigDecimal balance;
}
