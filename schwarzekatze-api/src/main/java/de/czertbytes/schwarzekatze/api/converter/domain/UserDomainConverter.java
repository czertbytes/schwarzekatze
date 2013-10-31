package de.czertbytes.schwarzekatze.api.converter.domain;

import com.google.common.collect.Lists;
import de.czertbytes.schwarzekatze.api.domain.User;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class UserDomainConverter {

    public User toApiUser(final de.czertbytes.schwarzekatze.core.domain.user.User coreUser) {
        final User apiUser = new User();

        apiUser.setId(coreUser.getId());
        apiUser.setUsername(coreUser.getUsername());
        apiUser.setEmail(coreUser.getEmail());

        return apiUser;
    }

    public List<User> toApiUsers(final Collection<de.czertbytes.schwarzekatze.core.domain.user.User> coreUsers) {
        final List<User> apiUsers = Lists.newArrayList();

        for (de.czertbytes.schwarzekatze.core.domain.user.User coreUser : coreUsers) {
            apiUsers.add(toApiUser(coreUser));
        }

        return apiUsers;
    }

    public de.czertbytes.schwarzekatze.core.domain.user.User toCoreUser(final User apiUser) {
        final de.czertbytes.schwarzekatze.core.domain.user.User coreUser = new de.czertbytes.schwarzekatze.core.domain.user.User();

        coreUser.setId(apiUser.getId());
        coreUser.setUsername(apiUser.getUsername());
        coreUser.setEmail(apiUser.getEmail());

        return coreUser;
    }

    public List<de.czertbytes.schwarzekatze.core.domain.user.User> toCoreUsers(final List<User> apiUsers) {
        final List<de.czertbytes.schwarzekatze.core.domain.user.User> coreUsers = Lists.newArrayList();

        for (User apiUser : apiUsers) {
            coreUsers.add(toCoreUser(apiUser));
        }

        return coreUsers;
    }
}
