import java.nio.file.Paths;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//public class StaticResourceConfiguration implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        String absoluteUploadsPath = Paths.get("src", "main", "resources", "static", "uploads").toAbsolutePath().toString();
//        registry.addResourceHandler("/uploads/**")
//                .addResourceLocations("file:" + absoluteUploadsPath + "/");
//    }
//}


