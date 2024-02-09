package com.sofa.cinema.tools;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
public class AssemblyScanner {
    public static <T> List<T> getInstancesOfType(Class<T> type) {
        List<T> instances = new ArrayList<>();
        Class<?> assignableType = type;

        for (Class<?> scannedType : getScannedTypes()) {
            if (assignableType.isAssignableFrom(scannedType) && scannedType.isAssignableFrom(Object.class)) {
                try {
                    instances.add(type.cast(scannedType.getDeclaredConstructor().newInstance()));
                } catch (Exception e) {
                    // Handle instantiation exception (e.g., IllegalAccessException, InstantiationException)
                    e.printStackTrace();
                }
            }
        }

        return instances;
    }

    private static List<Class<?>> getScannedTypes() {
        List<Class<?>> scanners = new ArrayList<>();

        for (Package pkg : Package.getPackages()) {
            try {
                Class<?>[] classes = Class.forName(pkg.getName()).getClasses();
                for (Class<?> clazz : classes) {
                    // Exclude interfaces, abstract classes, and system classes
                    if (!clazz.isInterface() && !Modifier.isAbstract(clazz.getModifiers())
                            && !clazz.getName().startsWith("java.") && !clazz.getName().startsWith("sun.reflect.generics")) {
                        scanners.add(clazz);
                    }
                }
            } catch (ClassNotFoundException e) {
                // Handle class not found exception
                e.printStackTrace();
            }
        }

        return scanners;
    }

}
