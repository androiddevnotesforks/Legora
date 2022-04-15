package com.apartments.api.models.repos

import com.apartments.api.models.entities.posts.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentsRepository: PagingAndSortingRepository<Comment, Long> {

    fun findAllByPostId(postId: Long, pageable: Pageable): Page<Comment>

}
