package model;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private List<OutingTag> tags = new ArrayList<OutingTag>();
    private double inexpensiveOutingLimit;

    public void addTag(OutingTag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }
    public void removeTag(OutingTag tag) {
        this.tags.remove(tag);
    }

    public List<OutingTag> getTags() {
        return tags;
    }

    public double getInexpensiveOutingLimit() {
        return inexpensiveOutingLimit;
    }

    public void setInexpensiveOutingLimit(double inexpensiveOutingLimit) {
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }
}
