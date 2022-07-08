package site.bzyl.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVo<T> {
    // 分页数据
    private List<T> rows;
    // 数据总数
    private Long total;
}
