package zw.co.santech.springmongo.utils;

import java.util.*;
import java.util.stream.Collectors;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class OrderedTestRunner extends BlockJUnit4ClassRunner {
    public OrderedTestRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected List computeTestMethods() {
        Map<FrameworkMethod, Integer> orders = new HashMap();
        List<FrameworkMethod> methods = super.computeTestMethods();
        int maxOrder = 0;

        for (FrameworkMethod method : methods) {
            Order order = method.getAnnotation(Order.class);
            maxOrder = Math.max(maxOrder, order == null ? 0 : order.value());
            orders.put(method, order == null ? null : order.value());
        }

        int order = maxOrder + 1;
        methods.forEach((methodx) -> {
            orders.computeIfAbsent(methodx, (value) -> order);
        });
        return methods.stream().sorted(Comparator.comparingInt(orders::get)).collect(Collectors.toList());
    }
}

