//package ch.oliumbi.sqllib;
//
//import java.lang.reflect.AnnotatedType;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Type;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.Arrays;
//import java.util.List;
//
//public class ResultHandler {
//
//    public void handle(Object object) {
////        ResultSet resultSet = null;
////
////        try {
////            PreparedStatement prepareStatement = connection.prepareStatement(query);
////            resultSet = prepareStatement.executeQuery();
////        } catch (SQLException e) {
////            throw new RuntimeException(e);
////        }
//
//        Method[] methods = object.getClass().getDeclaredMethods();
//
//        object.getClass().getDeclaredMethod()
//
//        Method[] getters = Arrays.stream(methods).filter(method -> {
//            return method.getName().startsWith("get");
//        }).toArray(Method[]::new);
//
//        for (Method getter : getters) {
//            System.out.println(getter.getName());
//
//            AnnotatedType[] annotatedTypes = getter.getAnnotatedParameterTypes();
//
//            for (AnnotatedType annotatedType : annotatedTypes) {
//                Type type = annotatedType.getType();
//                System.out.println(type.getTypeName());
//            }
//
//
////            try {
////                method.invoke(object, "test");
////            } catch (IllegalAccessException | InvocationTargetException e) {
////                throw new RuntimeException(e);
////            }
//
////            method.invoke(object, resultSet.get)
//        }
//
//
//    }
//}
