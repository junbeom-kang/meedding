package video.meedding.Meedding.Controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import video.meedding.Meedding.service.MemberService;
import video.meedding.Meedding.service.RoomService;

import static org.junit.jupiter.api.Assertions.*;
@Transactional
@SpringBootTest
class MemberControllerTest {
    @Autowired MemberService memberService;
    @Autowired RoomService roomService;

    @Test
    public void beanTest() throws Exception {
        //given
        System.out.println(memberService.getClass());

        //when


        //then

    }

}