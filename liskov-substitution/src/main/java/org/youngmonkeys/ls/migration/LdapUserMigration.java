package org.youngmonkeys.ls.migration;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

public class LdapUserMigration extends Migrator {

    @Override
    protected void doMigrate() {
        long lastMigratedUserId = getLastMigratedLdapUserId();
        List<LdapUser> ldapUsers = getLdapUsers(
            lastMigratedUserId
        );
        if (ldapUsers.isEmpty()) {
            return;
        }
        ldapUsers
            .stream()
            .filter(
                it -> !containsUserByLdapUserId(
                    it.getId()
                )
            )
            .map(this::convertLdapUserToUser)
            .forEach(this::saveUser);
        saveLastMigratedLdapUserId(
            ldapUsers.get(
                ldapUsers.size() - 1
            ).getId()
        );
    }

    private long getLastMigratedLdapUserId() {
        return 0;
    }

    public void saveLastMigratedLdapUserId(
        long lastMigratedLdapUserId
    ) {}

    private List<LdapUser> getLdapUsers(
        long lastMigratedUserId
    ) {
        return new ArrayList<>();
    }

    private boolean containsUserByLdapUserId(
        long ldapUserId
    ) {
        return false;
    }

    private User convertLdapUserToUser(
        LdapUser ldapUser
    ) {
        return new User();
    }

    private void saveUser(User user) {}

    public static class User {}

    @Getter
    public static class LdapUser {
        private long id;
    }
}
