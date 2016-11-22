package model.users;

import model.Entity;
import model.tags.Tag;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;


public class Profile extends Entity {
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

    public static Hashtable<Tag, Integer> getTagsOccurrence(List<Profile> profiles) {
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
        boolean hasTag = false;
        for (int i = 0; i < getTags().size(); i++) {
            if (getTags().get(i).getId() == tag.getId()) {
                hasTag = true;
                break;
            }
        }
        if (!hasTag) {
            getTags().add(tag);
        }
    }

    public void removeTag(Tag tag) {
        for (int i = 0; i < getTags().size(); i++) {
            if (getTags().get(i).getId() == tag.getId()) {
                getTags().remove(i);
            }
        }
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    public double getInexpensiveOutingLimit() {
        return inexpensiveOutingLimit;
    }

    public void setInexpensiveOutingLimit(double inexpensiveOutingLimit) {
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }
}
