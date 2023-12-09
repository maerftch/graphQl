package ma.xproce.inventoryservice.web;

import ma.xproce.inventoryservice.dtos.CreatorDTO;
import ma.xproce.inventoryservice.dtos.VideoDTO;
import ma.xproce.inventoryservice.entities.Creator;
import ma.xproce.inventoryservice.entities.Video;
import ma.xproce.inventoryservice.mappers.CreatorMapperConfig;
import ma.xproce.inventoryservice.mappers.VideoMapperConfig;
import ma.xproce.inventoryservice.repositories.CreatorRepo;
import ma.xproce.inventoryservice.repositories.VideoRepo;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VideoGraphQlController {
    private CreatorRepo creatorRepository;
    private VideoRepo videoRepository;
    private CreatorMapperConfig creatorMapper=new CreatorMapperConfig();
    private VideoMapperConfig videoMapper=new VideoMapperConfig();

    VideoGraphQlController(CreatorRepo creatorRepository, VideoRepo videoRepository) {
        this.creatorRepository = creatorRepository;
        this.videoRepository = videoRepository;
    }

    @QueryMapping
    public List<VideoDTO> videotList() {
        List<Video> videos = videoRepository.findAll();
        return videos.stream().map(videoMapper::fromVideo).toList();
    }

    @QueryMapping
    public List<CreatorDTO> creatorList() {
        List<Creator> creators = creatorRepository.findAll();
        return creators.stream().map(creatorMapper::fromCreator).toList();
    }
    @QueryMapping
    public CreatorDTO creatorById(@Argument Long id) {
        Creator c = creatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Creator %s not found", id)));
        return creatorMapper.fromCreator(c);

    }
    @QueryMapping
    public VideoDTO videoById(@Argument Long id){
        Video v = videoRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format("Video %s not found",id)));
        return videoMapper.fromVideo(v);
    }
    @MutationMapping
    public Creator saveCreator(@Argument CreatorDTO creatorDTO){
        Creator creator=creatorMapper.fromCreatorDTO(creatorDTO);
        return creatorRepository.save(creator) ;
    }

    @MutationMapping
    public Video saveVideo(@Argument VideoDTO videoDTO){
        Video video = videoMapper.fromVideoDTO(videoDTO);
        creatorRepository.save(video.getCreator());
        return videoRepository.save(video);

    }



}