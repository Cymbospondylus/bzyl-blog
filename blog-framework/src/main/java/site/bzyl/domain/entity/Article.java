package site.bzyl.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;



@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("bzyl_article")
@Accessors(chain = true) // 让setter方法有返回值, 实现链式编程
public class Article {
    @TableId
    private Long id;
    //标题
    private String title;
    //文章内容
    private String content;
    //文章摘要
    private String summary;
    //所属分类id
    private Long categoryId;
    // 实体类中不存在于表的属性, 不加注解会报错
    @TableField(exist = false)
    private String categoryName;
    //缩略图
    private String thumbnail;
    //是否置顶（1否，0是）
    private String isTop;
    //状态（1已发布，0草稿）
    private Integer status;
    //访问量
    private Long viewCount;
    //是否允许评论 1是，0否
    private Integer isComment;
    
    private Long createBy;
    
    private Date createTime;
    
    private Long updateBy;
    
    private Date updateTime;
    //删除标志（1代表未删除，0代表已删除）
    private Integer delFlag;

}

