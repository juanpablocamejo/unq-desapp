package model.builders;

import model.tags.Tag;
import model.tags.TagCategory;


public class TagBuilder {

    private String name = "Tag x";
    private TagCategory category = new TagCategory("category");

    public static TagBuilder anyTag() {
        return new TagBuilder();
    }

    public Tag build() {
        return new Tag(name,category);
    }

    public TagBuilder withName(String n) {
        name = n;
        return this;
    }
    public TagBuilder withCategory(TagCategory c) {
        category = c;
        return this;
    }
}
