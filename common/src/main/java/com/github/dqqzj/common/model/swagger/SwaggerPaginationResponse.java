package com.github.dqqzj.common.model.swagger;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author qinzhongjian
 * @date created in 2018/6/26 11:56
 * @since 1.0.0
 */
@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SwaggerPaginationResponse<T> implements Serializable {

    private static final long serialVersionUID = 5753340671258665259L;

    @ApiModelProperty(value = "页号(客户端指定, 页号从1作为起始页)", required = true, example = "3")
    @JsonProperty("pageNum")
    private int pageNum;

    @ApiModelProperty(value = "页面大小(客户端指定)", required = true, example = "30")
    @JsonProperty("pageSize")
    private int pageSize;

    @ApiModelProperty(value = "实际页面大小", required = true, example = "18")
    @JsonProperty("size")
    private int size;

    @ApiModelProperty(value = "排序方向", example = "CREATE_TIME DESC")
    @JsonProperty("orderBy")
    private String orderBy;

    @ApiModelProperty(value = "当前页面第一个元素在数据库中的行号", example = "63")
    @JsonProperty("startRow")
    private int startRow;

    @ApiModelProperty(value = "当前页面最后一个元素在数据库中的行号", example = "81")
    @JsonProperty("endRow")
    private int endRow;

    @ApiModelProperty(value = "总的项目数", required = true, example = "81")
    @JsonProperty("total")
    private long total;

    @ApiModelProperty(value = "总页面数", required = true, example = "3")
    @JsonProperty("pages")
    private int pages;

    @ApiModelProperty(value = "详细信息", required = true)
    @JsonProperty("list")
    private List<T> list;

    @ApiModelProperty(value = "首页页号", required = true, example = "1")
    @JsonProperty("firstPage")
    private int firstPage;

    @ApiModelProperty(value = "上一页页号", required = true, example = "2")
    @JsonProperty("pre_page")
    private int prePage;

    @ApiModelProperty(value = "下一页页号", required = true, example = "3")
    @JsonProperty("nextPage")
    private int nextPage;

    @ApiModelProperty(value = "尾页页号", required = true, example = "3")
    @JsonProperty("lastPage")
    private int lastPage;

    @ApiModelProperty(value = "是否为第一页", required = true, example = "false")
    @JsonProperty("isFirstPage")
    private boolean isFirstPage;

    @ApiModelProperty(value = "是否为最后一页", required = true, example = "true")
    @JsonProperty("isLastPage")
    private boolean isLastPage;

    @ApiModelProperty(value = "是否有前一页", required = true, example = "true")
    @JsonProperty("hasPreviousPage")
    private boolean hasPreviousPage;

    @ApiModelProperty(value = "是否有下一页", required = true, example = "false")
    @JsonProperty("hasNextPage")
    private boolean hasNextPage;

    @ApiModelProperty(value = "导航页码数", required = true, example = "3")
    @JsonProperty("navigatePages")
    private int navigatePages;

    @ApiModelProperty(value = "所有导航页码数", required = true, example = "[1,2,3]")
    @JsonProperty("navigatepageNums")
    private int[] navigatepageNums;
}
