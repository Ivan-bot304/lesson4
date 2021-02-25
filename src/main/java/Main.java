
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) {


        System.out.println("\n\nLesson 1");
        /*Необходимо реализовать приложение на вход которого поступает текст,
        а на выходе выводится частотный словарь букв латинского(английского) алфавита.*/
        String inputString = "aAbbbtttt";
        for(Map.Entry<String, Integer> entry: makeLetterDictionary(inputString).entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }


        System.out.println("\n\nLesson 2");
        /*Напишите метод, который получает на вход Map<K, V> и возвращает Map,
        где ключи и значения поменяны местами.
        Так как значения могут совпадать, то тип значения в Map будет уже не K,
        а Collection<K>: Map<V, Collection<K>>*/
        for(Map.Entry<Integer, String> entry: replace(makeLetterDictionary(inputString)).entrySet()){
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }



        System.out.println("\n\nLesson 3");
        /*Напишите метод, который на вход получает коллекцию объектов,
        а возвращает коллекцию уже без дубликатов.
        <T> Collection<T> removeDuplicates(Collection<T> collection)*/
        ArrayList<Integer> inputList=new ArrayList<Integer>();
        inputList.add(1);
        inputList.add(3);
        inputList.add(3);
        inputList.add(3);
        for (Integer i: removeDuplicates(inputList)){
            System.out.println(i);
        }


        System.out.println("\n\nLesson 4");
        /*Необходимо написать функцию, которая на вход получает массив строк,
        в формате имя_игрока количество_очков.
        Необходимо вывести победителя данной игры. Победителем считается тот,
        кто раньше набрал максимальное количество очков.*/
        String[] inputDate = {"Ivan 5", "Petr 3", "Alex 10", "Petr 8", "Ivan 6", "Alex 5", "Ivan 1", "Petr 5", "Alex 1"};
        for (Player p: createRating(inputDate) ) {
            System.out.println(p);
        }

    }


    static Map<String, Integer> makeLetterDictionary (String str){
        SortedMap<String,Integer> letterDictionary = new TreeMap<String, Integer>() ;

        for (char letter: str.toLowerCase().replaceAll("[^a-z]","").toCharArray()) {
            if(letterDictionary.get(String.valueOf(letter)) == null){
                letterDictionary.put(String.valueOf(letter),1);
            }
            else{
                letterDictionary.put(String.valueOf(letter),letterDictionary.get(String.valueOf(letter))+1);
            }
        }
        return letterDictionary;
    }

   static   <T> Collection<T> removeDuplicates(Collection<T> collection){

        TreeSet treeSet=new TreeSet(collection);

         return treeSet;
    }

    static  Map<Integer, String> replace (Map<String, Integer> map){

        Map<Integer,  String> mapNew = new HashMap<Integer, String>();

        for(Map.Entry<String, Integer> entry: map.entrySet()){

            mapNew.put(entry.getValue(),entry.getKey());

        }

        return  mapNew;

    }

    static List<Player> createRating (String[] inputDate){

        List<String> arrayDate = Arrays.asList(inputDate);

        List<Player> listPlayer= new ArrayList<Player>();

        boolean flgCheck=false; //флаг для определения есть ли уже игрок в списке (true - уже есть; false - еще нет;)

        for (String player: arrayDate) {

            flgCheck=false;

            for (Player lPlayer:listPlayer) {

                if(lPlayer.getName().equals(player.split(" ")[0])){
                    flgCheck=true;
                    lPlayer.setPoints(lPlayer.getPoints()+Integer.valueOf(player.split(" ")[1]));
                }

            }

            if(!flgCheck) {
                Player newPlayer = new Player();
                newPlayer.setName(player.split(" ")[0]);
                newPlayer.setPoints(Integer.valueOf(player.split(" ")[1]));
                listPlayer.add(newPlayer);
            }

        }

        Collections.sort(listPlayer);

        return listPlayer;
    }

    static class Player implements Comparable{
        String name;
        Integer  points;

        public String getName() {
            return name;
        }

        public Integer getPoints() {
            return points;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        @Override
        public String toString() {
            return "Player{" +
                    "name='" + name + '\'' +
                    ", points=" + points +
                    '}';
        }

        @Override
        public int compareTo(Object o) {
            if(((Player) o).points==this.points){

                return 1;
            }
            else{
                return ((Player) o).points - this.points;
            }
        }
    }

}
