package com.project.potteryshop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.potteryshop.Entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, String> {
    Image findImageByLinkImage(String linkImage);

    Image findByProductId(String productId);
}
