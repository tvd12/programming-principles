package org.youngmonkeys.dfs.shell;

import com.tvd12.ezyfox.util.EzyMapBuilder;
import com.tvd12.ezyhttp.server.core.annotation.Controller;
import com.tvd12.ezyhttp.server.core.annotation.DoPost;
import com.tvd12.ezyhttp.server.core.annotation.RequestBody;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;

@Controller
public class ShellController {

    @DoPost("/shell")
    public Map<String, String> shell(
        @RequestBody Command request
    ) throws Exception {
        String[] arguments = request.getArguments();
        String[] command = new String[1 + arguments.length];
        command[0] = request.getCommand();
        System.arraycopy(
            request.getArguments(),
            0,
            command,
            1,
            arguments.length
        );

        ProcessBuilder pb = new ProcessBuilder(command);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(
            new InputStreamReader(process.getInputStream())
        );
        StringBuilder output = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            output.append(line);
        }

        BufferedReader errReader = new BufferedReader(
            new InputStreamReader(process.getErrorStream())
        );
        StringBuilder error = new StringBuilder();
        while ((line = errReader.readLine()) != null) {
            error.append(line);
        }

        int exitCode = process.waitFor();
        return EzyMapBuilder.mapBuilder()
            .put("output", output)
            .put("error", error)
            .put("exitCode", exitCode)
            .toMap();
    }


    public interface CommandHandler {
        void execute(String[] arguments);
    }

    public static class HelloCommandHandler implements CommandHandler {
        @Override
        public void execute(String[] arguments) {
            System.out.println("hello");
        }
    }

    public static class WorldCommandHandler implements CommandHandler {
        @Override
        public void execute(String[] arguments) {
            System.out.println("world");
        }
    }

    private final Map<String, CommandHandler> handlerByCommandName =
        EzyMapBuilder.mapBuilder()
            .put("hello", HelloCommandHandler.class)
            .put("world", WorldCommandHandler.class)
            .toMap();

    @DoPost("/prevent-shell")
    public Map<String, String> preventShell(
        @RequestBody Command request
    ) {
        CommandHandler handler = handlerByCommandName.get(request.getCommand());
        if (handler != null) {
            handler.execute(request.getArguments());
            return Collections.singletonMap("ok", "true");
        }
        return Collections.singletonMap("ok", "false");
    }
}
