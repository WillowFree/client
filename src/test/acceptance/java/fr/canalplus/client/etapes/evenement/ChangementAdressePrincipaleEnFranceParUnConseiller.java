package fr.canalplus.client.etapes.evenement;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import fr.canalplus.client.ServiceClientApplication;
import fr.canalplus.client.model.*;
import fr.canalplus.client.repositories.ClientRepository;
import fr.canalplus.client.repositories.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-acceptance-tests.properties")
@WebAppConfiguration
@AutoConfigureMockMvc
@ContextConfiguration(classes = ServiceClientApplication.class)
@ActiveProfiles({"acceptance-tests"})
public class ChangementAdressePrincipaleEnFranceParUnConseiller {

    private static final String PRENOM_DU_CLIENT = "prenom du client";
    private static final String NOM_DU_CLIENT = "nom du client";
    private static final String DATE_DE_NAISSANCE_STR = "1980-01-01T00:00:00.000Z";
    private static final OffsetDateTime DATE_DE_NAISSANCE_DU_CLIENT = OffsetDateTime.parse(DATE_DE_NAISSANCE_STR);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContratRepository contratRepository;

    private ClientEntity client;

    @Before
    public void setUp() {
        clientRepository.deleteAll();
        contratRepository.deleteAll();
    }

    @Given("un abonné avec une adresse principale active en France")
    public void creerUnAbonneAvecUneAdressePrincipaleEnFrance() {
        client = new ClientEntity(NOM_DU_CLIENT, PRENOM_DU_CLIENT, DATE_DE_NAISSANCE_DU_CLIENT);

        AdresseEntity adressePrincipalEnFrance = new AdresseEntity("7 rue de sevres", "75006",
                "Paris", Pays.FRANCE, TypeAdresse.PRINCIPAL);
        client.addAdresse(adressePrincipalEnFrance);

        ContratEntity contrat1 = new ContratEntity("Contrat_1");
        contrat1.setAdresseEntity(adressePrincipalEnFrance);
        client.addContrat(contrat1);

        ContratEntity contrat2 = new ContratEntity("Contrat_2");
        contrat2.setAdresseEntity(adressePrincipalEnFrance);
        client.addContrat(contrat2);

        client = clientRepository.save(client);
    }

    @When("^le conseiller connecté à \"([^\"]*)\" modifie l'adresse de l'abonné sans date d'effet$")
    public void unConseillerModifieLAdresseSansDateDEffet(String canal) throws Exception {
        //language=JSON
        String miseAJourAdresseAbonnePayLoad = "{\n" +
                "  \"canal\": \"" + canal + "\",\n" +
                "  \"id\": \"" + client.getId() + "\",\n" +
                "  \"nom\": \"" + NOM_DU_CLIENT + "\",\n" +
                "  \"prenom\": \"" + PRENOM_DU_CLIENT + "\",\n" +
                "  \"dateDeNaissance\": \"" + DATE_DE_NAISSANCE_STR + "\",\n" +
                "  \"adresseId\": \"" + client.getAdressePrincipale().getId() + "\",\n" +
                "  \"libelleAdresse\": \"6 rue d alleray\",\n" +
                "  \"codePostaleAdresse\": \"75015\",\n" +
                "  \"villeAdresse\": \"Paris\",\n" +
                "  \"paysAdresse\": \"FRANCE\",\n" +
                "  \"typeAdresse\": \"PRINCIPAL\"\n" +
                "}\n";

        post("http://localhost:8089/client/update", MediaType.APPLICATION_JSON, miseAJourAdresseAbonnePayLoad);
    }

    @Then("l'adresse de l'abonné modifiée est enregistrée sur l'ensemble des contrats de l'abonné")
    public void adresseDeLAbonneModifieeSurLEnsembleDeSesContrats() {
        AdresseEntity adresseModifieeAttendue = new AdresseEntity("6 rue d alleray",
                "75015", "Paris", Pays.FRANCE, TypeAdresse.PRINCIPAL);

        Set<ContratEntity> contrats = contratRepository.findAllByClientEntity_NomAndClientEntity_Prenom(
                NOM_DU_CLIENT, PRENOM_DU_CLIENT);
        contrats.forEach(contratEntity -> {
            assertThat(contratEntity.getAdresseEntity()).isEqualTo(adresseModifieeAttendue);
        });
    }

    @And("un mouvement de modification d'adresse est créé")
    public void unMovementDeModificationDeLAdresseEstCree() {
        System.out.println("********    unMovementDeModificationDeLAdresseEstCree");
    }

    protected void post(String url, MediaType mediaType, String body, Object... urlVariables) throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(url, urlVariables)
                .accept(mediaType)
                .contentType(mediaType)
                .content(body));
    }
}
