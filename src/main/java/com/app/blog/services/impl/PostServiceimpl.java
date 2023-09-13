package com.app.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Category;
import com.app.blog.entities.Post;
import com.app.blog.entities.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.PostDto;
import com.app.blog.repositories.CategoryRepo;
import com.app.blog.repositories.PostRepo;
import com.app.blog.repositories.UserRepo;
import com.app.blog.services.PostService;

@Service
public class PostServiceimpl implements PostService {

	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto  createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "user id", userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		
		Post post = this.modelMapper.map(postDto, Post.class);
		post.setImageName("deafault.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost = this.postRepo.save(post);

		return this.modelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
      Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post",	 "posId", postId));	
      post.setTitle(postDto.getTitle());
      post.setContent(postDto.getContent());
      post.setImageName(postDto.getImageName());
      
      Post updatedPost=this.postRepo.save(post);
      return this.modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void delete(Integer postid) {
		Post post = this.postRepo.findById(postid).orElseThrow(()-> new ResourceNotFoundException("Post", "postId", postid));
	    this.postRepo.delete(post);
	}

	@Override
	public List<PostDto> getAllPost() {
	List<Post> posts = this.postRepo.findAll();
	List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
			.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public PostDto getPostById(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
    PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto ;
		
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
		List<Post> posts = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

}
