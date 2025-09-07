package com.back.myproject.domain.member.entity;


import com.back.myproject.global.jpa.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Table(name = "member")
@Getter
public class Member extends BaseEntity implements UserDetails{ 

}
