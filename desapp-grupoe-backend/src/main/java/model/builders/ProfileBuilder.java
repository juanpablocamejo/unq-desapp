package model.builders;

import model.tags.Tag;
import model.users.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileBuilder {

    private List<Tag> tags = anyTagList();
    private double inexpensiveOutingLimit = 100;

    public static ProfileBuilder anyProfile() {
        return new ProfileBuilder();
    }

    public Profile build() {
        return new Profile(tags, inexpensiveOutingLimit);
    }

    public ProfileBuilder withTags(ArrayList<Tag> t) {
        tags = t;
        return this;
    }

    public ProfileBuilder withInexpensiveOutingLimit(double d) {
        inexpensiveOutingLimit = d;
        return this;
    }

    public List<Tag> anyTagList() {
        List<Tag> list = new ArrayList<>();
        list.add(TagBuilder.anyTag().withName("Tag1").build());
        return list;
    }
}
