package org.youngmonkeys.dfs.permission;

import com.tvd12.ezyfox.collect.Sets;
import com.tvd12.ezyhttp.server.core.annotation.Authenticated;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoGet;

import java.util.Set;

@Controller
@Authenticated
public class AdminsController {

    @DoGet("/admins")
    public Set<String> adminsGet() {
        return Sets.newHashSet("admin");
    }
}
