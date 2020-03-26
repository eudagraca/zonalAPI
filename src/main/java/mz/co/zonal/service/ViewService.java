package mz.co.zonal.service;

import mz.co.zonal.models.View;
import mz.co.zonal.repository.ProductRepository;
import mz.co.zonal.repository.UserRepository;
import mz.co.zonal.repository.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService implements ViewServiceImpl{

    @Autowired
    private ViewsRepository repository;

    @Autowired
    private ProductRepository product;

    @Autowired
    private UserRepository user;

    @Override
    public void view(Long productId, Long userId) {
        var view = repository.findByProductIdAndUserId(productId, userId);
        if (view == null){
            var userF = user.findUserById(userId);
            var productF = product.findOneById(productId);
            repository.save(new View(userF, productF));
            productF.setViewCount(productF.getViewCount()+1);
            product.save(productF);
        }
    }
}
