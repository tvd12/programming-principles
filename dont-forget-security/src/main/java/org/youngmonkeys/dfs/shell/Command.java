package org.youngmonkeys.dfs.shell;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Command {
    private String command;
    private String[] arguments;
}
