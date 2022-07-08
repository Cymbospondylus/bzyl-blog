package site.bzyl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

// 用View Object优化返回前端的数据, 避免展示null和敏感信息

/**
 * No serializer found for class site.bzyl.domain.vo.HotArticleListVo and no properties discovered to create BeanSerializer
 * 如果没有lombok的@Data注解, 缺少get方法就不能创建BeanSerializer, 不能进行序列化
 * 同时转json给前端需要get方法, 没有的话就会报这个错
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotArticleListVo implements Serializable {
    private Long id;
    //标题
    private String title;
    //访问量
    private Long viewCount;
}
