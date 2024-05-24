package org.micro.documentmanager.Enumeration;


import static org.micro.documentmanager.Constants.Constants.*;


public enum Authority {
    USER(USER_AUTHORITY),
    ADMIN(ADMIN_AUTHORITY),
    SUPER_ADMIN(SUPER_ADMIN_AUTHORITY),
    MANAGER(MANAGER_AUTHORITY);

    public final String value;

    Authority(String value) {
        this.value = value;
    }
}
