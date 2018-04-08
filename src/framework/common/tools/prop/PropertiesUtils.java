package framework.common.tools.prop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Properties的功能类
 * 
 * @author jinni 2007-2-28
 */
public class PropertiesUtils
{
    /**
     * Logger for this class
     */

    private static final String FileNotFoundException = "找不到属性配置文件";

    private static final String LoadPropertiesException = "加载属性文件时出现异常";

    private static final String CloseIOException = "关闭文件流时出现异常";
    
    public  static final Map<String, Properties> PROPERTIES_MAP = new HashMap<String, Properties>();
    
    public static final String  IP_ADDRESS = "ipAddress";
    
    /**
     * 根据类所在的位置获取相应的属性文件
     * 
     * @param loader 类加载器
     * @param name 属性文件名
     * @return Properties 如果在读取属性文件过程中出错,则返回null值
     */
    public static Properties getProperties(ClassLoader loader, String name)
    {
        Properties properties = PROPERTIES_MAP.get(name);
        InputStream inputStream = null;
    	inputStream = PropertiesUtils.class.getResourceAsStream("/" + name);
        if(properties==null)
        {
            properties = PropertiesUtils.getPropertiesByInput(inputStream);
            if(properties==null)
            {
                throw  new NullPointerException("资源文件没有定义【"+name+"】");
            }
            PROPERTIES_MAP.put(name, properties);
        }
        return properties;
    }
    
    /**
     * 根据类所在的位置获取相应的属性文件
     * 
     * @param clazz 相应类
     * @param name 属性文件名
     * @return Properties 如果在读取属性文件过程中出错,则返回null值
     */
    public static Properties getProperties(Class clazz, String name)
    {
        return PropertiesUtils.getProperties(clazz.getClassLoader(),name);
    }

    /**
     * 根据类所在的位置获取相应的属性文件
     * @param name 属性文件名
     */
    public static Properties getProperties(String name)
    {
        return  PropertiesUtils.getProperties(PropertiesUtils.class.getClassLoader(),name);
    }

    /**
     * 根据文件全名路径获取相应的属性文件
     * 
     * @param path 文件全路径名
     * @return Properties 如果在读取属性文件过程中出错,则返回null值
     */
    public static Properties getPropertiesByPath(String path)
    {
        FileInputStream in = null;
        try
        {
            in = new FileInputStream(new File(path));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        try
        {
            Properties prop = new UTF8Properties();
            prop.load(in);
            return prop;
        }
        catch (IOException e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (in != null)
                {
                    in.close();
                }
            }
            catch (IOException e)
            {
            }
        }
    }

    /**
     * 根据文件全名路径获取相应的属性文件
     * 
     * @param path 文件全路径名
     * @return Properties 如果在读取属性文件过程中出错,则返回null值
     */
    private static Properties getPropertiesByInput(InputStream inputStream)
    {
        try
        {
            Properties prop = new UTF8Properties();
            prop.load(inputStream);
            return prop;
        }
        catch (IOException e)
        {
            return null;
        }
        finally
        {
            try
            {
                if (inputStream != null)
                {
                	inputStream.close();
                }
            }
            catch (IOException e)
            {
            }
        }
    }
    
    /**
     * 读取resource.properties 文件中资源值
     * 
     * @param key
     * @return String
     */
    public static String getDefaultPropertiesValue(String key)
    {
        String value = PropertiesUtils.getPropertiesByPath("E:\\tmp\\resource.properties").getProperty(key);
        if(value == null)
        {
            return "";
        }
        return value;
    }
    
    /**
     * 读取resource.properties 文件中资源值
     * 
     * @param key
     * @return String
     */
    public static void setDefaultPropertiesValue(String key, String value)
    {
    	PropertiesUtils.getPropertiesByPath("E:\\tmp\\resource.properties").setProperty(key, value);
    }
}
