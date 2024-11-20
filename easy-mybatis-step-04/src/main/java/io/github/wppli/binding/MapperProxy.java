package io.github.wppli.binding;

import io.github.wppli.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 映射器代理类:通过代理类包装对数据库的操作，目前我们本章节会先提供一个简单的包装，模拟对数据库的调用。
 * 之后对 MapperProxy 代理类，提供工厂实例化操作 MapperProxyFactory#newInstance，为每个 IDAO 接口生成代理类。
 *
 * @author li--jiaqiang 2024−11−19
 */
public class MapperProxy<T> implements InvocationHandler, Serializable {

    private static final long serialVersionUID = -6424540398559729838L;
    private static final Logger log = LoggerFactory.getLogger(MapperProxy.class);

    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    /**
     *
     * MapperProxy 负责实现 InvocationHandler 接口的 invoke 方法，最终所有的实际调用都会调用到这个方法包装的逻辑。
     * 实现 InvocationHandler#invoke 代理类接口，封装操作逻辑的方式，对外接口提供数据库操作对象。
     *
     * @param proxy the proxy instance that the method was invoked on
     *              调用该方法的代理实例
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *        method 的 {@code Method} 实例对应于在 proxy 实例上调用的接口方法。
     *        {@code Method} 对象的声明类将是声明方法的接口，该接口可以是代理类通过其继承方法的代理接口的超接口
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *        args 一个对象数组，其中包含在代理实例上的方法调用中传递的参数的值，
     *        如果接口方法不接受任何参数，则为 {@code null}。
     *        基元类型的参数包装在相应的基元包装类的实例中，
     *        例如 {@code java.lang.Integer} 或 {@code java.lang.Boolean}。
     *
     * @return return object
     * @throws Throwable error
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 要注意如果是 Object 提供的 toString、hashCode 等方法是不需要代理执行的，
        // 所以添加 Object.class.equals(method.getDeclaringClass()) 判断
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        } else {
            final MapperMethod mapperMethod = cachedMapperMethod(method);
            return mapperMethod.execute(sqlSession, args);
        }
    }

    /**
     * 去缓存中找MapperMethod
     */
    private MapperMethod cachedMapperMethod(Method method) {
        MapperMethod mapperMethod = methodCache.get(method);
        if (mapperMethod == null) {
            //找不到才去new
            mapperMethod = new MapperMethod(mapperInterface, method, sqlSession.getConfiguration());
            methodCache.put(method, mapperMethod);
        }
        return mapperMethod;
    }
}