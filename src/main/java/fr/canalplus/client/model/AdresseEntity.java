package fr.canalplus.client.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_adresse")
public class AdresseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "libelle", nullable = false)
    private String libelle;

    @Column(name = "codePostale", nullable = false)
    private String codePostale;

    @Column(name = "ville", nullable = false)
    private String ville;

    @Column(name = "pays", nullable = false)
    private Pays pays;

    @Column(name = "typeAdresse", nullable = false)
    private TypeAdresse typeAdresse;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id", referencedColumnName = "id", nullable = false)
    private ClientEntity clientEntity;

    public AdresseEntity() {
    }

    public AdresseEntity(String libelle, String codePostale, String ville, Pays pays, TypeAdresse typeAdresse) {
        this.libelle = libelle;
        this.codePostale = codePostale;
        this.ville = ville;
        this.pays = pays;
        this.typeAdresse = typeAdresse;
    }

    public long getId() {
        return id;
    }

    public AdresseEntity setId(long id) {
        this.id = id;
        return this;
    }

    public AdresseEntity setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
        return this;
    }

    public TypeAdresse getTypeAdresse() {
        return typeAdresse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AdresseEntity adresse = (AdresseEntity) o;
        return Objects.equals(libelle, adresse.libelle) &&
                Objects.equals(codePostale, adresse.codePostale) &&
                Objects.equals(ville, adresse.ville) &&
                Objects.equals(pays, adresse.pays) &&
                Objects.equals(typeAdresse, adresse.typeAdresse);
    }

    @Override
    public int hashCode() {

        return Objects.hash(libelle, codePostale, ville, pays, typeAdresse);
    }

    @Override
    public String toString() {
        return "AdresseEntity{" +
                "id=" + id +
                ", libelle='" + libelle + '\'' +
                ", codePostale='" + codePostale + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", typeAdresse=" + typeAdresse +
                '}';
    }
}
