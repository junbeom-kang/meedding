package video.meedding.Meedding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import video.meedding.Meedding.domain.Room;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseRoomDto {
    private Long room_id;
    private String title;
    private String password;
    public static ResponseRoomDto convertToResponseRoomDto(Room room) {
        ResponseRoomDto responseRoomDto=new ResponseRoomDto();
        responseRoomDto.room_id=room.getId();
        responseRoomDto.title= room.getRoomTitle();
        responseRoomDto.password=room.getRoomPassword();
        return responseRoomDto;
    }
}
