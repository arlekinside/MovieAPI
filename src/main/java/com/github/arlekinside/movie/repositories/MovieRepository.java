package com.github.arlekinside.movie.repositories;

import com.github.arlekinside.movie.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query("SELECT m FROM Movie m WHERE upper(m.title) = upper(:tittle)")
    List<Movie> findByTitle(String tittle);
}
