package com.haanbhai.blogs.payloads;

import com.haanbhai.blogs.entities.Category;
import com.haanbhai.blogs.entities.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private String postTitle;
    private String content;
    private String imageName;
    private Date postCreated;
    private Category category;
    private User user;
}
