package sk.havkymnauky.restaurant.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table( name = DBProperties.MENU)
@JsonIgnoreProperties( value = "soups")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = DBProperties.MENU_ID )
    private long id;

    @Column( name = DBProperties.MENU_DATE )
    @NonNull
    private Date menuDate;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = DBProperties.MENU_SOUP,
            joinColumns = { @JoinColumn(name = DBProperties.MENU_ID) },
            inverseJoinColumns = { @JoinColumn(name = DBProperties.SOUP_ID) }
    )
    private Set<Soup> soups = new HashSet<>();

    public Menu(@NonNull Date menuDate) {
        this.menuDate = menuDate;
    }

    public Menu() {
    }

    public long getId() {
        return id;
    }

    public Set<Soup> getSoups() {
        return soups;
    }

    public void setSoups(Set<Soup> menuSoup) {
        this.soups = menuSoup;
    }

    public void setId(long menuId) {
        this.id = menuId;
    }

    public Date getDate() {
        return menuDate;
    }

    public void setDate(Date menuDate) {
        this.menuDate = menuDate;
    }
}
