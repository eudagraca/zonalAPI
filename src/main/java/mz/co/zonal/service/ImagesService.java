package mz.co.zonal.service;

import mz.co.zonal.models.Images;
import mz.co.zonal.repository.ImagesRepository;
import mz.co.zonal.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagesService implements ImagesServiceImpl {

    @Autowired
    private ImagesRepository repository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Images saveImage(Images images) {
        return repository.save(images);
    }

    public int deleteImages(Long id) {
        var product = productRepository.findOneById(id);
        return repository.deleteImagesByProductId(product);
    }
}
