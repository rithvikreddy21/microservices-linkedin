package com.rithvikreddy.linkedInProject.postsService.service;

import com.rithvikreddy.linkedInProject.postsService.dto.PostCreateRequestDto;
import com.rithvikreddy.linkedInProject.postsService.dto.PostDto;
import com.rithvikreddy.linkedInProject.postsService.entity.Post;
import com.rithvikreddy.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.rithvikreddy.linkedInProject.postsService.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    public PostDto createPost(PostCreateRequestDto postCreateRequestDto, Long userId) {
        log.info("Creating post for user with id: {}",userId);
        Post post = modelMapper.map(postCreateRequestDto, Post.class);
        post.setUserId(userId);
        post = postRepository.save(post);
        return modelMapper.map(post, PostDto.class);
    }

    public PostDto getPostById(Long postId) {
        log.info("getting the post by id: {}",postId);
        Post post = postRepository.findById(postId).orElseThrow(
                ()-> new ResourceNotFoundException("Resource not found with postId: "+postId)
        );
        return modelMapper.map(post,PostDto.class);
    }

    public List<PostDto> getAllPostsOfUser(Long userId) {
        log.info("Getting all posts by the user: {}",userId);
        List<Post> postList = postRepository.findByUserId(userId);
        return postList
                .stream()
                .map((element) -> modelMapper.map(element, PostDto.class))
                .collect(Collectors.toList());
    }
}
