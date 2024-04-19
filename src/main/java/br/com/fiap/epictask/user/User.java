package br.com.fiap.epictask.user;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity(name = "EpicUser")
@Data
@EqualsAndHashCode(callSuper = false)
public class User extends DefaultOAuth2User {
    
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Column(unique = true)
    private String email;
    private String avatarUrl;
    private Integer score;

    public User(){
        super(
            List.of(new SimpleGrantedAuthority("ROLE_USER")),
            Map.of("name", "Anonymous"),
            "name"
        );
    }

    public User(Collection<? extends GrantedAuthority> authorities, Map<String, Object> attributes,
            String nameAttributeKey) {
        super(authorities, attributes, nameAttributeKey);
    }

    public User(OAuth2User principal) {
        super(principal.getAuthorities(), principal.getAttributes(), "name");
        this.name = principal.getAttribute("name");
        this.email = principal.getAttribute("email");
        this.avatarUrl = principal.getAttribute("avatar_url");
    }

    

}
