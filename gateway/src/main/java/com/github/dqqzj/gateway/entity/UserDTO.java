package com.github.dqqzj.gateway.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author qzj 2017年10月13日
 */

@Data
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "user")
public class UserDTO implements UserDetails,Serializable {
    @Id
    private String username;// 用户姓名 电话号码
    private String email;//邮件
    private String password;// 登录密码
    private String role;// 角色
    private LocalDateTime lastLoginTime;// 最后登录时间
    private BigDecimal balance;//余额
    private LocalDateTime registerTime;//注册时间
    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    // 这是权限
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }


}
