package com.ye.warehouse.entity;

/**
 * @PackageName:com.ye.warehouse.entity
 * @ClassName:Page
 * @Description:分页实体
 * @author:何进业
 * @date:2021/4/1 12:43
 **/
public class Page {
    /**
     * 当前页码，默认为1
     */
    private int current = 1;
    /**
     * 显示上限.默认为5
     */
    private int limit = 5;
    /**
     * 数据总数（用于计算总页数）
     */
    private int rows;
    /**
     * 查询路径（用于复用分页链接）
     */
    private String path;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if (current >=1 ) {
            this.current = current;
        }
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit >=1 && limit <=100) {
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows >= 0) {
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 获取当前页的起始行
     */

    public int getOffset(){
        //current*limit-limit
        return (current-1)*limit;
    }

    /**
     * 获取总页数
     */
    public int getTotal(){
        //rows/limit [+1]
        if (rows % limit ==0){
            return rows/limit;
        }else {
            return rows/limit + 1;
        }
    }

    /**
     * 获取分页下方显示的起始页码
     */
    public int getFrom(){
        int from = current - 2;
        return from < 1 ? 1 :from;
    }

    /**
     * 获取分页下方显示的结束页码
     */
    public int getTo(){
        int to = current + 2;
        int total = getTotal();
        return to > total ? total : to;
    }
}
