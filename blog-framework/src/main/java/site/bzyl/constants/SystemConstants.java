package site.bzyl.constants;

public class SystemConstants {
    // 文章发布状态
    public static final Integer ARTICLE_STATUS_DRAFT = 0;
    public static final Integer ARTICLE_STATUS_PUBLISHED = 1;
    // 分类列表状态
    public static final Integer CATEGORY_STATUS_DISABLED = 0;
    public static final Integer CATEGORY_STATUS_ENABLED = 1;


    // 分页查询
    public static final Integer HOT_ARTICLE_LIST_CURRENT = 1;
    public static final Integer HOT_ARTICLE_LIST_SIZE_PER_PAGE = 10;

    // 友链状态： 可用、不可用、待审核
    public static final Integer LINK_STATUS_AVAILABLE = 0;
    public static final Integer LINK_STATUS_UNAVAILABLE = 1;
    public static final Integer LINK_STATUS_PENDING = 2;
}
