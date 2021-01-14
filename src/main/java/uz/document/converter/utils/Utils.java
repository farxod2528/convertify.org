package uz.document.converter.utils;

import uz.document.converter.constant.Extensions;

import java.util.UUID;

import static uz.document.converter.constant.AppConstants.FILE_LOCATION;

public class Utils {

    public static String getRandomFileName(Extensions extension){
        StringBuilder builder = new StringBuilder();
        builder
                .append(FILE_LOCATION)
                .append("\\")
                .append(UUID.randomUUID()
                .toString()
                .replaceAll("-","")
                , 0, 16)
                .append(extension.getExtension());
        return builder.toString();
    }
}
