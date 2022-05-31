package com.example.domain.usecase

import com.example.domain.repository.Repository
import javax.inject.Inject

class GetPostCommentsUseCase @Inject constructor(
    private val repository: Repository
) {
}