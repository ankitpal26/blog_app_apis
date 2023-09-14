package com.app.blog.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.blog.config.AppConstants;
import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.PostDto;
import com.app.blog.payloads.PostResponse;
import com.app.blog.services.FileService;
import com.app.blog.services.PostService;

@RestController
@RequestMapping("/api/")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileService fileService;
	
	@Value("${project.image}")
	private String path;

	

//	create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(
			@RequestBody PostDto postDto,
			@PathVariable Integer userId,
			@PathVariable Integer categoryId
			)
	{
		PostDto createPost = this.postService.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createPost,HttpStatus.CREATED);
		
	}
	
//	update post
	
	@PutMapping("/posts/{postId}")
	public ResponseEntity<PostDto> updatePost(
			@RequestBody PostDto postDto,
			@PathVariable Integer postId
			){
		PostDto updatePost=this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
		
	}
	
//	get by user
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<PostResponse> getPostByUser(
			@PathVariable Integer userId,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize
			){
	
		PostResponse posts = this.postService.getPostByUser(userId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	
//	get by category
	
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<PostResponse> getPostByCategory(
			@PathVariable Integer categoryId,
			@RequestParam(value = "pageNumber",defaultValue = "0",required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize
			){
		
		PostResponse posts = this.postService.getPostByCategory(categoryId, pageNumber, pageSize);
		return new ResponseEntity<PostResponse>(posts,HttpStatus.OK);
	}
	

//	get all post
	@GetMapping("/posts")
	public ResponseEntity<PostResponse> getAllPosts(
			@RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
			@RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false)Integer pageSize
//			@RequestParam(value = "dir",defaultValue = AppConstants.SORT_DIR,required = false) Direction dir,
//			@RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy
			){
		  PostResponse postResponse = this.postService.getAllPost(pageNumber,pageSize);
		return  new ResponseEntity<PostResponse>(postResponse,HttpStatus.OK);
	}
	
//	get post by id
	@GetMapping("/posts/{postId}")
	public ResponseEntity<PostDto> getById(@PathVariable Integer postId){
		PostDto postDto =this.postService.getPostById(postId);
		return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
		
	}
	
//	delete post
	@DeleteMapping("/posts/{postId}")
	public ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
		this.postService.delete(postId);
        return new ResponseEntity<ApiResponse>(new ApiResponse("post is deleted successfully !!",true),HttpStatus.OK);		
	}
	
//	search post
	@GetMapping("/posts/search/{keywords}")
	public ResponseEntity<List<PostDto>> searchByPostTitle(
			@PathVariable String keywords
			){
		List<PostDto> result = this.postService.searchPosts(keywords);
		return new ResponseEntity<List<PostDto>>(result,HttpStatus.OK);
	}
	
//	post image upload
	@PostMapping("/post/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("image") MultipartFile image,
			@PathVariable Integer postId
			) throws IOException{
		PostDto postDto = this.postService.getPostById(postId);
		
		String fileName = this.fileService.uploadImage(path, image);
		postDto.setImageName(fileName);
	    PostDto	updatePost = this.postService.updatePost(postDto, postId);
		return new ResponseEntity<PostDto>(updatePost,HttpStatus.OK);
	}
	
	
}
