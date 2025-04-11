package com.rithvikreddy.linkedInProject.postsService.service;

import com.rithvikreddy.linkedInProject.postsService.entity.Post;
import com.rithvikreddy.linkedInProject.postsService.entity.PostLike;
import com.rithvikreddy.linkedInProject.postsService.exception.BadRequestException;
import com.rithvikreddy.linkedInProject.postsService.exception.ResourceNotFoundException;
import com.rithvikreddy.linkedInProject.postsService.repository.PostLikeRepository;
import com.rithvikreddy.linkedInProject.postsService.repository.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void likePost(Long postId) {
        Long userId = 1L;
        log.info("User with ID: {} liked the post with ID:{}",userId,postId);
        Post post = postRepository.findById(postId).orElseThrow(()
                            ->new ResourceNotFoundException("Post not found with ID: "+postId));
        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(hasAlreadyLiked)
            throw new BadRequestException("post is already liked");
        PostLike postLike = new PostLike();
        postLike.setPostId(postId);
        postLike.setUserId(userId);
        postLikeRepository.save(postLike);

        // TODO: send notification to owner when someone likes the post
    }

    @Transactional
    public void unlikePost(Long postId) {
        Long userId = 1L;
        log.info("User with ID: {} liked the post with ID:{}",userId,postId);
        Post post = postRepository.findById(postId).orElseThrow(()
                ->new ResourceNotFoundException("Post not found with ID: "+postId));
        boolean hasAlreadyLiked = postLikeRepository.existsByUserIdAndPostId(userId,postId);
        if(!hasAlreadyLiked)
            throw new BadRequestException("can't unlike post which is already liked");
        postLikeRepository.deleteByUserIdAndPostId(userId,postId);
    }
}
