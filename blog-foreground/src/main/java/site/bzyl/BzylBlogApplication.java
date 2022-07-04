package site.bzyl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类要和controller放在同一个module下
 */
@SpringBootApplication
public class BzylBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(BzylBlogApplication.class, args);
    }
}
