package com.tw.mall.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tw_user")
@Getter
@Setter
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String username;
    private String password;
    private String fullname;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Authority authority;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @Column(name = "user_id")
//    private List<OrderVO> orders;
}
