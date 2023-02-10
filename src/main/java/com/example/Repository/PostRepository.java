package com.example.Repository;

import com.example.entity.Post;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllByUserOrderByCreateDateDesc(User users);

    List<Post> findAllByUserByCreatedDateDesc();

    Optional<Post> findPostByIdAndUser(Long id, User users);
}
