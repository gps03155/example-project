package config.mixing.videosystem;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

@Configuration
@Import(DVDPlayerConfig.class) // java config 가져오기
@ImportResource("classpath:config/mixing/videosystem/DVDConfig.xml") // xml 가져오기 - 컴파일이 되어있지 않기 때문에 오류가 날 수 있어서 불안한 방법
public class VideoSystemConfig02 {

}
