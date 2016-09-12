package model.users;

import model.outings.OutingTag;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Profile {
    private List<OutingTag> tags = new ArrayList<>();
    private double inexpensiveOutingLimit;

    public Profile() {
    }

    public Profile(List<OutingTag> tags, double inexpensiveOutingLimit) {
        this.tags = tags;
        this.inexpensiveOutingLimit = inexpensiveOutingLimit;
    }

    public static Profile mergeProfiles(List<Profile> profiles) {
        int quorum = profiles.size() / 2;
        Hashtable<OutingTag, Integer> tagsOccurrence = getTagsOccurrence(profiles);
        double inexpensiveLimitAvg = profiles.parallelStream().mapToDouble(Profile::getInexpensiveOutingLimit).average().getAsDouble();
        return new Profile(getQuorumTags(quorum, tagsOccurrence), inexpensiveLimitAvg);
    }

    private static List<OutingTag> getQuorumTags(int quorum, Hashtable<OutingTag, Integer> tagsOccurrence) {
        List<OutingTag> quorumTags = new ArrayList<>();
        tagsOccurrence.forEach((tag, occurrence) -> {
            if (occurrence > quorum) quorumTags.add(tag);
        });
        return quorumTags;
    }

    private static Hashtable<OutingTag, Integer> getTagsOccurrence(List<Profile> profiles) {
        Hashtable<OutingTag, Integer> tagsOccurrence = new Hashtable<>();
        for (Profile profile : profiles) {
            for (OutingTag tag : profile.getTags()) {
                Integer currentOccurrence = tagsOccurrence.getOrDefault(tag, 0);
                tagsOccurrence.put(tag, currentOccurrence + 1);
            }
        }
        return tagsOccurrence;
    }

    public void addTag(OutingTag tag) {
        if (!this.tags.contains(tag)) {
            this.tags.add(tag);
        }
    }

    public void removeTag(OutingTag tag) {
        if (tags.contains(tag)) {
            tags.remove(tag);
        }
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
