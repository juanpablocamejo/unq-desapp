package services;


import model.builders.TagBuilder;
import model.tags.Tag;
import model.tags.TagCategory;
import org.springframework.transaction.annotation.Transactional;
import services.initialization.Initializable;

public class TagService extends GenericService<Tag> implements Initializable {
    @Transactional
    @Override
    public void initialize() {
        TagCategory c = new TagCategory("music");
        getRepository().save(TagBuilder.anyTag().withName("Rock").withCategory(c).build());
        getRepository().save(TagBuilder.anyTag().withName("Pop").withCategory(c).build());
        getRepository().save(TagBuilder.anyTag().withName("Jazz").withCategory(c).build());
    }
}
