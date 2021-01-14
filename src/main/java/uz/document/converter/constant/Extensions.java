package uz.document.converter.constant;

import uz.document.converter.service.impl.Image2Pdf;

public enum  Extensions {
    DOCX(".docx"),
    PDF(".pdf");

    private String extension;

    Extensions(final String extension){
        this.extension = extension;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
