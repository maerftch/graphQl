package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dtos.VideoDTO;
import ma.xproce.inventoryservice.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class VideoMapperConfig {
    private final ModelMapper modelMapper = new ModelMapper();
    public VideoDTO fromVideo(Video video){
        return this.modelMapper.map(video,VideoDTO.class);
    }
    public Video fromVideoDTO(VideoDTO videoDTO){
        return this.modelMapper.map(videoDTO,Video.class);
    }
}
