    package ma.xproce.inventoryservice;

    import ma.xproce.inventoryservice.dtos.CreatorDTO;
    import ma.xproce.inventoryservice.dtos.VideoDTO;
    import ma.xproce.inventoryservice.entities.Creator;
    import ma.xproce.inventoryservice.entities.Video;
    import ma.xproce.inventoryservice.mappers.CreatorMapperConfig;
    import ma.xproce.inventoryservice.mappers.VideoMapperConfig;
    import org.modelmapper.ModelMapper;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.CommandLineRunner;
    import org.springframework.boot.SpringApplication;
    import org.springframework.boot.autoconfigure.SpringBootApplication;
    import ma.xproce.inventoryservice.repositories.CreatorRepo;
    import ma.xproce.inventoryservice.repositories.VideoRepo;

    import java.util.List;
    import java.util.stream.Collectors;

    @SpringBootApplication
    public class InventoryServiceApplication {

        @Autowired
        private CreatorMapperConfig creatorMapper;
        @Autowired
        private VideoMapperConfig videoMapperConfig;
        private VideoRepo videoRepo;
        private CreatorRepo creatorRepo;
        private ModelMapper modelMapper;


        public static void main(String[] args) {
            SpringApplication.run(InventoryServiceApplication.class, args);
        }

        CommandLineRunner start1(VideoRepo videoRepo, CreatorRepo creatorRepo) {

            return args -> {
                List<Creator> creators = List.of(Creator.builder().name("y").email("c1@gmail.com").build(),
                        Creator.builder().name("x").email("c2@gmail.com").build()
                        );
                for (Creator creator : creators) {
                    creatorRepo.save(creator);
                }
                List<Video> videos = List.of(Video.builder().name("X").url("vid").description("description").creator(creators.get(1)).build(),
                        Video.builder().name("Y").url("vid1").description("description").creator(creators.get(1)).build()
                        );
                for (Video video : videos) {
                    videoRepo.save(video);
                }


            };
        }

    }


