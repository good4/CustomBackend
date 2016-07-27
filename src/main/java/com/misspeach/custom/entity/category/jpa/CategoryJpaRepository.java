package com.misspeach.custom.entity.category.jpa;

import com.misspeach.custom.entity.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by shizhan on 16/7/26.
 */
public interface CategoryJpaRepository extends JpaRepository<Category,Long>{

    @Query("select c.category_image from Category c where c.category_name=?1")
    String findImageBycategory(String category_name);
}
