package com.alejofila.domain.usecase

import com.alejofila.domain.model.Movie
import io.reactivex.Single

interface GetMoviesUseCase{
    operator fun invoke(page: Int) : Single<List<Movie>>
}