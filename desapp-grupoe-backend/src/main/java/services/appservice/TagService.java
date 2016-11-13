package services.appservice;


import model.builders.TagBuilder;
import model.tags.Tag;
import model.tags.TagCategory;
import org.springframework.transaction.annotation.Transactional;
import services.initialization.Initializable;

public class TagService extends GenericService<Tag> implements Initializable {
    @Transactional
    @Override
    public void initialize() {
        TagCategory music = new TagCategory("Music");
        TagCategory movie = new TagCategory("Movie");
        TagCategory gastronomy = new TagCategory("Gastronomy");
        TagCategory transport = new TagCategory("Transport");
        TagCategory others = new TagCategory("Others");

        getRepository().save(TagBuilder.anyTag().withName("Rock").withCategory(music).build());
        getRepository().save(TagBuilder.anyTag().withName("Pop").withCategory(music).build());
        getRepository().save(TagBuilder.anyTag().withName("Jazz").withCategory(music).build());

        getRepository().save(TagBuilder.anyTag().withName("Suspense").withCategory(movie).build());
        getRepository().save(TagBuilder.anyTag().withName("Drama").withCategory(movie).build());
        getRepository().save(TagBuilder.anyTag().withName("Science Fiction").withCategory(movie).build());
        getRepository().save(TagBuilder.anyTag().withName("Action").withCategory(movie).build());
        getRepository().save(TagBuilder.anyTag().withName("Thriller").withCategory(movie).build());
        getRepository().save(TagBuilder.anyTag().withName("Romantic").withCategory(movie).build());

        getRepository().save(TagBuilder.anyTag().withName("Italian").withCategory(gastronomy).build());
        getRepository().save(TagBuilder.anyTag().withName("Mexican").withCategory(gastronomy).build());
        getRepository().save(TagBuilder.anyTag().withName("Japanese").withCategory(gastronomy).build());
        getRepository().save(TagBuilder.anyTag().withName("Spanish").withCategory(gastronomy).build());
        getRepository().save(TagBuilder.anyTag().withName("Arabean").withCategory(gastronomy).build());

        getRepository().save(TagBuilder.anyTag().withName("Car").withCategory(transport).build());
        getRepository().save(TagBuilder.anyTag().withName("Bus").withCategory(transport).build());
        getRepository().save(TagBuilder.anyTag().withName("Van").withCategory(transport).build());
        getRepository().save(TagBuilder.anyTag().withName("Subway").withCategory(transport).build());
        getRepository().save(TagBuilder.anyTag().withName("Train").withCategory(transport).build());

        getRepository().save(TagBuilder.anyTag().withName("Couples").withCategory(others).build());
        getRepository().save(TagBuilder.anyTag().withName("Friends").withCategory(others).build());
    }

}
