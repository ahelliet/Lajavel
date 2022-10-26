package lajavel;

import lajavel.facades.Log;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {

    public static String getMethod(Object obj, String methodName){
        String returnValue = null;

        try {
            Method method = obj.getClass().getMethod(methodName);
            method.setAccessible(true);
            returnValue = method.invoke(obj).toString();
        } catch (Exception e) {
            // Return error
            throw new RuntimeException(e);
        }
        return returnValue;
    }

    public static String getProperty(Object obj, String property){
        String returnValue = "";

        try {
            Field field = obj.getClass().getDeclaredField(property);
            field.setAccessible(true);
            returnValue = field.get(obj).toString();
        } catch (Exception e) {
            // Return error
            throw new RuntimeException("getProperty : " + e);
        }
        return returnValue;
    }

    public static String render(String viewName, Map.Entry<String, Object>... entries) {

        StringBuffer stringBuffer = new StringBuffer();
        String rawHtml = getViewContentFromName(viewName);
        Matcher matcher = Pattern.compile("\\{\\{(.*?)\\}\\}").matcher(rawHtml);
        Matcher listMatcher = Pattern.compile("\\{% for (\\w*) in (\\w*) %\\}\n"
                + "(.*)\n"
                + "\\s*\\{% endfor %\\}").matcher(rawHtml);


        listMatcher.find();
        Log.error(listMatcher.group(1));

        while (matcher.find()) {
            String rawStringOfAnObject = matcher.group(1).replaceAll("\\s+", "");
            String[] objectAndProperty = rawStringOfAnObject.split("\\.");
            String objectName = objectAndProperty[0];
            String propertyName = objectAndProperty[1];

            for(Map.Entry<String, Object> entry: entries) {

                if(entry.getKey().equals(objectName)) {

                    Boolean isMethod = false;
                    if (propertyName.endsWith(("()"))) {
                        isMethod = true;
                        propertyName = propertyName.replace("()", "");
                    }

                    if(!isMethod) {
                        Object propertyValue = getProperty(entry.getValue(), propertyName);
                        matcher.appendReplacement(stringBuffer, (String) propertyValue);
                        break;
                    } else {
                        Object methodValue = getMethod(entry.getValue(), propertyName);
                        matcher.appendReplacement(stringBuffer, (String) methodValue);
                        break;
                    }
                }
            }
        }
        matcher.appendTail(stringBuffer);

        return stringBuffer.toString();
    }

    private static String getViewContentFromName(String viewName) {
        URL resource = View.class.getClassLoader().getResource("views/" + viewName +".javel");

        if(resource == null) {
            throw new RuntimeException("File : " + viewName + " not found!");
        }

        try {
            return Files.readString(Path.of(resource.toURI()), StandardCharsets.UTF_8);
        } catch (URISyntaxException | IOException e) {
            throw  new RuntimeException(e);
        }
    }
}
