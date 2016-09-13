package model.planning;


import model.builders.OutingFilterBuilder;
import model.outings.OutingTag;
import model.users.Profile;

public class BasicSearchStrategy {

    protected OutingFilterBuilder searchFilter(Profile profile, String tagName) {
        OutingTag searchTag = new OutingTag();
        searchTag.setName(tagName);
        return OutingFilterBuilder.anyOutingFilter()
                .withSearchTag(searchTag)
                .withProfileTags(profile.getTags());
    }

}
