package ru.pizza.products.utils;

import org.springframework.web.multipart.MultipartFile;
import ru.pizza.products.entities.ImageProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class ImageUtil implements StaticURL {

    //todo Рефакторинг
    public static String createFile(ImageProduct imageProduct) {
        File mFile =
                new File(SOURCE_EMPLOYEE_DIR_URL +
                        BASE_EMPLOYEE_DIR_URL +
                        generateUniqueFileName(imageProduct.getType()));
        if (!mFile.exists()) {
            try {
                mFile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try (FileOutputStream fos = new FileOutputStream(mFile)) {

                fos.write(imageProduct.getFile());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return BASE_EMPLOYEE_DIR_URL + mFile.getName();
    }

    public static void delete(String url) {
        new File(url).delete();
    }

    private static String generateUniqueFileName(String type) {
        String s;
        if (type.equals("image/jpg") || type.equals("image/jpeg")) {
            s = ".jpg";
        } else {
            s = ".png";
        }
        return "ImageMenu-" + UUID.randomUUID() + s;
    }
}