package com.back.myproject.domain.member.entity;


import com.back.myproject.domain.member.enums.UserRole;
import com.back.myproject.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
@Getter
public class Member extends BaseEntity implements UserDetails {

    @Column(name = "member_name", nullable = false)
    private String memberName;

    @Column(name = "member_nickname", nullable = false, unique = true)
    private String memberNickname;

    @Column(name = "member_password", nullable = false)
    private String memberPassword;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_role", nullable = false)
    private UserRole role;

    @Builder
    public Member(String memberName,String memberNickname,String memberPassword, UserRole role) {
        this.memberName = memberName;
        this.memberNickname = memberNickname;
        this.memberPassword = memberPassword;
        this.role = role;
    }

    // --- UserDetails 구현을 위한 메서드들 ---

    // 사용자의 권한 목록을 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.getKey()));
    }

    // 사용자의 비밀번호를 반환 (반드시 암호화된 상태여야 함)
    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    // 사용자의 고유 식별자(ID)를 반환
    @Override
    public String getUsername() {
        return this.memberName;
    }

    // 아래 4개는 계정 상태를 관리하는 메서드 (특별한 경우가 아니면 모두 true를 반환하도록 설정)
    @Override
    public boolean isAccountNonExpired() { // 계정이 만료되었는가?
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 계정이 잠겼는가?
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 비밀번호가 만료되었는가?
        return true;
    }

    @Override
    public boolean isEnabled() { // 계정이 활성화되었는가?
        return true;
    }



}
