package video.meedding.Meedding.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.repository.FriendsRepository;
import video.meedding.Meedding.repository.MemberRepository;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class FriendService {
    private final MemberRepository memberRepository;
    private final FriendsRepository friendsRepository;


}
