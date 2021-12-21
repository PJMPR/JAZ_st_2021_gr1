package com.example.demo.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
<<<<<<< HEAD
=======
import java.util.Objects;
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217

@Entity
public class Language {
    private int languageId;
    private String name;
    private Timestamp lastUpdate;
<<<<<<< HEAD
    private Collection<Film> filmsByLanguageId;
=======
    private Collection<Film> films;
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217

    @Id
    @Column(name = "language_id")
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_update")
    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
<<<<<<< HEAD

        Language language = (Language) o;

        if (languageId != language.languageId) return false;
        if (name != null ? !name.equals(language.name) : language.name != null) return false;
        if (lastUpdate != null ? !lastUpdate.equals(language.lastUpdate) : language.lastUpdate != null) return false;

        return true;
=======
        Language language = (Language) o;
        return languageId == language.languageId && Objects.equals(name, language.name) && Objects.equals(lastUpdate, language.lastUpdate);
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
    }

    @Override
    public int hashCode() {
<<<<<<< HEAD
        int result = languageId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastUpdate != null ? lastUpdate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "language")
    public Collection<Film> getFilmsByLanguageId() {
        return filmsByLanguageId;
    }

    public void setFilmsByLanguageId(Collection<Film> filmsByLanguageId) {
        this.filmsByLanguageId = filmsByLanguageId;
    }
}
=======
        return Objects.hash(languageId, name, lastUpdate);
    }

    @OneToMany(mappedBy = "language")
    public Collection<Film> getFilms() {
        return films;
    }

    public void setFilms(Collection<Film> films) {
        this.films = films;
    }
}
>>>>>>> 62e3a4e14484aa83fc53cd8bd6a8184df1b08217
