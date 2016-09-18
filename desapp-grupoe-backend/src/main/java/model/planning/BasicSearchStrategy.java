package model.planning;


import model.builders.OutingFilterBuilder;
import model.outings.OutingTag;
import model.users.Profile;

public class BasicSearchStrategy {

    protected OutingFilterBuilder searchFilter(Profile profile, String tagName) {
        OutingTag searchTag = new OutingTag();
        searchTag.setName(tagName);
        return OutingFilterBuilder.anOutingFilter()
                .withSearchTag(searchTag);
    }

    protected OutingFilterBuilder searchFilter(Profile profile, double price) {
        return OutingFilterBuilder.anOutingFilter()
                .withMaxPrice(price);
    }

}
