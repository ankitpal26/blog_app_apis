package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.PostDto;

public interface PostService {

	
//	create
	 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	 
//	 update
	 PostDto updatePost(PostDto postDto, Integer postId);
	 
//	 delete
	 void delete(Integer postId);
	 
//	 get all post
	 List<PostDto> getAllPost();
	 
//	 get single post
	 PostDto getPostById(Integer postId);
	 
//	 get all post by category
	 List<PostDto> getPostByCategory(Integer categoryId);
	 
//	 get all post by user
	 List<PostDto> getPostByUser(Integer userId);
	 
//     List<Post> searchPosts(String keyword);
}
