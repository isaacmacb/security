package tech.buildrun.springsecurity.repository;

import org.springframework.stereotype.Repository;
import tech.buildrun.springsecurity.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

@Repository
public interface TweetRepository extends JpaRepository<Tweet, Long> {

}
