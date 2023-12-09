package ma.xproce.inventoryservice.mappers;

import ma.xproce.inventoryservice.dtos.CreatorDTO;
import ma.xproce.inventoryservice.dtos.VideoDTO;
import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CreatorMapperConfig {
    private final ModelMapper modelMapper = new ModelMapper();
    public CreatorDTO fromCreator(Creator creator){
        return this.modelMapper.map(creator,CreatorDTO.class);
    }
    public Creator fromCreatorDTO(CreatorDTO creatorDTO){
        return this.modelMapper.map(creatorDTO,Creator.class);
    }
}
