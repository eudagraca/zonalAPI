package mz.co.zonal.utils;

import mz.co.zonal.errors.ResourceNotFoundException;
import mz.co.zonal.models.Product;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ImageConverterToByte {
    public static ArrayList<Product> convertImages(List<Product> productList) {
        var products = new ArrayList<Product>();
        for (var prod : productList) {
            convertBase(prod);
            products.add(prod);
        }
        return products;
    }

    public static Product convertSingleProductImages(Product product){
        convertBase(product);
        return product;
    }

    private static void convertBase(Product product) {
        var images = new ArrayList<byte[]>();
        if (product.getImages() != null) {
            for (var image : product.getImages()) {
                if (image.getImagePath() != null) {
                    try {
                        String picPath = image.getImagePath();
                        Path path = Paths.get(picPath);
                        images.add(Files.readAllBytes(path));
                    } catch (IOException e) {
                        throw new ResourceNotFoundException(e.getMessage());
                    }
                }
            }
            product.setImagesByte(images);
        }
    }
}
