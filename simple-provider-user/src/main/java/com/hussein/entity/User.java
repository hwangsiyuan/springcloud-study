package com.hussein.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * <p>Title: User</p>
 * <p>Description: </p>
 * <p>Company: www.hussein.com</p>
 *
 * @author hwangsy
 * @date 2019/12/31 8:09 PM
 */
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "balance")
    private BigDecimal balance;
}
