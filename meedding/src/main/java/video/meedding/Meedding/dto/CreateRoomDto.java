package video.meedding.Meedding.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateRoomDto {
    @NotBlank
    private String title;
}
