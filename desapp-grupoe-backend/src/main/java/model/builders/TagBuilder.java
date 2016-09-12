package model.builders;

import model.outings.OutingTag;


public class TagBuilder {

    private long id = 1;
    private String name = "Tag x";
    private OutingTag parent = new OutingTag();

    public static TagBuilder anyTag() {
        return new TagBuilder();
    }

    public OutingTag build() {
        return new OutingTag(id, name, parent);
    }

    public TagBuilder withId(long i) {
        id = i;
        return this;
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
