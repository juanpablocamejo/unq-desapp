package model.builders;

import model.outings.OutingTag;


public class TagBuilder {

    private String name = "Tag x";
    private OutingTag parent = new OutingTag();

    public static TagBuilder anyTag() {
        return new TagBuilder();
    }

    public OutingTag build() {
        return new OutingTag(name, parent);
    }

    public TagBuilder withName(String n) {
        name = n;
        return this;
    }

    public TagBuilder withParent(OutingTag p) {
        parent = p;
        return this;
    }

}
