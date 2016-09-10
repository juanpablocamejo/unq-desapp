package model.builders;

import model.OutingTag;
import model.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileBuilder {

    public static ProfileBuilder anyProfile() {
        return new ProfileBuilder();
    }

    private List<OutingTag> tags = new ArrayList<OutingTag>();
    private double inexpensiveOutingLimit = 100;

    public Profile build() {
        Profile profile = new Profile(tags, inexpensiveOutingLimit);
        return profile;
    }

    public ProfileBuilder withTags(ArrayList<OutingTag> t) {
        tags = t;
        return this;
    }

    public ProfileBuilder withInexpensiveOutingLimit(double d) {
        inexpensiveOutingLimit = d;
        return this;
    }
}
