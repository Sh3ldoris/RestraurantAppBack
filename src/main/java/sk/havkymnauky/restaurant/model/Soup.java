package sk.havkymnauky.restaurant.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table( name = DBProperties.SOUP )
@JsonIgnoreProperties( value = "menus")
public class Soup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = DBProperties.SOUP_ID )
    private long soupID;

    @Column( name = DBProperties.SOUP_NAME )
    @NonNull
    private String soupName;

    @Column( name = DBProperties.SOUP_PRICE )
    @NonNull
    private double soupPrice;

    @ManyToMany(
            fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "soups"
    )
    private Set<Menu> menus = new HashSet<>();

    public Soup(String soupName, double soupPrice) {
        this.soupName = soupName;
        this.soupPrice = soupPrice;
    }

    public Soup() {
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public long getID() {
        return soupID;
    }

    public void setID(long soupID) {
        this.soupID = soupID;
    }

    public String getName() {
        return soupName;
    }

    public void setName(String soupName) {
        this.soupName = soupName;
    }

    public double getPrice() {
        return soupPrice;
    }

    public void setPrice(double soupPrice) {
        this.soupPrice = soupPrice;
    }
}
