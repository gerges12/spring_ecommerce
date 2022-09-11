package com.luv2code.ecommerce.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.ecommerce.dao.ProductRepository;
import com.luv2code.ecommerce.dao.VoteRepository;
import com.luv2code.ecommerce.dto.VoteDto;
import com.luv2code.ecommerce.entity.Product;
import com.luv2code.ecommerce.entity.User;
import com.luv2code.ecommerce.entity.Vote;
import com.luv2code.ecommerce.entity.VoteType;
import com.luv2code.ecommerce.exceptions.ProductNotFoundException;
import com.luv2code.ecommerce.exceptions.ProductNotHaingCommentsException;
import com.luv2code.ecommerce.exceptions.voteException;

@Service
public class VoteService {

	@Autowired
	VoteRepository   voteRepository  ;
	
	@Autowired
	ProductRepository  productrepository  ;
	
	@Autowired
	AuthService authService ;

	public String statusmessage;
	
	public void vote(VoteDto voteDto) {
		
		Vote vote =  new Vote()  ;
		
		
		List<VoteType> choices = Arrays.asList(VoteType.values());

		
		Product product = productrepository.findById(voteDto.getProduct_id())
		         .orElseThrow(  
       () -> new ProductNotFoundException("product id not found " )) ;
		
		User user =  authService.getCurrentUser()  ;
		
		Vote oldvote =voteRepository.findByProductAndUser(product, user)  ;
		
		
		
		  if (oldvote!= null && oldvote.getVoteType() == voteDto.getVoteType() && voteDto.getVoteType() !=VoteType.DELETE ) //  if user has been already reacted then send the same react
		{
			throw new voteException("you have been reacted with " + voteDto.getVoteType() ) ;
		}
		
	
		
		
		else if (oldvote!= null && oldvote.getVoteType() != voteDto.getVoteType() && voteDto.getVoteType() !=VoteType.DELETE ) // if user changed the reaction
		{
			if(voteDto.getVoteType()== VoteType.LIKE  )
			{
				int l = product.getLikes()  ;
				int dl = product.getDislikes()  ;
				
				product.setDislikes(dl-1);
				product.setLikes(l+1);
				
				productrepository.save(product)  ;
			}
			
			else if(voteDto.getVoteType()== VoteType.DISLIKE  )
			{
				int l = product.getLikes()  ;
				int dl = product.getDislikes()  ;
				
				product.setDislikes(dl+1);
				product.setLikes(l-1);
				
				productrepository.save(product)  ;
			}
			
			oldvote.setVoteType(voteDto.getVoteType());
			
			
			voteRepository.save(oldvote)  ;
			statusmessage=  "you changed your react"  ;
			
		}
		
	
		else if (oldvote== null  && voteDto.getVoteType() !=VoteType.DELETE )   // if user reacted with first time
		{
			int l = product.getLikes()  ;
			int dl = product.getDislikes()  ;

			
			if(voteDto.getVoteType()== VoteType.LIKE  )
			{
				product.setLikes(l+1);
				System.out.println("likeeeee");

			}
			
			else if(voteDto.getVoteType()== VoteType.DISLIKE  )
			{
				product.setDislikes(dl+1);

				System.out.println("dislikeeeee");

			}

			
			productrepository.save(product)  ;
			
			vote.setProduct(product);
			vote.setUser(user);
			
			vote.setVoteType(voteDto.getVoteType());
			
			
			voteRepository.save(vote)  ;
			statusmessage= "you have react with " + voteDto.getVoteType()  ;

		}
		  
		else if (oldvote != null && voteDto.getVoteType() == VoteType.DELETE ) {
			
			
			
			
			if(oldvote.getVoteType()== VoteType.LIKE  )
			{
				int l = product.getLikes()  ;
				
				product.setLikes(l-1);
				
				productrepository.save(product)  ;
			}
			
			else if(oldvote.getVoteType()== VoteType.DISLIKE  )
			{
				int dl = product.getDislikes()  ;
				
				product.setDislikes(dl-1);
				
				productrepository.save(product)  ;
			}
			
			voteRepository.delete(oldvote);
			statusmessage= "you have deleting your react"  ;

		}
		
		
		
	}

}
