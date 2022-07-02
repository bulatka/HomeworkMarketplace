package goods;

import java.util.Objects;

public class Goods implements Comparable<Goods> {
    protected int id;
    protected String name;
    protected int price;
    protected int rating;

    public Goods(String name, int price, int rating) {
        this.id = Objects.hash(name);
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + name + ", " + price + " $]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Goods)) return false;
        Goods goods = (Goods) o;
        return getId() == goods.getId() && getPrice() == goods.getPrice()
                && getName().equals(goods.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public int compareTo(Goods item) {
        return this.id - item.id;
    }
}
