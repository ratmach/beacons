package ratmach.workshop.beacontransmitter;


import java.util.HashMap;
import java.util.Map;

public class TouristInfo {

    private String title ;

    private String description;

    private String action;

    private String actionLink;

    private static Map<Integer,TouristInfo> infoMap = new HashMap<>();

    static {
        TouristInfo opera = new TouristInfo();
        opera.setTitle("Opera");
        opera.setAction("https://en.wikipedia.org/wiki/Georgian_National_Opera_Theater");
        opera.setDescription("The Georgian National Opera and Ballet Theater of Tbilisi (Georgian: თბილისის ოპერისა და ბალეტის სახელმწიფო აკადემიური თეატრი), formerly known as the Tiflis Imperial Theater, is an opera house situated on Rustaveli Avenue in Tbilisi, Georgia. Founded in 1851, Tbilisi Opera is the main opera house of Georgia and one of the oldest such establishments in eastern Europe.\n" +
                "\n" +
                "Since 1896, the theater has resided in an exotic neo-Moorish edifice originally constructed by Victor Johann Gottlieb Schröter, a prominent architect of Baltic German origin. Although definitively Oriental in its decorations and style, the building's layout, foyers and the main hall are that of a typical European opera house. Since its foundation, the theater has been damaged by several fires and underwent major rehabilitation works under Soviet and Georgian leadership; the most recent restoration effort concluded in January 2016, having taken six years and costing approximately 40 million U.S. dollars, donated by a Georgian business foundation.[1]\n" +
                "\n" +
                "The opera house is one of the centers of cultural life in Tbilisi and was once home to Zacharia Paliashvili, the Georgian national composer whose name the institution has carried since 1937. The Opera and Ballet Theater also houses the State Ballet of Georgia under the leadership of internationally renowned Georgian ballerina Nina Ananiashvili. In recent years it has hosted opera stars such as Montserrat Caballé and José Carreras,[2] while also serving as a traditional venue for national celebrations and presidential inaugurations.");
        infoMap.put(1, opera);

        TouristInfo museum = new TouristInfo();
        museum.setTitle("Museum");
        museum.setAction("https://en.wikipedia.org/wiki/Georgian_National_Museum");
        museum.setDescription("The Georgian National Museum (Georgian: საქართველოს ეროვნული მუზეუმი, translit.: sakartvelos erovnuli muzeumi) unifies several leading museums in Georgia. It was established within the framework of structural, institutional, and legal reforms aimed at modernizing the management of the institutions united within this network, and at coordinating research and educational activities. Since its formation on December 30, 2004, the Museum has been directed by Professor David Lordkipanidze.[1]\n" +
                "\n" +
                "The Georgian National Museum integrates the management of the following museums:" +
                "" +
                "" +
                "Simon Janashia Museum of Georgia, Tbilisi\n" +
                "Samtskhe-Javakheti History Museum, Akhaltsikhe\n" +
                "Open Air Museum of Ethnography, Tbilisi\n" +
                "Art Museum of Georgia, Tbilisi, and its branches\n" +
                "Museum of the Soviet Occupation, Tbilisi\n" +
                "Dmanisi Museum-Reserve of History and Archaeology, Dmanisi\n" +
                "Vani Museum-Reserve of Archaeology, Vani\n" +
                "Museum of History of Tbilisi, Tbilisi\n" +
                "Museum of History and Ethnography of Svaneti, Mestia\n" +
                "Institute of Palaeobiology, Tbilisi\n" +
                "Sighnaghi Museum, Sighnaghi");
        infoMap.put(2, museum);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static TouristInfo get(int id){
        return infoMap.get(id);
    }

    public String getActionLink() {
        return actionLink;
    }

    public void setActionLink(String actionLink) {
        this.actionLink = actionLink;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
        setActionLink(action);
    }
}
