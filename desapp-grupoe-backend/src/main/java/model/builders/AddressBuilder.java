package model.builders;

import model.locations.Address;
import model.locations.Coord;

import static model.builders.CoordBuilder.anyCoord;

public class AddressBuilder {

    private Coord coord = anyCoord().build();
    private String location = "Buenos Aires";

    public static AddressBuilder anyAddress() {
        return new AddressBuilder();
    }

    public Address build() {
        return new Address(coord, location);
    }

    public AddressBuilder withCoord(Coord c) {
        coord = c;
        return this;
    }

    public AddressBuilder withLocation(String l) {
        location = l;
        return this;
    }

}
