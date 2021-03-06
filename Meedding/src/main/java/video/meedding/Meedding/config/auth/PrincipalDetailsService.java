package video.meedding.Meedding.config.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import video.meedding.Meedding.domain.Member;
import video.meedding.Meedding.exception.NoMemberException;
import video.meedding.Meedding.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    @Override
    public PrincipalDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        return new PrincipalDetails(memberRepository.findById(Long.valueOf(s)).orElseThrow(NoMemberException::new));
    }
}
