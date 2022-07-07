package site.bzyl.utils;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BeanCopyUtils {
    // 工具类Utils隐藏构造器
    private BeanCopyUtils(){
    }

    public static <T> T copyBean(Object src, Class<T> clazz) {
        // 创建目标对象
        T res = null;
        try {
            // 利用反射创建对象
            res = clazz.newInstance();
            // 调用Spring的工具类进行Bean拷贝
            BeanUtils.copyProperties(src, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    // 函数式编程 + 泛型方法, 没有T.class, 是Class<T>
    public static <S, T> List<T> copyBeanList(List<S> src, Class<T> clazz) {
        return src.stream()
                .map(s -> copyBean(s, clazz))
                .collect(Collectors.toList());
    }
}
