package video.meedding.Meedding.config.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import video.meedding.Meedding.domain.Member;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Data
@Getter
public class PrincipalDetails implements UserDetails {
    private Member member;
    public PrincipalDetails(Member member) {
        this.member=member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        System.out.println(member.getRoleList().get(0));
        member.getRoleList().forEach(r -> {
            authorities.add(()->{return r;});
        });
        System.out.println(authorities);
        return authorities;
    }


    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getLoginid();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
