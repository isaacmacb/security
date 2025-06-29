package tech.buildrun.springsecurity.repository;

import tech.buildrun.springsecurity.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TweetRepository extends JpaRepository<Tweet, UUID> {

}
