package org.micro.documentmanager.Constants;

public class Constants {
    public static final String ROLE_PREFIX = "ROLE_";
    public static final String AUTHORITY_DELIMITER = ",";
    public static final String USER_AUTHORITY = "document:read, document:create, document:update, document:delete";
    public static final String ADMIN_AUTHORITY = "document:read, document:create, document:update, document:delete, user:read, user:create, user:update";
    public static final String SUPER_ADMIN_AUTHORITY = "document:read, document:create, document:update, document:delete, user:read, user:create, user:update, user:delete";
    public static final String MANAGER_AUTHORITY = "document:read, document:create, document:update, document:delete";
}
