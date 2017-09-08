package com.shinemo.core.permission;

import com.shinemo.nsfw.user.entity.User;

/**
 * Created by pc on 2017/7/31.
 */
public interface PermissionCheck {

    public boolean isAccessible(User user, String code);
}
