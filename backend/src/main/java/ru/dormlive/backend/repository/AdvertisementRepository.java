package ru.dormlive.backend.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import ru.dormlive.backend.model.Advertisement;

public interface AdvertisementRepository extends MongoRepository<Advertisement, ObjectId>, QuerydslPredicateExecutor<Advertisement> {
}
