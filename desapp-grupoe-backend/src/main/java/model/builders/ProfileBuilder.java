package model.builders;

import model.outings.OutingTag;
import model.users.Profile;

import java.util.ArrayList;
import java.util.List;

public class ProfileBuilder {

    private List<OutingTag> tags = new ArrayList<>();
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
}
