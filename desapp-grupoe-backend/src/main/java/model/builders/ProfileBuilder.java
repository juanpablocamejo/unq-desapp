package model.builders;

import model.outings.OutingTag;
import model.users.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileBuilder {

    private List<OutingTag> tags = anyTagList();
    private double inexpensiveOutingLimit = 100;

    public static ProfileBuilder anyProfile() {
        return new ProfileBuilder();
    }

    public Profile build() {
        return new Profile(tags, inexpensiveOutingLimit);
    }

    public ProfileBuilder withTags(ArrayList<OutingTag> t) {
        tags = t;
        return this;
    }

    public ProfileBuilder withInexpensiveOutingLimit(double d) {
        inexpensiveOutingLimit = d;
        return this;
    }

    public List<OutingTag> anyTagList() {
        List<OutingTag> list = new ArrayList<>();
        list.add(TagBuilder.anyTag().withName("Tag1").build());
        return list;
    }
}
