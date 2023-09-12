package com.app.blog.services;

import java.util.List;

import com.app.blog.entities.Post;
import com.app.blog.payloads.PostDto;

public interface PostService {

	
//	create
	 PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	 
//	 update
	 Post updatePost(PostDto postDto, Integer postId);
	 
//	 delete
	 void delete(Integer postid);
	 
//	 get all post
	 List<Post> getAllPost();
	 
//	 get single post
	 Post getPostById(Integer postId);
	 
//	 get all post by category
	 List<Post> getPostByCategory(Integer categoryId);
	 
//	 get all post by user
	 List<Post> getPostByUser(Integer userId);
	 
//     List<Post> searchPosts(String keyword);
}
