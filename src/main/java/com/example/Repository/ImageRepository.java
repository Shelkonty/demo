package com.example.Repository;

import com.example.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.beans.JavaBean;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {


    Optional<Image> findByUserId(Long userId);

    Optional<Image> findByPostId(Long postId);

}
