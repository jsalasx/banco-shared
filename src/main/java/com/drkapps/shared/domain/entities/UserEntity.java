package com.drkapps.shared.domain.entities;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;


@MongoEntity(collection = "users")
public class UserEntity  {

    @BsonId
    public ObjectId id;

    public String name;
    public String email;
    public String password;
    public String phone;
    public String address;

}
