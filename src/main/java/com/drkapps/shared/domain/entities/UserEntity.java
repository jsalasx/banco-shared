package com.drkapps.shared.domain.entities;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;

@MongoEntity(collection = "users")
public class UserEntity extends PanacheMongoEntity {

    public String name;
    public String email;
    public String password;
    public String phone;
    public String address;
}
