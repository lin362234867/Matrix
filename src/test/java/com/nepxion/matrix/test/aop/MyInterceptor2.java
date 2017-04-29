package com.nepxion.matrix.test.aop;

/**
 * <p>Title: Nepxion Matrix</p>
 * <p>Description: Nepxion Matrix AOP</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @email 1394997@qq.com
 * @version 1.0
 */

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import com.nepxion.matrix.aop.AbstractInterceptor;
import com.nepxion.matrix.util.MatrixUtils;

@Component("myInterceptor2")
public class MyInterceptor2 extends AbstractInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Class<?> proxyClass = invocation.getClass();
        String proxyClassName = proxyClass.getCanonicalName();
        Object proxiedObject = invocation.getThis();
        // Object[] arguments = invocation.getArguments();
        Class<?> proxiedClass = proxiedObject.getClass();
        String proxiedClassName = proxiedClass.getCanonicalName();
        Annotation[] classAnnotations = proxiedClass.getAnnotations();

        Method method = invocation.getMethod();
        String methodName = method.getName();
        Annotation[] methodAnnotations = method.getAnnotations();

        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("My Interceptor 2 :");
        System.out.println("   proxyClassName=" + proxyClassName);
        System.out.println("   className=" + proxiedClassName);
        System.out.println("   classAnnotations=");
        for (Annotation classAnnotation : classAnnotations) {
            System.out.println("      " + classAnnotation.toString());
        }

        if (proxiedClass.getInterfaces() != null) {
            for (Class<?> proxiedInterface : proxiedClass.getInterfaces()) {
                System.out.println("   interfaceName=" + proxiedInterface.getCanonicalName());
                System.out.println("   interfaceAnnotations=");
                for (Annotation interfaceAnnotation : proxiedInterface.getAnnotations()) {
                    System.out.println("      " + interfaceAnnotation.toString());
                }
            }
        }

        System.out.println("   methodName=" + methodName);
        System.out.println("   methodAnnotations=");
        for (Annotation methodAnnotation : methodAnnotations) {
            System.out.println("      " + methodAnnotation.toString());
        }

        String parameterAnnotationValue = MatrixUtils.getValueByParameterAnnotation(invocation, MyAnnotation5.class, String.class);
        System.out.println("   parameterAnnotation[MyAnnotation5] value=" + parameterAnnotationValue);

        System.out.println("------------------------------------------------------------------------------------------");

        return invocation.proceed();
    }
}