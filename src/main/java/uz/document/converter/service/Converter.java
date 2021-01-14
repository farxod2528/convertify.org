package uz.document.converter.service;

import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.document.converter.constant.Mappings;
import uz.document.converter.exception.CustomException;
import uz.document.converter.model.Request;
import uz.document.converter.model.Response;
import uz.document.converter.service.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

import static uz.document.converter.constant.Mappings.*;

@Service
public class Converter {
    private final Map<Mappings, Function<Request, Response<Resource>>> mappings;

    private final AbstractConverter pdfConverter;
    private final AbstractConverter wordConverter;
    private final AbstractConverter excelConverter;
    private final AbstractConverter pptConverter;
    private final AbstractConverter pdf2zipConverter;
    private final AbstractConverter rotate_pdfConverter;
    private final AbstractConverter image2pdfConverter;
    private final AbstractConverter encryptPDF;
    private final AbstractConverter number_pagesPdf;
    private final AbstractConverter marge_pdf;
    private final AbstractConverter split_pdf;



    private final Environment env;


    public Converter(PdfConverter pdfConverter,
                     WordConverter wordConverter,
                     ExcelConverter excelConverter,
                     PptConverter pptConverter,
                     Pdf2Zip pdf2zipConverter,
                     RotatePdf rotate_pdfConverter,
                     Image2Pdf image2pdfConverter,
                     Encrypt encryptPDF,
                     NumberPages number_pagesPdf,
                     MargePdf marge_pdf,
                     SplitPdf split_pdf,
                     Environment env) {
        this.pdfConverter = pdfConverter;
        this.wordConverter = wordConverter;
        this.excelConverter = excelConverter;
        this.pptConverter = pptConverter;
        this.pdf2zipConverter = pdf2zipConverter;
        this.rotate_pdfConverter = rotate_pdfConverter;
        this.image2pdfConverter = image2pdfConverter;
        this.encryptPDF = encryptPDF;
        this.number_pagesPdf = number_pagesPdf;
        this.marge_pdf = marge_pdf;
        this.split_pdf = split_pdf;
        this.env = env;


        mappings = new HashMap<>();
        mappings.put(PDF2WORD, pdfConverter::process);
        mappings.put(PDF2PPT, pdfConverter::process);
        mappings.put(WORD, wordConverter::process);
        mappings.put(PPT, pptConverter::process);
        mappings.put(PDF2ZIP,pdf2zipConverter ::process);
        mappings.put(ROTATE_PDF,rotate_pdfConverter::process);
        mappings.put(IMAGE2PDF,image2pdfConverter::process);
        mappings.put(ENCRYPT,encryptPDF::process);
        mappings.put(NUMBER_PAGES,number_pagesPdf::process);
        mappings.put(MARGE_PDF, marge_pdf::process);
        mappings.put(SPLIT_PDF, split_pdf::process);
    }

    public Response<Resource> convertCorresponding(Request request) throws Exception{
        Mappings mapping = request.getMapping();
        MultipartFile source = Optional.of(request).map(Request::getSource).orElse(null);

        if (Objects.isNull(source) || source.isEmpty()){
            throw new CustomException(env.getProperty("converter.code.fail", Integer.class),
                    env.getProperty("converter.fail"));
        }

        return mappings.get(mapping).apply(request);
    }
}
