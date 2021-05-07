package com.example.restfulwebservice.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users =  new ArrayList<>();

    private static int usersCount = 3;

    static {
        users.add(new User(1, "boyung"));
        users.add(new User(2, "minsuk"));
        users.add(new User(3, "yon"));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId()==null){
            user.setId(++usersCount);
        }
        users.add(user);
        return user;
    }

    public User findOne(int num){
        for(User user: users){
            if(user.getId()==num){
                return user;
            }
        }
        return null;
    }

    public User deleteByNum(int num){
        Iterator<User> iterator = users.iterator();
        while(iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == num){
                iterator.remove();
                return user;
            }

        }
        return null;
    }
}
