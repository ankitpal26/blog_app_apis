package com.app.blog.services;

import java.util.List;

import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;

public interface PostService {

	
//	create
	 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	 
//	 update
	 PostDto updatePost(PostDto postDto, Integer postId);
	 
//	 delete
	 void delete(Integer postId);
	 
//	 get all post
	 PostResponse getAllPost(Integer pageNumber, Integer pageSize);
	 
//	 get single post
	 PostDto getPostById(Integer postId);
	 
//	 get all post by category
	 PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize);
	 
//	 get all post by user
	 PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize);

//	 search post
	 
     List<PostDto> searchPosts(String keyword);
}
