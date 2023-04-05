package org.youngmonkeys.dyc.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public final class ReflectionObjects {

    private ReflectionObjects() {}

    public static boolean isEmptyObject(Object obj) {
        return getObjectProperties(
            obj,
            true
        ).isEmpty();
    }

    public static Map<String, Object> getObjectProperties(
        Object obj
    ) {
        return getObjectProperties(obj, false);
    }

    public static Map<String, Object> getObjectProperties(
        Object obj,
        boolean onlyTakeFirstValue
    ) {
        Map<String, Object> properties = new HashMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())
                || Modifier.isFinal(field.getModifiers())
                || Modifier.isTransient(field.getModifiers())
            ) {
                continue;
            }
            Object value;
            try {
                if (Modifier.isPublic(field.getModifiers())) {
                    value = field.get(obj);
                } else {
                    field.setAccessible(true);
                    value = field.get(obj);
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            if (value != null) {
                properties.put(field.getName(), value);
                if (onlyTakeFirstValue) {
                    break;
                }
            }
        }
        return properties;
    }
}
