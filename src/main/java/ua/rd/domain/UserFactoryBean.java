package ua.rd.domain;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import ua.rd.domain.User;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User((Tweet) null);
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
