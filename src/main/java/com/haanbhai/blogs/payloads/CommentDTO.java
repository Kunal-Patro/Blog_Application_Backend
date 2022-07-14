package com.haanbhai.blogs.payloads;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommentDTO {
    private Integer commentId;
    private String content;
    private UserDTO user;
}
