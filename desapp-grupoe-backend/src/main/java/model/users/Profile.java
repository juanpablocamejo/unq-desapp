package model.users;

import model.Entity;
import model.tags.Tag;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Profile extends Entity {
    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    private List<Tag> tags = new ArrayList<>();
    private double inexpensiveOutingLimit;

    public Profile() {
    }

    public Profile(List<Tag> tags, double inexpensiveOutingLimit) {
        this.tags = tags;
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }

    public static Profile mergeProfiles(List<Profile> profiles) {
        int quorum = profiles.size() / 2;
        Hashtable<Tag, Integer> tagsOccurrence = getTagsOccurrence(profiles);
        double inexpensiveLimitAvg = profiles.parallelStream().mapToDouble(Profile::getInexpensiveOutingLimit).average().getAsDouble();
        return new Profile(getQuorumTags(quorum, tagsOccurrence), inexpensiveLimitAvg);
    }

    private static List<Tag> getQuorumTags(int quorum, Hashtable<Tag, Integer> tagsOccurrence) {
        List<Tag> quorumTags = new ArrayList<>();
        tagsOccurrence.forEach((tag, occurrence) -> {
            if (occurrence > quorum) quorumTags.add(tag);
        });
        return quorumTags;
    }

    private static Hashtable<Tag, Integer> getTagsOccurrence(List<Profile> profiles) {
        Hashtable<Tag, Integer> tagsOccurrence = new Hashtable<>();
        for (Profile profile : profiles) {
            for (Tag tag : profile.getTags()) {
                Integer currentOccurrence = tagsOccurrence.getOrDefault(tag, 0);
                tagsOccurrence.put(tag, currentOccurrence + 1);
            }
        }
        return tagsOccurrence;
    }

    public void addTag(Tag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(Tag tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
        }
    }

    public List<Tag> getTags() {
        return tags;
    }

    public double getInexpensiveOutingLimit() {
        return inexpensiveOutingLimit;
    }

    public void setInexpensiveOutingLimit(double inexpensiveOutingLimit) {
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }
}
