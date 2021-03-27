package com.peng.flux.action;

/**
 * @author lovely
 * @create 2021-03-02 17:01
 * @description
 */
public class ContentAction extends Action {
    private Content content;

    public ContentAction(Content content) {
        super(ActionType.CONTENT_CAHNGED);
        this.content = content;
    }
}