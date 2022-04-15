package com.apartments.api.models.repos

import com.apartments.api.models.entities.posts.Post
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PostsRepository: PagingAndSortingRepository<Post, Long> {

}