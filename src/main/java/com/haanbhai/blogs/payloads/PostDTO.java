package com.haanbhai.blogs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Integer postId;
    private String postTitle;
    private String content;
    private String imageName;
    private Date postCreated;
    private CategoryDTO category;
    private UserDTO user;
}
