package zw.co.santech.springmongo.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

public class OrderedTestRunner extends BlockJUnit4ClassRunner {
    public OrderedTestRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    protected List<FrameworkMethod> computeTestMethods() {
        Map<FrameworkMethod, Integer> orders = new HashMap();
        List<FrameworkMethod> methods = super.computeTestMethods();
        int maxOrder = 0;
        Iterator var4 = methods.iterator();

        while(var4.hasNext()) {
            FrameworkMethod method = (FrameworkMethod)var4.next();
            Order order = (Order)method.getAnnotation(Order.class);
            maxOrder = Math.max(maxOrder, order == null ? 0 : order.value());
            orders.put(method, order == null ? null : order.value());
        }

        int order = maxOrder + 1;
        methods.forEach((methodx) -> {
            orders.computeIfAbsent(methodx, (value) -> {
                return order;
            });
        });
        return (List)methods.stream().sorted((f1, f2) -> {
            return (Integer)orders.get(f1) - (Integer)orders.get(f2);
        }).collect(Collectors.toList());
    }
}

