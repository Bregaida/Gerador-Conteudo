package util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Utilities to manipulate reflection.
 * 
 * @author alexandre.barboza
 */
public class ReflectionUtil
{

    /**
     * Returns the getter method name from a {@link Field}
     * 
     * @param field
     * @return
     */
    public static String getFieldGetter(Field field)
    {
        return "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    /**
     * Returns the boolean getter method name from a {@link Field}
     * 
     * @param field
     * @return
     */
    public static String getFieldBooleanGetter(Field field)
    {
        return "is" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    /**
     * Returns the setter method name from a {@link Field}
     * 
     * @param field
     * @return
     */
    public static String getFieldSetter(Field field)
    {
        return "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
    }

    /**
     * Returns the {@link Method} from a {@link Field} into a given {@link Class}.
     * 
     * @param field
     * @param clazz
     * @return <code>null</code> if the {@link Method} cannot be found.
     */
    @SuppressWarnings("all")
    public static Method findGetterMethod(Field field, Class<?> clazz)
    {
        Method method = null;
        String methodName = getFieldGetter(field);
        try
        {
            method = clazz.getDeclaredMethod(methodName, null);
        }
        catch (NoSuchMethodException e)
        {
            methodName = getFieldBooleanGetter(field);
            try
            {
                method = clazz.getDeclaredMethod(methodName, null);
            }
            catch (NoSuchMethodException ee)
            {
                Class<?> currentClass = clazz.getSuperclass();
                if (currentClass != null)
                {
                    method = findGetterMethod(field, currentClass);
                }
                else
                {
                    method = null;
                }
            }
        }
        return method;
    }

    /**
     * Returns the {@link Method} from a {@link Field} into a given {@link Class}.
     * 
     * @param field
     * @param clazz
     * @param parameterTypes
     *            Class types parameters of the setter method.
     * @return <code>null</code> if the {@link Method} cannot be found.
     */
    public static Method findSetterMethod(Field field, Class<?> clazz, Class<?>... parameterTypes)
    {
        Method method = null;
        String methodName = getFieldSetter(field);
        try
        {
            method = clazz.getDeclaredMethod(methodName, parameterTypes);
        }
        catch (NoSuchMethodException e)
        {
            method = null;
        }
        return method;
    }

    /**
     * Get an object value into an entity by the getter method given a field.
     * 
     * @param field
     * @param entity
     * @param clazz
     * @return
     * @throws Exception
     */
    @SuppressWarnings("all")
    public static Object getObjectValue(Field field, Object entity, Class<?> clazz) throws Exception
    {
        Object value = null;

        Method method = findGetterMethod(field, clazz);
        if (method != null)
        {
            value = method.invoke(entity, null);
        }

        return value;
    }

    /**
     * Set an object value into an entity by the setter method given a field.
     * 
     * @param field
     * @param entity
     * @param value
     * @param parameterTypes
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public void setObjectValue(Field field, Object entity, Object value, Class<?>... parameterTypes) throws IllegalAccessException,
            InvocationTargetException
    {
        Method method = findSetterMethod(field, entity.getClass(), parameterTypes);
        if (method != null)
        {
            method.invoke(entity, value);
        }
    }

    /**
     * Verify if the given class is a instance of a typed class.
     * 
     * @param thisClass
     * @param typeClass
     * @return
     */
    public static boolean instanceOf(Class<?> thisClass, Class<?> typeClass)
    {
        boolean retorno = false;
        if (thisClass.getCanonicalName().equals(typeClass.getCanonicalName()))
        {
            retorno = true;
        }
        else
        {
            thisClass = thisClass.getSuperclass();
            if (thisClass != null)
            {
                retorno = instanceOf(thisClass, typeClass);
            }
        }
        return retorno;
    }

    /**
     * Verify if the given class is a implementation of a typed class.
     * 
     * @param thisClass
     * @param typeClass
     * @return
     */
    public static boolean implementsOf(Class<?> thisClass, Class<?> typeClass)
    {
        boolean retorno = false;
        List<Class<?>> interfaces = Arrays.asList(thisClass.getInterfaces());
        if (interfaces.contains(typeClass))
        {
            retorno = true;
        }
        else
        {
            thisClass = thisClass.getSuperclass();
            if (thisClass != null)
            {
                retorno = implementsOf(thisClass, typeClass);
            }
        }
        return retorno;
    }

    /**
     * Find all fields, including super classes, from a given class.
     * 
     * @param thisClass
     * @return
     */
    public static List<Field> findAllFields(Class<?> thisClass)
    {
        List<Field> fields = new LinkedList<Field>();
        fields.addAll(Arrays.asList(thisClass.getDeclaredFields()));
        Class<?> currentClass = thisClass.getSuperclass();
        if (currentClass != null)
        {
            fields.addAll(findAllFields(currentClass));
        }
        return fields;
    }

    /**
     * Get a field of a class by given a field name
     * 
     * @param thisClass
     * @param fieldName
     * @return
     */
    public static Field getField(Class<?> thisClass, String fieldName)
    {
        List<Field> fields = findAllFields(thisClass);
        for (Field field : fields)
        {
            if (field.getName().equals(fieldName))
            {
                return field;
            }
        }
        return null;
    }

}
