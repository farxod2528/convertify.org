package uz.document.converter.service.impl;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.document.converter.constant.Mappings;
import uz.document.converter.model.Request;
import uz.document.converter.service.AbstractConverter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Component
public class Image2Pdf extends AbstractConverter {

    @Override
    protected Optional<Resource> convert(Request source) {
        Mappings mapping = source.getMapping();
        MultipartFile image2pdf = source.getSource();
        return Optional.empty();
    }}
