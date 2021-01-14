package uz.document.converter;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@PropertySources({
        @PropertySource(value = "classpath:application.properties", encoding = "UTF-8"),
        @PropertySource(value = "classpath:messages.properties", encoding = "UTF-8")
})
@SpringBootApplication
public class ConverterApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ConverterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
