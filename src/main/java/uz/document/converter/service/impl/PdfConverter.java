package uz.document.converter.service.impl;

import com.sun.org.apache.xpath.internal.operations.Mult;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import uz.document.converter.constant.Mappings;
import uz.document.converter.model.Request;
import uz.document.converter.service.AbstractConverter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import static uz.document.converter.constant.Extensions.DOCX;
import static uz.document.converter.constant.Extensions.PDF;
import static uz.document.converter.constant.Mappings.*;
import static uz.document.converter.utils.Utils.getRandomFileName;

@Component
public class PdfConverter extends AbstractConverter {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Mappings, Function<MultipartFile, Optional<Resource>>> converters;


    public PdfConverter(){
        converters = new HashMap<>();
        converters.put(PDF2WORD, this::convertPdfToWord);
        converters.put(PDF2PPT, this::convertPdfToPpt);
        converters.put(PDF2ZIP, this::convertPdfToZip);
        converters.put(IMAGE2PDF,this::convertImageToPdf);
        converters.put(ROTATE_PDF,this::rotatePdf);
        converters.put(NUMBER_PAGES,this::numberPages);
        converters.put(ENCRYPT,this::encryptPdf);

    }

    @Override
    protected Optional<Resource> convert(Request source)  {
        Mappings mapping = source.getMapping();
        MultipartFile pdf = source.getSource();
        return converters.get(mapping).apply(pdf);
    }

    private Optional<Resource> convertPdfToWord(MultipartFile pdf){
        Resource resource = null;
        String fileName = getRandomFileName(DOCX);


        try (InputStream inps = pdf.getInputStream()){


            InputStream rss = new FileInputStream(fileName);
            resource = new InputStreamResource(rss);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        return Optional.ofNullable(resource);
    }

    private Optional<Resource> convertPdfToPpt(MultipartFile pdf){
        return Optional.empty();
    }
    private Optional<Resource> convertPdfToZip(MultipartFile zip){
        return Optional.empty();
    }

    private Optional<Resource> convertImageToPdf(MultipartFile Image){
        Resource resource = null;
        String fileName = getRandomFileName(PDF);
        

        return Optional.ofNullable(resource);
    }
    private Optional<Resource> rotatePdf (MultipartFile rotate){
        return  Optional.empty();
    }
    private Optional<Resource> numberPages (MultipartFile number) {
        return Optional.empty();
    }
    private Optional<Resource> encryptPdf (MultipartFile encrypt) {
        return Optional.empty();
    }
}
