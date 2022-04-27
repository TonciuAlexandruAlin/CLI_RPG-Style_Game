package WorldOfMarcel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

import java.io.*;
import java.util.*;

public class Game {

    HashMap<Cell.Type, ArrayList<String>> MapOfStories;
    ArrayList<Account> Accounts;
    static Game game = null;

    public static Game getInstance(){
        if(game == null){
            game = new Game();
        }
        return game;
    }

    public void getStories() {
        File input = new File("src\\stories.json");
        try {
            MapOfStories = new HashMap<Cell.Type, ArrayList<String>>();
            MapOfStories.put(Cell.Type.EMPTY, new ArrayList<String>());
            MapOfStories.put(Cell.Type.SHOP, new ArrayList<String>());
            MapOfStories.put(Cell.Type.ENEMY, new ArrayList<String>());
            MapOfStories.put(Cell.Type.FINISH, new ArrayList<String>());
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray stories = fileObject.get("stories").getAsJsonArray();
            for (JsonElement i : stories) {
                JsonObject fileObject2 = i.getAsJsonObject();
                String typeOfStory = fileObject2.get("type").getAsString();
                String valueOfStory = fileObject2.get("value").getAsString();
                if (typeOfStory.compareTo("EMPTY") == 0) {
                    MapOfStories.get(Cell.Type.EMPTY).add(valueOfStory);
                }
                if (typeOfStory.compareTo("ENEMY") == 0) {
                    MapOfStories.get(Cell.Type.ENEMY).add(valueOfStory);
                }
                if (typeOfStory.compareTo("SHOP") == 0) {
                    MapOfStories.get(Cell.Type.SHOP).add(valueOfStory);
                }
                if (typeOfStory.compareTo("FINISH") == 0) {
                    MapOfStories.get(Cell.Type.FINISH).add(valueOfStory);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getAccounts() {
        File input2 = new File("src\\accounts.json");
        try {
            Accounts = new ArrayList<Account>();
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input2));
            JsonObject fileObject = fileElement.getAsJsonObject();
            JsonArray acc = fileObject.get("accounts").getAsJsonArray();
            for (JsonElement i : acc) {
                JsonObject fileObject2 = i.getAsJsonObject();
                String name = fileObject2.get("name").getAsString();
                String country = fileObject2.get("country").getAsString();
                int maps_completed = fileObject2.get("maps_completed").getAsInt();
                Credentials info = new Credentials();
                JsonObject fileObject3 = fileObject2.get("credentials").getAsJsonObject();
                info.setEmail(fileObject3.get("email").getAsString());
                info.setPasswd(fileObject3.get("password").getAsString());
                JsonArray fileObject4 = fileObject2.get("favorite_games").getAsJsonArray();
                TreeSet<String> prefferedGames = new TreeSet<String>();
                for (JsonElement j : fileObject4) {
                    prefferedGames.add(j.getAsString());
                }
                Account.Information PlayerInfo = new Account.Information.Infobuilder(name, info)
                        .country(country)
                        .prefferedGames(prefferedGames)
                        .build();
                JsonArray fileObject5 = fileObject2.get("characters").getAsJsonArray();
                ArrayList<Character> Characters = new ArrayList<Character>();
                for (JsonElement j : fileObject5) {
                    JsonObject fileObject6 = j.getAsJsonObject();
                    String nameChar = fileObject6.get("name").getAsString();
                    String profession = fileObject6.get("profession").getAsString();
                    int level = Integer.parseInt(fileObject6.get("level").getAsString());
                    int experience = fileObject6.get("experience").getAsInt();
                    CharacterFactory helper = new CharacterFactory();
                    Character character = helper.Factory(profession, nameChar, experience, level);
                    Characters.add(character);
                }
                Account cont = new Account(PlayerInfo, Characters, maps_completed);
                Accounts.add(cont);
            }


        } catch (Exception | InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    public void write() throws IOException{
        FileWriter output = new FileWriter("src\\accounts.json");
        JsonWriter object = new JsonWriter(output);
        object.setIndent("  ");
        object.beginObject();
        writeAcc(object, Accounts);
        object.endObject();
        object.close();
    }
    public void writeAcc(JsonWriter object, ArrayList<Account> Accounts) throws IOException {
        object.name("accounts");
        object.beginArray();
        for(int i = 0; i < Accounts.size(); i++){
            writeCont(object, Accounts.get(i));
        }
        object.endArray();
    }
    public void writeCont(JsonWriter object, Account cont) throws  IOException{
        object.beginObject();
        object.name("credentials");
        writeCredentials(object, cont.info.getData());
        object.name("name").value(cont.info.getName());
        object.name("country").value(cont.info.getCountry());
        object.name("favorite_games");
        writeFavGames(object, cont.info.getPrefferedGames());
        object.name("maps_completed").value(Integer.toString(cont.NumberOfGames));
        object.name("characters");
        writeCharacters(object, cont.characters);
        object.endObject();
    }
    public void writeCredentials(JsonWriter object, Credentials credentials) throws IOException{
        object.beginObject();
        object.name("email").value(credentials.getEmail());
        object.name("password").value(credentials.getPasswd());
        object.endObject();
    }
    public void writeCharacters(JsonWriter object, ArrayList<Character> characters) throws IOException {
        object.beginArray();
        for(int i = 0; i < characters.size(); i++){
            object.beginObject();
            object.name("name").value(characters.get(i).name);
            object.name("profession").value(characters.get(i).getClass().getSimpleName());
            object.name("level").value(Integer.toString(characters.get(i).currentLvl));
            object.name("experience").value(characters.get(i).currentExp);
            object.endObject();
        }
        object.endArray();
    }
    public void writeFavGames(JsonWriter object, TreeSet<String> prefferedGames) throws  IOException{
        object.beginArray();
        for(String i : prefferedGames){
            object.value(i);
        }
        object.endArray();
    }

    public void run() throws InvalidCommandException {
        System.out.println("Text / Grafic: ");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        String Mod = scan.next();
        try {
            if (Mod.compareTo("Text") == 0) {
                System.out.println("Joc sau Hardcodat");
                String hardcoded = scan.next();
                if (hardcoded.compareTo("Joc") == 0) {
                    getAccounts();
                    getStories();
                    System.out.println("Introduceti e-mailul: ");
                    System.out.println();
                    String email = scan.next();
                    int poz = -1;
                    for (int i = 0; i < Accounts.size(); i++) {
                        if (Accounts.get(i).info.getData().getEmail().compareTo(email) == 0) {
                            System.out.println("Introduceti parola: ");
                            System.out.println();
                            String passwd = scan.next();
                            if (Accounts.get(i).info.getData().getPasswd().compareTo(passwd) == 0) {
                                poz = i;
                                break;
                            }
                        }
                    }
                    try {
                        if (poz != -1) {
                            System.out.println("Ce caracter alegeti? ");
                            System.out.println();
                            for (int j = 0; j < Accounts.get(poz).characters.size(); j++) {
                                System.out.println(j + ". " + Accounts.get(poz).characters.get(j).name);
                            }
                            int Character = scan.nextInt();
                            Character player = Accounts.get(poz).characters.get(Character);
                            Cell currentPos = new Cell(new EmptyCell(), 0, 0);
                            System.out.println("Ai level " + player.currentLvl + " si " + player.currentExp + " experienta");
                            System.out.println("Stats-urile tale sunt: " + player.charisma + " charisma, " + player.strength + " strength " + player.dexterity + " dexterity ");
                            Grid table = Grid.generateTable(5, 5, player, currentPos);
                            table.printTable();
                            while (player.currentHP != 0 && table.current.element.toChar() != 'F') {
                                try {
                                    String move = scan.next();
                                    if (move.compareTo("N") == 0) {
                                        table.goNorth();
                                        table.printTable();
                                    } else if (move.compareTo("S") == 0) {
                                        table.goSouth();
                                        table.printTable();
                                    } else if (move.compareTo("E") == 0) {
                                        table.goEast();
                                        table.printTable();
                                    } else if (move.compareTo("W") == 0) {
                                        table.goWest();
                                        table.printTable();
                                    } else {
                                        throw new InvalidCommandException("Comanda introdusa nu este valida!");
                                    }

                                    if (table.current.element.toChar() == 'N') {
                                        if (table.get(table.current.ox).get(table.current.oy).visited == false) {
                                            System.out.println(MapOfStories.get(Cell.Type.EMPTY).get(rand.nextInt(MapOfStories.get(Cell.Type.EMPTY).size())));
                                            int chance = rand.nextInt(100);
                                            if (chance < 20) {
                                                int x = rand.nextInt(1, 11);
                                                System.out.println("Te-ai impiedicat de o punguta cu bani! Ai primit " + x + " banuti.");
                                                player.storage.coins = player.storage.coins + x;
                                            }

                                        }
                                    }
                                    if (table.current.element.toChar() == 'S') {
                                        player.storage.coins = 500;
                                        while (true) {
                                            if (table.get(table.current.ox).get(table.current.oy).visited == false) {
                                                System.out.println(MapOfStories.get(Cell.Type.SHOP).get(rand.nextInt(MapOfStories.get(Cell.Type.SHOP).size())));
                                            }
                                            table.get(table.current.ox).get(table.current.oy).visited = true;
                                            System.out.println("Aveti " + player.storage.coins + " banuti");
                                            System.out.println("Ce potiuni doriti sa cumparati? Index pentru cumparare & 9 pentru quit");
                                            try {
                                                Shop shop = (Shop) table.current.element;
                                                for (int i = 0; i < shop.potions.size(); i++) {
                                                    System.out.println(i + ". " + shop.potions.get(i).getClass().getSimpleName());
                                                }
                                                int potiune = scan.nextInt();
                                                if (potiune == 9) {
                                                    table.printTable();
                                                    break;
                                                } else if (shop.potions.size() == 0) {
                                                    System.out.println("Nu prea mai avem marfa..");
                                                    table.printTable();
                                                    break;
                                                } else if (potiune >= 0 && potiune < shop.potions.size()) {
                                                    player.buyPotion(shop.SellPotion(potiune));
                                                    shop.potions.remove(potiune);
                                                } else {
                                                    throw new InvalidCommandException("Nu avem aceasta potiune..");
                                                }
                                            } catch (InvalidCommandException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                        System.out.println("Mai ai " + player.storage.coins + " banuti si mai poti cara " + player.storage.remainingWeigth() + " Kg");
                                    }
                                    if (table.current.element.toChar() == 'E') {
                                        System.out.println("Ai intalnit un inamic! Pregateste-te!");
                                        System.out.println(MapOfStories.get(Cell.Type.ENEMY).get(rand.nextInt(MapOfStories.get(Cell.Type.ENEMY).size())));
                                        table.get(table.current.ox).get(table.current.oy).visited = true;
                                        Enemy enemy = (Enemy) table.current.element;
                                        System.out.println("Inamicul are stats-urile: " + enemy.currentHP + " HP si " + enemy.currentMP + " MP");
                                        System.out.println("Tu ai stats-urile : " + player.currentHP + " HP si " + player.currentMP + " MP");
                                        int turn = 1;
                                        while (player.currentHP > 0 && enemy.currentHP > 0) {
                                            System.out.println("Inamicul mai are " + enemy.currentHP + " HP si " + enemy.currentMP + " MP  si protectiile: (Ice, " + enemy.ice + "), (Earth, " + enemy.earth + ") si (Fire, " + enemy.fire + ").");
                                            System.out.println("Tu mai ai : " + player.currentHP + " HP si " + player.currentMP + " MP");
                                            if (turn == 1) {
                                                try {
                                                    System.out.println("Cu ce doresti sa ataci? Sau doresti sa iti reincarci viata? Skill, Hit or Potion");
                                                    String battle = scan.next();
                                                    if (battle.compareTo("Skill") == 0 && player.Spells.size() > 0) {
                                                        System.out.println("Ce abilitate vrei sa folosesti?");
                                                        for (int i = 0; i < player.Spells.size(); i++) {
                                                            System.out.println(i + ". " + player.Spells.get(i).getClass().getSimpleName());
                                                        }
                                                        player.useSpell(player.Spells.get(scan.nextInt()), enemy);
                                                    } else if (battle.compareTo("Hit") == 0) {
                                                        System.out.println("Ai atacat cu un hit puternic care a provocat " + player.getDamage() + " daune.");
                                                        enemy.receiveDamage(player.getDamage());
                                                    } else if (battle.compareTo("Potion") == 0) {
                                                        System.out.println("Aici sunt potiunile tale:");
                                                        for (int i = 0; i < player.storage.potions.size(); i++) {
                                                            System.out.println(player.storage.potions.get(i).getClass().getSimpleName());
                                                        }
                                                        player.usePotion(player.storage.potions.get(scan.nextInt()));
                                                    } else {
                                                        throw new InvalidCommandException("Nu ai aceasta putere.");
                                                    }
                                                } catch (InvalidCommandException e) {
                                                    e.printStackTrace();
                                                }
                                            } else if (turn == 0) {
                                                int chance = rand.nextInt(100);
                                                if (chance < 25) {
                                                    System.out.println("Inamicul a atacat cu skill.");
                                                    enemy.useSpell(enemy.Spells.get(rand.nextInt(enemy.Spells.size())), player);
                                                } else {
                                                    System.out.println("Inamicul a atacat cu un hit puternic pentru " + enemy.getDamage() + " damage.");
                                                    player.receiveDamage(enemy.getDamage());
                                                }
                                            }
                                            turn = (turn + 1) % 2;
                                            System.out.println();
                                        }
                                        if (enemy.currentHP <= 0) {
                                            System.out.println("Ai castigat aceasta batalie, felicitari! Iti poti continua aventura.");
                                            System.out.println();
                                            int y = 30;
                                            player.currentExp = player.currentExp + y;
                                            System.out.println("Ai primit " + y + " puncte de experienta.");
                                            if (player.currentExp >= 100) {
                                                player.currentLvl = player.currentLvl + 1;
                                                player.currentExp = 0;
                                                System.out.println("Felicitari, ai ajuns la nivelul " + player.currentLvl + ".");
                                            }
                                            int chance = rand.nextInt(100);
                                            if (chance < 80) {
                                                int x = rand.nextInt(6, 18);
                                                System.out.println("Batalia ti-a adus niste banuti frumosi. Ai primit " + x + " coins.");
                                                System.out.println();
                                                player.storage.coins = player.storage.coins + x;
                                            }
                                            table.printTable();
                                        } else {
                                            System.out.println("Ai pierdut rusinos aceasta batalie.");
                                            return;
                                        }
                                    }
                                    table.get(table.current.ox).get(table.current.oy).visited = true;

                                } catch (InvalidCommandException e) {
                                    e.printStackTrace();
                                    break;
                                }
                                if (table.current.element.toChar() == 'F') {
                                    System.out.println(MapOfStories.get(Cell.Type.FINISH).get(rand.nextInt(MapOfStories.get(Cell.Type.FINISH).size())));
                                    System.out.println("Felicitari, aventurierule! Alte provocari te vor astepta in curand!");
                                    Accounts.get(poz).NumberOfGames++;
                                }
                            }
                        } else {
                            throw new InvalidCommandException("User sau parola gresita.");
                        }
                    } catch (InvalidCommandException e) {
                        e.printStackTrace();
                    }
                } else if (hardcoded.compareTo("Hardcodat") == 0) {
                    getAccounts();
                    getStories();
                    System.out.println("Introduceti e-mailul: ");
                    System.out.println();
                    String email = scan.next();
                    int poz = -1;
                    for (int i = 0; i < Accounts.size(); i++) {
                        if (Accounts.get(i).info.getData().getEmail().compareTo(email) == 0) {
                            System.out.println("Introduceti parola: ");
                            System.out.println();
                            String passwd = scan.next();
                            if (Accounts.get(i).info.getData().getPasswd().compareTo(passwd) == 0) {
                                poz = i;
                                break;
                            }
                        }
                    }
                    if (poz != -1) {
                        System.out.println("Ce caracter alegeti? ");
                        System.out.println();
                        for (int j = 0; j < Accounts.get(poz).characters.size(); j++) {
                            System.out.println(j + ". " + Accounts.get(poz).characters.get(j).name);
                        }
                        int Character = scan.nextInt();
                        Character player = Accounts.get(poz).characters.get(Character);
                        Cell currentPos = new Cell(new EmptyCell(), 0, 0);
                        System.out.println("Ai level " + player.currentLvl + " si " + player.currentExp + " experienta");
                        System.out.println("Stats-urile tale sunt: " + player.charisma + " charisma, " + player.strength + " strength " + player.dexterity + " dexterity ");
                        Grid table = Grid.generateHarcoded(5, 5, player, currentPos);
                        table.printTable();
                        String next = scan.next();
                        if (next.compareTo("p") == 0) {

                            for (int i = 0; i < 3; i++) {
                                table.goEast();
                                table.current.visited = true;
                            }
                            table.printTable();
                            if (table.current.element.toChar() == 'S') {
                                player.storage.coins = 500;
                                for (int i = 0; i < 2; i++) {
                                    if (table.get(table.current.ox).get(table.current.oy).visited == false) {
                                        System.out.println(MapOfStories.get(Cell.Type.SHOP).get(rand.nextInt(MapOfStories.get(Cell.Type.SHOP).size())));
                                    }
                                    table.get(table.current.ox).get(table.current.oy).visited = true;
                                    System.out.println("Aveti " + player.storage.coins + " banuti.");
                                    System.out.println("Avem urmatoarele potiuni:");
                                    Shop shop = (Shop) table.current.element;
                                    for (int j = 0; j < shop.potions.size(); j++) {
                                        System.out.println(j + ". " + shop.potions.get(j).getClass().getSimpleName());
                                    }
                                    String potiunebuy = scan.next();
                                    if (potiunebuy.compareTo("p") == 0) {
                                        player.buyPotion(shop.potions.get(0));
                                        shop.potions.remove(0);
                                    }
                                }
                                System.out.println("Inventarul tau contine: ");
                                for (int i = 0; i < player.storage.potions.size(); i++) {
                                    System.out.println(player.storage.potions.get(i).getClass().getSimpleName());
                                }
                                System.out.println("Mai ai " + player.storage.coins + " banuti si mai poti cara " + player.storage.remainingWeigth() + " Kg.");
                            }
                            table.printTable();
                            next = scan.next();
                            if (next.compareTo("p") == 0) {
                                table.goEast();
                                table.current.visited = true;
                                for (int i = 0; i < 3; i++) {
                                    table.goSouth();
                                    table.current.visited = true;
                                }
                                table.printTable();
                            }
                            if (table.current.element.toChar() == 'E') {
                                System.out.println("Ai intalnit un inamic! Pregateste-te!");
                                System.out.println(MapOfStories.get(Cell.Type.ENEMY).get(rand.nextInt(MapOfStories.get(Cell.Type.ENEMY).size())));
                                table.get(table.current.ox).get(table.current.oy).visited = true;
                                Enemy enemy = (Enemy) table.current.element;
                                while (player.currentHP > 0 && enemy.currentHP > 0) {
                                    System.out.println("Inamicul mai are " + enemy.currentHP + " HP si " + enemy.currentMP + " MP  si protectiile (Ice, " + enemy.ice + "), (Earth, " + enemy.earth + "), si (Fire, " + enemy.fire + ").");
                                    System.out.println("Tu mai ai : " + player.currentHP + " HP si " + player.currentMP + " MP");
                                    if (player.Spells.size() != 0) {
                                        System.out.println("Ai folosit abilitatea de " + player.Spells.get(0).getClass().getSimpleName() + " care a provocat " + player.Spells.get(0).damage + " daune.");
                                        player.useSpell(player.Spells.get(0), enemy);
                                    } else if (player.storage.potions.size() != 0) {
                                        System.out.println("Te-ai hidratat cu " + player.storage.potions.get(0).getClass().getSimpleName() + ".");
                                        player.usePotion(player.storage.potions.get(0));
                                    } else {
                                        System.out.println("Ai folosit un hit puternic de " + player.getDamage() + " damage.");
                                        enemy.receiveDamage(player.getDamage());
                                    }
                                    int sansa = rand.nextInt(100);
                                    if (sansa < 30 && enemy.Spells.size() > 0) {
                                        System.out.println("Inamicul a folosit o abilitate puternica.");
                                        enemy.useSpell(enemy.Spells.get(rand.nextInt(enemy.Spells.size())), player);
                                        System.out.println();
                                    } else {
                                        System.out.println("Inamicul te-a lovit pentru " + enemy.getDamage() + " damage.");
                                        player.receiveDamage(enemy.getDamage());
                                        System.out.println();
                                    }
                                }
                                if (enemy.currentHP <= 0) {
                                    System.out.println("Ai castigat aceasta batalie, felicitari! Iti poti continua aventura.");
                                    int y = 30;
                                    player.currentExp = player.currentExp + y;
                                    System.out.println("Ai primit " + y + " puncte de experienta.");
                                    if (player.currentExp >= 100) {
                                        player.currentLvl = player.currentLvl + 1;
                                        player.currentExp = 0;
                                        System.out.println("Felicitari, ai ajuns la nivelul " + player.currentLvl + ".");
                                    }
                                    System.out.println();
                                    int chance = rand.nextInt(100);
                                    if (chance < 80) {
                                        int x = rand.nextInt(6, 18);
                                        System.out.println("Batalia ti-a adus niste banuti frumosi. Ai primit " + x + " coins.");
                                        System.out.println();
                                        player.storage.coins = player.storage.coins + x;
                                    }
                                    table.printTable();
                                } else {
                                    System.out.println("Ai pierdut rusinos aceasta batalie.");
                                    return;
                                }
                            }
                            next = scan.next();
                            if (next.compareTo("p") == 0) {
                                table.goSouth();
                                table.current.visited = true;
                            }
                            if (table.current.element.toChar() == 'F') {
                                System.out.println(MapOfStories.get(Cell.Type.FINISH).get(rand.nextInt(MapOfStories.get(Cell.Type.FINISH).size())));
                                System.out.println("Felicitari, aventurierule! Alte provocari te vor astepta in curand!");
                                Accounts.get(poz).NumberOfGames++;
                            }
                        }
                    }
                }else{
                    getAccounts();
                    getStories();
                    throw new InvalidCommandException("Nu avem acesta implementare.");
                }
            }
            else {
                getAccounts();
                getStories();
                throw new InvalidCommandException("Nu avem acest mod. Revenim cu update-uri.");
            }
        }catch (InvalidCommandException e){
            e.printStackTrace();
        }
    }
}