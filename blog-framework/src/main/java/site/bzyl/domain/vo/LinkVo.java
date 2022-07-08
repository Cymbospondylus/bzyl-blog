package site.bzyl.domain.vo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 友链(Link)表实体类
 *
 * @author makejava
 * @since 2022-07-08 21:21:30
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("bzyl_link")
public class LinkVo {
    @TableId
    private Long id;

    private String name;
    
    private String logo;
    
    private String description;
    //网站地址
    private String address;

}
