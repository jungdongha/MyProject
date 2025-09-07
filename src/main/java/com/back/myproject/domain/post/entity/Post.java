package com.back.myproject.domain.post.entity;

import com.back.myproject.domain.user.entity.User;
import com.back.myproject.global.jpa.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;


@Entity
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "post")
public class Post extends BaseEntity {

    @Column(name = "post_title", length = 100, nullable = false)
    private String title;

    @Column(name = "post_content", nullable = false)
    @Lob
    private String content;

    @Column(name = "post_image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments= new ArrayList<>();




    @Builder
    public Post(String title, String content,String imageUrl ,User user) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.user = user;
    }
}
