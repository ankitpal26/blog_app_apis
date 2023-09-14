package com.app.blog.services.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.blog.entities.Category;
import com.app.blog.entities.Post;
import com.app.blog.entities.User;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
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
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize) {
		
	 Pageable p=PageRequest.of(pageNumber, pageSize);
		
	 Page<Post> pagePost = this.postRepo.findAll(p);
	 List<Post> posts=pagePost.getContent();;
	 
	 List<PostDto> postDtos=posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
			.collect(Collectors.toList());
	 
	 PostResponse postResponse=new PostResponse();
	 postResponse.setContent(postDtos);
	 postResponse.setPageNumber(pagePost.getNumber());
	 postResponse.setPageSize(pagePost.getSize());
	 postResponse.setTotalElements(pagePost.getTotalElements());
	 
	 postResponse.setTotalPages(pagePost.getTotalPages());
	 postResponse.setLastPage(pagePost.isLast());
		return postResponse ;
	}

	@Override
	public PostDto getPostById(Integer postId) {
    Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post", "post id", postId));
    PostDto postDto=modelMapper.map(post, PostDto.class);
		return postDto ;
		
	}

	@Override
	public PostResponse getPostByCategory(Integer categoryId,Integer pageNumber, Integer pageSize) {
	
		Pageable p= PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost=this.postRepo.findAll(p);
		List<Post> posts=pagePost.getContent();
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category", "category id", categoryId));
//		List<Post> posts = this.postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		 postResponse.setContent(postDtos);
		 postResponse.setPageNumber(pagePost.getNumber());
		 postResponse.setPageSize(pagePost.getSize());
		 postResponse.setTotalElements(pagePost.getTotalElements());
		 
		 postResponse.setTotalPages(pagePost.getTotalPages());
		 postResponse.setLastPage(pagePost.isLast());
		 
		 
		return postResponse ;
		
	
	}

	@Override
	public PostResponse getPostByUser(Integer userId,Integer pageNumber, Integer pageSize) {
		
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post> pagePost= this.postRepo.findAll(p);
		List<Post> posts =pagePost.getContent();
		
		
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "UserId", userId));
//		List<Post> posts1 = this.postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		PostResponse postResponse=new PostResponse();
		 postResponse.setContent(postDtos);
		 postResponse.setPageNumber(pagePost.getNumber());
		 postResponse.setPageSize(pagePost.getSize());
		 postResponse.setTotalElements(pagePost.getTotalElements());
		 
		 postResponse.setTotalPages(pagePost.getTotalPages());
		 postResponse.setLastPage(pagePost.isLast());
//		 
			return postResponse ;
		
	}

	@Override
	public List<PostDto> searchPosts(String keyword) {
		List<Post> posts = this.postRepo.findByTitle("%"+keyword+"%");
		List<PostDto> postDtos = posts.stream().map((post)-> this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postDtos;
	}
	

}
