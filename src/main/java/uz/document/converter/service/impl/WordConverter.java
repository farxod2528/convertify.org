package uz.document.converter.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.document.converter.model.Request;
import uz.document.converter.service.AbstractConverter;

import java.util.Optional;

@Component
public class WordConverter extends AbstractConverter {

    @Override
    protected Optional<Resource> convert(Request source) {
        return Optional.empty();
    }
}
