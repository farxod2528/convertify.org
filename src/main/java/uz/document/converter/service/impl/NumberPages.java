package uz.document.converter.service.impl;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import uz.document.converter.model.Request;
import uz.document.converter.service.AbstractConverter;

import java.util.Optional;

@Component
public class NumberPages extends AbstractConverter {
    @Override
    protected Optional<Resource> convert(Request source) {
        return Optional.empty();
    }
}
