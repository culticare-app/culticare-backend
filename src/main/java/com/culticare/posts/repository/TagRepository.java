package com.culticare.posts.repository;

import com.culticare.posts.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<HashTag, Long> {

    Optional<HashTag> findByName(String name);
}
