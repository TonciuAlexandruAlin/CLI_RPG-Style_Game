package WorldOfMarcel;

import java.util.ArrayList;
import java.util.TreeSet;

public class Account {

    int NumberOfGames;
    Information info;
    ArrayList<Character> characters;
    public Account(Information info, ArrayList<Character>  characters, int NumberOfGames){
        this.info = info;
        this.characters = characters;
        this.NumberOfGames = NumberOfGames;
    }

    static class Information{

        private Credentials data;
        private TreeSet<String> prefferedGames;
        private String name;
        private String country;

        private Information(Infobuilder builder){

            this.data = builder.data;
            this.name = builder.name;
            this.prefferedGames = builder.prefferedGames;
            this.country = builder.country;
        }

        public Credentials getData() {
            return data;
        }

        public TreeSet<String> getPrefferedGames() {
            return prefferedGames;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        static class Infobuilder{
            private String name;
            private String country;
            private TreeSet<String> prefferedGames;
            private Credentials data;

            public Infobuilder(String name, Credentials data){
                this.name = name;
                this.data = data;
            }
            public Infobuilder country(String country){
                this.country = country;
                return this;
            }
            public Infobuilder prefferedGames(TreeSet<String> prefferedGames){
                this.prefferedGames = prefferedGames;
                return this;
            }

            public Information build(){
                Information AccInfo = new Information(this);
                return AccInfo;
            }

        }
    }
}

class Credentials{

    private String Email;
    private String Passwd;

    public String getEmail(){

        return Email;
    }
    public void setEmail(String Email){

        this.Email = Email;
    }
    public void setPasswd(String Passwd){

        this.Passwd = Passwd;
    }
    public String getPasswd(){

        return Passwd;
    }
}
