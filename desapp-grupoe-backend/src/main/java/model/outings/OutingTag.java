package model.outings;

public class OutingTag {
    private long id;
    private String name;
    private OutingTag parent;

    public OutingTag() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OutingTag outingTag = (OutingTag) o;

        return id == outingTag.id;

    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    public OutingTag(long id, String name, OutingTag parent) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OutingTag getParent() {
        return parent;
    }

    public void setParent(OutingTag parent) {
        this.parent = parent;
    }


}
